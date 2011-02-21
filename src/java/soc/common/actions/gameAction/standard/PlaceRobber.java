package soc.common.actions.gameAction.standard;

import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.board.HexLocation;
import soc.common.board.hexes.Hex;
import soc.common.core.Core;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.BeforeDiceRollTurnPhase;
import soc.common.game.gamePhase.turnPhase.BuildingTurnPhase;
import soc.common.game.gamePhase.turnPhase.RollDiceTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.images.Resources;

/*
 * Robber is placed by current player due to 7 roll or Soldier
 * development card play
 */
public class PlaceRobber extends AbstractTurnAction
{
    private static final long serialVersionUID = 3908846616233400447L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().moveRobberMedium(),
                null, null, null);

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
    private HexLocation newLocation;

    /**
     * @return the newLocation
     */
    public HexLocation getNewLocation()
    {
        return newLocation;
    }

    /**
     * @param newLocation
     *            the newLocation to set
     */
    public PlaceRobber setNewLocation(HexLocation newLocation)
    {
        this.newLocation = newLocation;

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

        // we need a good location
        if (newLocation == null)
        {
            invalidMessage = "Location can't be null";
            return false;
        }

        // Make sure the player does not put robber or pirate on the edge of the
        // map,
        // which is forbidden
        if (game.getBoard().getHexes().isAtEdge(newLocation))
        {
            invalidMessage = "putting robber on the edge is not allowed";
            return false;
        }

        // TODO: check if a previous action included rolling a 7,
        // or playing a soldier development card

        // Player may not leave the robber on the same location
        if (game.getRobber().getLocation().equals(newLocation))
        {
            invalidMessage = "putting robber back onto same location is not allowed";
            return false;
        }

        Hex hex = game.getBoard().getHexes().get(newLocation);

        if (!hex.isRobberPlaceable())
        {
            invalidMessage = "Can't place robber or pirate on a "
                    + hex.getName();
            return false;
        }

        return super.isValid(game);
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
        game.getRobber().setLocation(newLocation);

        // TODO: fix message
        // _Message = String.Format("{0} put the robber on the {1}",
        // xmlGame.GetPlayer(Sender).XmlPlayer.Name,
        // Location.ToString(xmlGame.Board));
        super.perform(game);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase instanceof BeforeDiceRollTurnPhase
                || turnPhase instanceof RollDiceTurnPhase
                || turnPhase instanceof BuildingTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().placeRobberToDo(player.getUser().getName());
    }

    @Override
    public boolean mustExpected()
    {
        return true;
    }

    @Override
    public ActionWidget createActionWidget(GamePlayer player)
    {
        return null;
    }

    @Override
    public GameBehaviour getNextActionBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getNextActionBehaviourFactory()
                .createMoveRobberBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getOpponentReceiveBehaviourFactory()
                .createMoveRobberBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getReceiveBehaviourFactory().createMoveRobberBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getSendBehaviourFactory().createMoveRobberBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
