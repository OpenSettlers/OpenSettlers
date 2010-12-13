package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.developmentCards.DevelopmentCard;


public class PlayDevelopmentCard extends TurnAction
{
    private DevelopmentCard developmentcard;

    /**
     * @return the developmentcard
     */
    public DevelopmentCard getDevelopmentcard()
    {
        return developmentcard;
    }

    /**
     * @param developmentcard the developmentcard to set
     */
    public PlayDevelopmentCard setDevelopmentcard(DevelopmentCard developmentcard)
    {
        this.developmentcard = developmentcard;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    
}
