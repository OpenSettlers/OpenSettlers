package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.developmentCards.DevelopmentCard;


public class PlayDevelopmentCard extends TurnAction
{
    private DevelopmentCard developmentCard;

    /**
     * @return the developmentcard
     */
    public DevelopmentCard getDevelopmentcard()
    {
        return developmentCard;
    }

    /**
     * @param developmentcard the developmentcard to set
     */
    public PlayDevelopmentCard setDevelopmentcard(DevelopmentCard developmentcard)
    {
        this.developmentCard = developmentcard;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#isValid(soc.common.game.Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game)) return false;

        if (developmentCard == null)
        {
            invalidMessage = "Devcard cannot be null";
            return false;
        }

        Player player = game.getPlayerByID(sender);
        if (!developmentCard.isPlayable())
        {
            invalidMessage = "You already played a non-victorypoint devcard this turn, or your devcards are bought this turn";
            return false;
        }

        if (developmentCard.isHasSummoningSickness() &&
                developmentCard.getTurnBought() == game.getCurrentTurn().getID())
        {
            invalidMessage = "Development card is not playable. Wait one turn";
        }

        if (!developmentCard.isValid(game))
        {
            invalidMessage = "Development card is not valid. Reason: " + 
                developmentCard.getInvalidMessage();
            return false;
        }
        
        if (!player.getDevelopmentCards().contains(developmentCard))
        {
            invalidMessage = "Player does not have development card in possesion";
            return false;
        }

        return true;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        Player gamePlayer = game.getPlayerByID(sender);
        
        //remove devcard from players hand
        player.getDevelopmentCards().remove(developmentCard);

        player.getPlayedDevelopmentCards().add(developmentCard);

        // Execute devcard
        developmentCard.play(game, player);

        // Mark all other cards needing to wait one turn as unplayable, if we play a non-unique dvcard
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
    
}
