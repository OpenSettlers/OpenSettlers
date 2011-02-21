package soc.common.actions.gameAction.standard;

import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.core.Core;
import soc.common.game.Game;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.BeforeDiceRollTurnPhase;
import soc.common.game.gamePhase.turnPhase.BuildingTurnPhase;
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

public class PlayDevelopmentCard extends AbstractTurnAction
{
    private static final long serialVersionUID = 9184498363690392316L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons()
                .developmentCardBack(), null, null, null);

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
    private DevelopmentCard developmentCard;

    /**
     * @return the development card
     */
    public DevelopmentCard getDevelopmentcard()
    {
        return developmentCard;
    }

    /**
     * @param developmentcard
     *            the development card to set
     */
    public PlayDevelopmentCard setDevelopmentcard(
            DevelopmentCard developmentcard)
    {
        this.developmentCard = developmentcard;
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

        if (developmentCard == null)
        {
            invalidMessage = "Devcard cannot be null";
            return false;
        }

        GamePlayer player = game.getPlayerByID(sender);
        if (!developmentCard.isPlayable())
        {
            invalidMessage = "You already played a non-victorypoint devcard this turn, or your devcards are bought this turn";
            return false;
        }

        if (developmentCard.isHasSummoningSickness()
                && developmentCard.getTurnBought() == game.getCurrentTurn()
                        .getID())
        {
            invalidMessage = "Development card is not playable. Wait one turn";
        }

        if (!developmentCard.isValid(game))
        {
            invalidMessage = "Development card is not valid. Reason: "
                    + developmentCard.getInvalidMessage();
            return false;
        }

        if (!player.getDevelopmentCards().contains(developmentCard))
        {
            invalidMessage = "Player does not have development card in possesion";
            return false;
        }

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
        // remove devcard from players hand
        player.getDevelopmentCards().remove(developmentCard);

        player.getPlayedDevelopmentCards().add(developmentCard);

        // Execute devcard
        developmentCard.play(game, player);

        // Mark all other cards needing to wait one turn as unplayable, if we
        // play a non-unique dvcard
        if (developmentCard.isLimitOnePerTurn())
        {
            for (DevelopmentCard dc : player.getDevelopmentCards())
            {
                if (dc.isLimitOnePerTurn())
                {
                    dc.setPlayable(false);
                }
            }
        }

        message = developmentCard.getMessage();
        super.perform(game);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase instanceof BeforeDiceRollTurnPhase
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
        return I18n.get().actions().noToDo();
    }

    @Override
    public ActionWidget createActionWidget(GamePlayer player)
    {
        return Core.get().getClientFactory().getActionWidgetFactory(player)
                .createPlayDevelopmentCardWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getNextActionBehaviourFactory()
                .createPlayDevelopmentCardBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getOpponentReceiveBehaviourFactory()
                .createPlayDevelopmentCardBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getReceiveBehaviourFactory()
                .createPlayDevelopmentCardBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getSendBehaviourFactory().createPlayDevelopmentCardBehaviour(
                        this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
