package soc.common.actions.gameAction.standard;

import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.game.Game;
import soc.common.game.developmentCards.AbstractDevelopmentCard;
import soc.common.game.developmentCards.DevelopmentCard;
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
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwt.client.images.Resources;

public class PlayDevelopmentCard extends AbstractTurnAction
{
    private static final long serialVersionUID = 9184498363690392316L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons()
                        .developmentCardBack16(), Resources.icons()
                        .developmentCardBack32(), Resources.icons()
                        .developmentCardBack48());

        @Override
        public Icon icon()
        {
            return icon;
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
    };
    private AbstractDevelopmentCard developmentCard;

    /** @return the development card */
    public DevelopmentCard getDevelopmentcard()
    {
        return developmentCard;
    }

    /** @param developmentcard
     *            the development card to set */
    public PlayDevelopmentCard setDevelopmentcard(
                    AbstractDevelopmentCard developmentcard)
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
                        && developmentCard.getTurnBought() == game
                                        .getCurrentTurn().getID())
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
            for (DevelopmentCard dc : player.getDevelopmentCards())
                if (dc.isLimitOnePerTurn())
                    dc.setPlayable(false);

        message = developmentCard.getMessage();
        super.perform(game);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase.isBeforeDiceRoll() || turnPhase.isBuilding();
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isPlayTurns();
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

    @Override
    public ActionWidget createActionWidget(
                    ActionWidgetFactory actionWidgetFactory)
    {
        return actionWidgetFactory.createPlayDevelopmentCardWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createPlayDevelopmentCardBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory
                        .createPlayDevelopmentCardBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory
                        .createPlayDevelopmentCardBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createPlayDevelopmentCardBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
