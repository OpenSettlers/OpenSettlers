package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.developmentCards.DevelopmentCard;

public class BuyDevelopmentCard extends TurnAction
{
    private ResourceList resources;
    private DevelopmentCard devCard;
    
    /**
     * @return the devCard
     */
    public DevelopmentCard getDevCard()
    {
        return devCard;
    }

    /**
     * @param devCard the devCard to set
     */
    public BuyDevelopmentCard setDevCard(DevelopmentCard devCard)
    {
        this.devCard = devCard;
    
        return this;
    }

    /**
     * @return the resources
     */
    public ResourceList getResources()
    {
        return resources;
    }

    /**
     * @param resources the resources to set
     */
    public BuyDevelopmentCard setResources(ResourceList resources)
    {
        this.resources = resources;
    
        return this;
    }

    public void setDevelopmentCard(DevelopmentCard drawTop)
    {
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#isValid(soc.common.game.Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game)) return false;

        // we need resources
        if (resources == null) return false;

        // ...and to devcard too.
        if (game.getDevelopmentCards().size() == 0)
        {
            invalidMessage = "Development cards are all gone!";
            return false;
        }

        // we need just three resources
        if (resources.size() != 3) return false;

        // three  discoveries is OK
        // TODO: fix ResourceList.ofType
        //if (resources.ofType(Diamond.class) > 2) return true;
        
        Player player = game.getPlayerByID(sender);

        if (!player.getResources().hasAtLeast(resources))
        {
            invalidMessage = "Player does not have given resources";
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
        
        // Perform  resources administration
        player.getResources().subtractResources(resources);
        game.getBank().add(resources);

        // Player should wait a turn before able to play new devcard
        devCard.setPlayable(!devCard.isHasSummoningSickness());
        devCard.setTurnBought(game.getCurrentTurn().getID());
        
        // Administer devcards 
        player.getDevelopmentCards().add(devCard);
        game.getDevelopmentCards().remove(devCard);

        // TODO: fix message
        //message = String.Format("{0} bought a development card",
        //    gamePlayer.XmlPlayer.Name);
        
        super.perform(game);
    }

}
