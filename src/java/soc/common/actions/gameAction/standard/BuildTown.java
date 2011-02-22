package soc.common.actions.gameAction.standard;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.board.HexLocation;
import soc.common.board.HexPoint;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.pieces.Town;
import soc.common.board.ports.Port;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.playerInfo.ActionDetailWidget;
import soc.gwtClient.images.Resources;

public class BuildTown extends AbstractTurnAction
{
    private static final long serialVersionUID = -2087932156154353767L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().town(), null, null,
                Resources.icons().townSmall());

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
    private HexPoint pointLocation;

    /**
     * @return the pointLocation
     */
    public HexPoint getPointLocation()
    {
        return pointLocation;
    }

    /**
     * @param pointLocation
     *            the pointLocation to set
     */
    public BuildTown setPointLocation(HexPoint pointLocation)
    {
        this.pointLocation = pointLocation;

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

        // we need at least an instance of the new place
        if (pointLocation == null)
        {
            invalidMessage = "Location cannot be null";
            return false;
        }

        // TODO: add neighbors check
        if (game.getAllPointPieces().contains(pointLocation))
        {
            invalidMessage = "The spot and its neighbours is already used by anyone";
            return false;
        }

        // player should have a ship or road at some neighbour
        // if (!(game.getCurrentPhase() instanceof InitialPlacementGamePhase))
        // {
        // boolean contains = true;
        // for (HexSide neighbour : pointLocation.getNeighbourSides())
        // {
        // if (player.getBuildPieces().getSidePieces().contains(neighbour))
        // {
        // contains = false;
        // break;
        // }
        // }
        // if (!contains)
        // {
        // invalidMessage = "No neighbouring ship or road found";
        // return false;
        // }
        // }

        // check if location is suitable (hexpoint neighbours can't be
        // already built on)

        // check if location is a valid one to built on
        // (contains at least a landhex)

        // couldnt find a neighbour, assume invalid state
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
        // update town management
        Town town = (Town) player.getStock().getTowns().get(0);
        town.setPoint(pointLocation);
        game.addPiece(town, player);

        if (game.getCurrentPhase().isPlayTurns())
        {
            // remove players' resources and put them in the bank
            player.getResources().moveTo(game.getBank(), town.getCost());
        }
        if (game.getCurrentPhase().isInitialPlacement()
                && player.getPointPieces().size() == 2)
        {
            // player gets resources in neighbouring hexes
            ResourceList resourcesFromPlacement = new ResourceList();
            for (HexLocation loc : pointLocation.getHexLocations())
            {
                Hex hex = game.getBoard().getHexes().get(loc);
                if (hex instanceof ResourceHex)
                {
                    resourcesFromPlacement.add(((ResourceHex) hex)
                            .getResource());
                }
            }
            player.getResources().swapResourcesFrom(resourcesFromPlacement,
                    game.getBank());
        }

        // Check if the town is built on a port, if so, add port
        // to list of ports of the player

        // Get a list of ports
        List<Port> ports = new ArrayList<Port>();
        for (HexLocation locaction : pointLocation.getHexLocations())
        {
            Hex hex = game.getBoard().getHexes().get(locaction);
            if (hex instanceof SeaHex)
            {
                SeaHex seaHex = (SeaHex) hex;
                if (seaHex.getPort() != null)
                {
                    ports.add(seaHex.getPort());
                }
            }
        }
        for (Port port : ports)
        {
            if (port.getHexSide().getHexPoint1().equals(pointLocation)
                    || port.getHexSide().getHexPoint2().equals(pointLocation))
            {
                player.getPorts().add(port);
            }
        }

        message = I18n.get().actions().builtTown(player.getUser().getName());

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
    public ActionWidget createActionWidget(
            ActionWidgetFactory actionWidgetFactory)
    {
        return actionWidgetFactory.createBuildTownWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createBuildTownBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createBuildTownBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createBuildTownBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createBuildTownBehaviour(this);
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
        return factory.getBuildTownDetailWidget(this);
    }
}
