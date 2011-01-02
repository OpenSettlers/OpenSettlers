package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.RollDiceTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;

public class LooseCards extends AbstractTurnAction
{
    private static final long serialVersionUID = -959183979765053849L;
    private ResourceList lostCards;
    private int amountCardsToLoose = 4;

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

}
