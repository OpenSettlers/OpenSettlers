package soc.common.game;

import soc.common.board.pieces.PlayerPieceList;
import soc.common.board.ports.PortList;
import soc.common.board.resources.ResourceList;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.DevelopmentCardList;

public class Player extends User
{
    /**
     * @return the playedDevelopmentCards
     */
    public DevelopmentCardList getPlayedDevelopmentCards()
    {
        return playedDevelopmentCards;
    }

    // Hand resource cards
    private ResourceList resources = new ResourceList();
    
    private String color;
    
    // Maximum cards the player may have in his hand
    // This is player specific, as a wall may increase this number
    private int maximumCardsInHandWhenSeven;

    // Stock of the player: list of roads, ships, towns, cities, knights etc a 
    // player has in stock
    private PlayerPieceList stock = new PlayerPieceList();
    
    private PlayerPieceList towns = new PlayerPieceList();
    private PlayerPieceList cities = new PlayerPieceList();
    private PlayerPieceList roads = new PlayerPieceList();

    // Keep track of being on turn
    private boolean isOnTurn=false;
    
    // List of ports the player has developed
    private PortList ports;
    
    // Development cards in hand 
    private DevelopmentCardList developmentCards = new DevelopmentCardList();

    // Played development cards 
    private DevelopmentCardList playedDevelopmentCards = new DevelopmentCardList();
    
    public void removeResources(ResourceList resources)
    {
        resources.subtractResources(resources);
    }
    
    public void addResources(ResourceList resources)
    {
        resources.addAll(resources);
    }
    
    public void useDevelopmentCard(DevelopmentCard developmentCard)
    {
        // Get rid of the card in our list of devcards 
        developmentCards.remove(developmentCard);
        
        // only place it in stock when we should
        if (developmentCard.keepInStock())
        {
            playedDevelopmentCards.add(developmentCard);
        }
    }
    public void addDevelopmentCard(DevelopmentCard developmentCard)
    {
        developmentCards.add(developmentCard);
    }
    
    public int getDevelopmentCardsCount()
    {
        return developmentCards.size();
    }
    
    public int getResourcesCount()
    {
        return resources.size();
    }
    
    /**
     * @return the isOnTurn
     */
    public boolean isOnTurn()
    {
        return isOnTurn;
    }

    /**
     * @return the stock
     */
    public PlayerPieceList getStock()
    {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public Player setStock(PlayerPieceList stock)
    {
        this.stock = stock;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    /**
     * @return the towns
     */
    public PlayerPieceList getTowns()
    {
        return towns;
    }

    /**
     * @return the cities
     */
    public PlayerPieceList getCities()
    {
        return cities;
    }

    /**
     * @return the roads
     */
    public PlayerPieceList getRoads()
    {
        return roads;
    }

    /**
     * @return the ports
     */
    public PortList getPorts()
    {
        return ports;
    }

    /**
     * @return the developmentCards
     */
    public DevelopmentCardList getDevelopmentCards()
    {
        return developmentCards;
    }    
    
    /*
     * Returns amount of gold a player can trade for at the bank using given ResourceList
     */
    public int amountGold(ResourceList resourcesToTradeWith)
    {
        return ports.amountGold(resourcesToTradeWith);
    }    
    
    /*
     * Returns amount of gold a player can trade for
     */
    public int amountGold()
    {
        return ports.amountGold(this.resources);
    }
    
    /**
     * @param isOnTurn the isOnTurn to set
     */
    public Player setOnTurn(boolean isOnTurn)
    {
        this.isOnTurn = isOnTurn;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }


    public int getMaximumCardsInHandWhenSeven()
    {
        return maximumCardsInHandWhenSeven;
    }

    public void setMaximumCardsInHandWhenSeven(int maximumCardsInHandWhenSeven)
    {
        this.maximumCardsInHandWhenSeven = maximumCardsInHandWhenSeven;
    }

    public ResourceList getResources()
    {
        return resources;
    }

    public void setResources(ResourceList resources)
    {
        this.resources = resources;
    }

    public Player setColor(String color)
    {
        this.color = color;
        
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    public String getColor()
    {
        return color;
    }
    
}
