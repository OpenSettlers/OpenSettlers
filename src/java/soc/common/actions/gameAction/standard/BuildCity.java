package soc.common.actions.gameAction.standard;

import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.board.hexes.Hex;
import soc.common.board.layout.HexLocation;
import soc.common.board.layout.HexPoint;
import soc.common.board.pieces.City;
import soc.common.board.pieces.Town;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;
import soc.gwt.client.images.Resources;

/*
 * Builds next city from stock on the playing board
 */
public class BuildCity extends AbstractTurnAction
{
    private static final long serialVersionUID = -2767352130887235545L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().city16(), Resources
                        .icons().city32(), Resources.icons().city48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "BuildCity";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().city();
        }

        @Override
        public String getDescription()
        {
            return null;// I18n.get().constants().getc
        }

    };
    private HexPoint pointLocation;

    /** @return the location */
    public HexPoint getPointLocation()
    {
        return pointLocation;
    }

    /** @param location
     *            the location to set */
    public BuildCity setLocation(HexPoint pointLocation)
    {
        this.pointLocation = pointLocation;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.gameAction.GameAction#isValid()
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
            return false;

        // we need at least an instance of the new place
        if (pointLocation == null)
        {
            invalidMessage = "Location cant be null";
            return false;
        }

        // player should have a ship or road at some neighbour
        GamePlayer player = game.getPlayerByID(sender);

        if (!(player.getTowns().contains(pointLocation)))
        {
            invalidMessage = "No town found to replace with a city";
            return false;
        }

        // if (!City.CITY.canBuild(game.getBoard(), player))
        // {
        // invalidMessage = "Player cannot build the city";
        // return false;
        // }
        //
        // if (!City.CITY.canPay(player))
        // {
        // invalidMessage = "Player cannot pay for the city";
        // return false;
        // }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        GamePlayer player = game.getPlayerByID(sender);

        // Get first city from stock
        City city = player.getStock().getCities().get(0);
        city.setPoint(pointLocation);

        if (game.getCurrentPhase().isPlayTurns())
        {
            // Pay for the city
            player.getResources().moveTo(game.getBank(), city.getCost());

            // Remove the town from the board
            Town town = player.getTowns().get(pointLocation);
            town.removeFromPlayer(player);
        }
        if (game.getCurrentPhase().isInitialPlacement())
        {
            // Add resources to player
            ResourceList resourcesFromCity = new ResourceList();
            for (HexLocation hexLocation : pointLocation.getHexLocations())
            {
                Hex hex = game.getBoard().getHexes().get(hexLocation);
                if (hex.canHaveResource())
                {
                    resourcesFromCity.add(hex.getResource());
                }
            }
            player.getResources().addList(resourcesFromCity);
        }

        // Add city to player
        game.addPiece(city, player);

        // TODO: fix message
        // message = String.Format("{0} build a city at {1}",
        // gamePlayer.XmlPlayer.Name, Location.ToString(xmlGame.Board));
        super.perform(game);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase.isBuilding();
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isPlayTurns() || gamePhase.isInitialPlacement();
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().builtTownToDo(player.getUser().getName());
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ActionWidget createActionWidget(
                    ActionWidgetFactory actionWidgetFactory)
    {
        return actionWidgetFactory.createBuildCityWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createBuildCityBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createBuildCityBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createBuildCityBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createBuildCityBehaviour(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#createActionDetailWidget
     * (soc.common.ui.ActionDetailWidgetFactory)
     */
    @Override
    public ActionDetailWidget createActionDetailWidget(
                    ActionDetailWidgetFactory factory)
    {
        return factory.getBuildCityDetailWidget(this);
    }
}
