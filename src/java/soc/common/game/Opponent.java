package soc.common.game;

import soc.common.board.resources.ResourceList;
import soc.common.game.developmentCards.DevelopmentCard;

public class Opponent extends Player
{
    private int developmentCardsCount = 0;
    private int resourcesCount = 0;
    
    /* (non-Javadoc)
     * @see soc.common.game.Player#getDevelopmentCardsCount()
     */
    @Override
    public int getDevelopmentCardsCount()
    {
        return developmentCardsCount;
    }

    /* (non-Javadoc)
     * @see soc.common.game.Player#getResourcesCount()
     */
    @Override
    public int getResourcesCount()
    {
        return resourcesCount;
    }

    /* (non-Javadoc)
     * @see soc.common.game.Player#removeResources(soc.common.board.resources.ResourceList)
     */
    @Override
    public void removeResources(ResourceList resources)
    {
        resourcesCount -= resources.size();
    }

    /* (non-Javadoc)
     * @see soc.common.game.Player#addDevelopmentCard(soc.common.game.developmentCards.DevelopmentCard)
     */
    @Override
    public void addDevelopmentCard(DevelopmentCard developmentCard)
    {
        developmentCardsCount += 1;
    }

    /* (non-Javadoc)
     * @see soc.common.game.Player#addResources(soc.common.board.resources.ResourceList)
     */
    @Override
    public void addResources(ResourceList resources)
    {
        resourcesCount += resources.size();
    }

    /* (non-Javadoc)
     * @see soc.common.game.Player#useDevelopmentCard(soc.common.game.developmentCards.DevelopmentCard)
     */
    @Override
    public void useDevelopmentCard(DevelopmentCard developmentCard)
    {
        developmentCardsCount -= 1;
    }

}
