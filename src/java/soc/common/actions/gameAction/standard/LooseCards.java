package soc.common.actions.gameAction.standard;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.RollDiceTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameBoard.factories.GameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.ReceiveGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;

public class LooseCards extends AbstractTurnAction
{
    private static final long serialVersionUID = -959183979765053849L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, null, null);

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
    private ResourceList lostCards;
    private int amountCardsToLoose = 4;

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#setPlayer(soc.common
     * .game.player.GamePlayer)
     */
    @Override
    public GameAction setPlayer(GamePlayer player)
    {
        this.player = player;
        this.sender = player.getUser().getId();
        amountCardsToLoose = player.getResources().halfCount();

        return this;
    }

    /**
     * @return the lostCards
     */
    public ResourceList getLostCards()
    {
        return lostCards;
    }

    /**
     * @param lostCards
     *            the lostCards to set
     */
    public LooseCards setLostCards(ResourceList lostCards)
    {
        this.lostCards = lostCards;

        return this;
    }

    /**
     * @return the amountCardsToLoose
     */
    public int getAmountCardsToLoose()
    {
        return amountCardsToLoose;
    }

    /**
     * @param amountCardsToLoose
     *            the amountCardsToLoose to set
     */
    public LooseCards setAmountCardsToLoose(int amountCardsToLoose)
    {
        this.amountCardsToLoose = amountCardsToLoose;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#isValid(soc.common.game
     * .Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
        {
            return false;
        }

        if (amountCardsToLoose < 4)
        {
            invalidMessage = "Amount of cards to loose should be 4 or more";
            return false;
        }

        if (lostCards == null)
        {
            invalidMessage = "LostCards cannot be null";
            return false;
        }

        if (lostCards.size() != amountCardsToLoose)
        {
            invalidMessage = "Amount of resources to loose should equal amount of lost cards";
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.turnActions.AbstractTurnAction#perform(
     * soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        // Move resources from player to bank
        game.getBank().swapResourcesFrom(lostCards, player.getResources());

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().looseCardsToDo(player.getUser().getName(),
                amountCardsToLoose);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase instanceof RollDiceTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }

    @Override
    public ActionWidget createActionWidget(GamePlayer player)
    {
        return null;
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createLooseCardsBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createLooseCardsBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createLooseCardsBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createLooseCardsBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
