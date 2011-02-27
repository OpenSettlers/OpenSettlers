package soc.common.actions.gameAction.standard;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.board.HexLocation;
import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.hexes.Hex;
import soc.common.board.pieces.Town;
import soc.common.board.pieces.pieceLists.PointPieceList;
import soc.common.board.ports.Port;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
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

public class BuildTown extends AbstractTurnAction
{
    private static final long serialVersionUID = -2087932156154353767L;
    private static transient Meta meta = new Meta()
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

        if (game.getAllPointPieces().contains(pointLocation))
        {
            invalidMessage = "The spot and its neighbours is already used by anyone";
            return false;
        }

        if (!game.getCurrentPhase().isInitialPlacement())
        {
            boolean hasNeighbour = false;
            PointPieceList allPointPieces = game.getAllPointPieces();
            for (HexPoint point : pointLocation.getNeighbours())
                if (allPointPieces.contains(point))
                    hasNeighbour = true;

            if (hasNeighbour)
            {
                invalidMessage = "A neighbouring pointpiece was found. You can build here";
                return false;
            }
        }

        // player should have a ship or road at some neighbour
        if (!(game.getCurrentPhase().isInitialPlacement()))
        {
            boolean contains = true;
            for (HexSide neighbour : pointLocation.getNeighbourSides())
            {
                if (player.getSidePieces().contains(neighbour))
                {
                    contains = false;
                    break;
                }
            }

            if (!contains)
            {
                invalidMessage = "No neighbouring ship or road found";
                return false;
            }
        }

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
                if (hex.hasResource())
                {
                    resourcesFromPlacement.add(hex.getResource());
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
            if (hex.hasPort())
                ports.add(hex.getPort());
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
