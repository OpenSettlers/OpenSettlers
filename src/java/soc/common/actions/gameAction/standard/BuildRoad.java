package soc.common.actions.gameAction.standard;

import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.board.HexSide;
import soc.common.board.pieces.Road;
import soc.common.game.Game;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.meta.Graphics;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import soc.common.views.widgetsInterface.generic.ToolTip;
import soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;
import soc.gwtClient.images.Resources;

public class BuildRoad extends AbstractTurnAction
{
    private static final long serialVersionUID = -1265027496921863516L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().road(), null, null,
                Resources.icons().citySmall());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public Graphics graphics()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getDescription()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public ToolTip createToolTip()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };
    private HexSide sideLocation;

    public HexSide getSideLocation()
    {
        return sideLocation;
    }

    public BuildRoad setSideLocation(HexSide sideLocation)
    {
        this.sideLocation = sideLocation;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#isValid(soc.common.game.Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
            return false;

        if (sideLocation == null)
        {
            invalidMessage = "Intersection cannot be null";
            return false;
        }

        // the spot must be free still
        if (game.getAllSidePieces().contains(sideLocation))
        {
            invalidMessage = "Already built on the given location";
            return false;
        }

        if (!(game.getCurrentPhase().isInitialPlacement()))
        {
            // if (!(Road.ROAD.canBuild(game.getBoard(), player)))
            // {
            // invalidMessage = "Player cannot build the road";
            // return false;
            // }
            //
            // if (!(Road.ROAD.canPay(player)))
            // {
            // invalidMessage = "Player cannot pay for the road";
            // return false;
            // }
        }

        return true;
    }

    /*
     * (non-Javadoc) TODO: implement DiscoveryHexes
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        boolean usedRoadbuildingToken = false;

        Road road = (Road) player.getStock().getRoads().get(0);
        road.setSide(sideLocation);

        // when in InGame phase, player should pay for road somehow
        if (game.getCurrentPhase().isPlayTurns())
        {
            int roadBuildingTokens = player.getRoadBuildingTokens();

            // When played a roadbuilding card, first use up roadbuilding tokens
            if (roadBuildingTokens > 0)
            {
                // the player has played a road building card this turn
                player.setRoadBuildingTokens(roadBuildingTokens--);
                usedRoadbuildingToken = true;
            }
            else
            {
                player.getResources().moveTo(game.getBank(), road.getCost());
            }

        }
        game.addPiece(road, player);

        if (game.getCurrentPhase().isPlayTurns())
        {
            // Check if the LR should be updated
            // TODO: do LR check
            // game.CalculateLongestRoad(pplayer);
        }

        // TODO: fix message
        // message = String.Format("{0} built a road"
        // ,game.GetPlayer(Sender).XmlPlayer.Name);

        // if (usedRoadbuildingToken)
        // _Message += ", using his roadbuilding development card.";
        message = player.getUser().getName() + " has built a road";

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
        return I18n.get().actions().builtRoadToDo(player.getUser().getName());
    }

    @Override
    public ActionWidget createActionWidget(
            ActionWidgetFactory actionWidgetFactory)
    {
        return actionWidgetFactory.createBuildRoadWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createBuildRoadBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createBuildRoadBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createBuildRoadBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createBuildRoadBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
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
        return factory.getBuildRoadDetailWidget(this);
    }
}
