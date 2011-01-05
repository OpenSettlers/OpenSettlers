package soc.common.game.player;

import soc.common.board.pieces.City;
import soc.common.board.pieces.PlayerPieceList;
import soc.common.board.pieces.Town;
import soc.common.board.ports.PortList;
import soc.common.board.resources.ResourceList;
import soc.common.game.RoadTokensChangedEvent;
import soc.common.game.RoadTokensChangedEventHandler;
import soc.common.game.TurnChangedEvent;
import soc.common.game.TurnChangedEventHandler;
import soc.common.game.VictoryPointsList;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.server.data.User;

import com.google.gwt.event.shared.SimpleEventBus;

/*
 * Implementation of a gameplayer
 */
public class GamePlayerImpl implements GamePlayer
{
    private User user;
    private SimpleEventBus eventBus;
    // Hand resource cards
    private ResourceList resources = new ResourceList();

    private int roadBuildingTokens;

    private String color;

    // Maximum cards the player may have in his hand
    // This is player specific, as a wall may increase this number
    private int maximumCardsInHandWhenSeven;

    // Stock of the player: list of roads, ships, towns, cities, knights etc a
    // player has in stock
    private PlayerPieceList stock = new PlayerPieceList();

    // List of pieces the player has built
    private PlayerPieceList buildPieces = new PlayerPieceList();

    // Keep track of being on turn
    private boolean isOnTurn = false;

    // List of ports the player has developed
    private PortList ports = new PortList();

    // Development cards in hand
    private DevelopmentCardList developmentCards = new DevelopmentCardList();

    // Played development cards
    private DevelopmentCardList playedDevelopmentCards = new DevelopmentCardList();

    // List of victory points
    private VictoryPointsList victoryPoints = new VictoryPointsList();

    /**
     * @param user
     *            the user to set
     */
    public GamePlayerImpl setUser(User user)
    {
        this.user = user;

        return this;
    }

    private SimpleEventBus getEventBus()
    {
        if (eventBus == null)
        {
            eventBus = new SimpleEventBus();
        }

        return eventBus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getTownsCities()
     */
    public PlayerPieceList getTownsCities()
    {
        PlayerPieceList result = new PlayerPieceList();

        result.add(buildPieces.ofType(Town.TOWN));
        result.add(buildPieces.ofType(City.CITY));

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getRoadBuildingTokens()
     */
    public int getRoadBuildingTokens()
    {
        return roadBuildingTokens;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#setRoadBuildingTokens(int)
     */
    public GamePlayer setRoadBuildingTokens(int roadBuildingTokens)
    {
        this.roadBuildingTokens = roadBuildingTokens;

        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getVictoryPoints()
     */
    public VictoryPointsList getVictoryPoints()
    {
        return victoryPoints;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getPlayedDevelopmentCards()
     */
    public DevelopmentCardList getPlayedDevelopmentCards()
    {
        return playedDevelopmentCards;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.GamePlayer#removeResources(soc.common.board.resources
     * .ResourceList)
     */
    public void removeResources(ResourceList resources)
    {
        resources.subtractResources(resources);
    }

    /*
     * (non-Javadoc)
     * 
     * @seesoc.common.game.GamePlayer#addResources(soc.common.board.resources.
     * ResourceList)
     */
    public void addResources(ResourceList resources)
    {
        resources.add(resources);
    }

    /*
     * (non-Javadoc)
     * 
     * @seesoc.common.game.GamePlayer#useDevelopmentCard(soc.common.game.
     * developmentCards.DevelopmentCard)
     */
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

    /*
     * (non-Javadoc)
     * 
     * @seesoc.common.game.GamePlayer#addDevelopmentCard(soc.common.game.
     * developmentCards.DevelopmentCard)
     */
    public void addDevelopmentCard(DevelopmentCard developmentCard)
    {
        developmentCards.add(developmentCard);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getDevelopmentCardsCount()
     */
    public int getDevelopmentCardsCount()
    {
        return developmentCards.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getResourcesCount()
     */
    public int getResourcesCount()
    {
        return resources.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#isOnTurn()
     */
    public boolean isOnTurn()
    {
        return isOnTurn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getStock()
     */
    public PlayerPieceList getStock()
    {
        return stock;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.GamePlayer#setStock(soc.common.board.pieces.PlayerPieceList
     * )
     */
    public GamePlayer setStock(PlayerPieceList stock)
    {
        this.stock = stock;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getBuildPieces()
     */
    public PlayerPieceList getBuildPieces()
    {
        return buildPieces;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getPorts()
     */
    public PortList getPorts()
    {
        return ports;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getDevelopmentCards()
     */
    public DevelopmentCardList getDevelopmentCards()
    {
        return developmentCards;
    }

    /*
     * Returns amount of gold a player can trade for at the bank using given
     * ResourceList
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.GamePlayer#amountGold(soc.common.board.resources.ResourceList
     * )
     */
    public int amountGold(ResourceList resourcesToTradeWith)
    {
        return ports.amountGold(resourcesToTradeWith);
    }

    /*
     * Returns amount of gold a player can trade for
     */
    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#amountGold()
     */
    public int amountGold()
    {
        return ports.amountGold(this.resources);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#setOnTurn(boolean)
     */
    public GamePlayer setOnTurn(boolean isOnTurn)
    {
        this.isOnTurn = isOnTurn;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getMaximumCardsInHandWhenSeven()
     */
    public int getMaximumCardsInHandWhenSeven()
    {
        return maximumCardsInHandWhenSeven;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#setMaximumCardsInHandWhenSeven(int)
     */
    public void setMaximumCardsInHandWhenSeven(int maximumCardsInHandWhenSeven)
    {
        this.maximumCardsInHandWhenSeven = maximumCardsInHandWhenSeven;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getResources()
     */
    public ResourceList getResources()
    {
        return resources;
    }

    /*
     * (non-Javadoc)
     * 
     * @seesoc.common.game.GamePlayer#setResources(soc.common.board.resources.
     * ResourceList)
     */
    public void setResources(ResourceList resources)
    {
        this.resources = resources;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#setColor(java.lang.String)
     */
    public GamePlayer setColor(String color)
    {
        this.color = color;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.GamePlayer#getColor()
     */
    public String getColor()
    {
        return color;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.GamePlayer#addOnTurnChangedEventHandler(soc.common.game
     * .TurnChangedEventHandler)
     */
    public void addOnTurnChangedEventHandler(TurnChangedEventHandler handler)
    {
        getEventBus().addHandler(TurnChangedEvent.TYPE, handler);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.GamePlayer#addRoadTokenChangedEventHandler(soc.common
     * .game.RoadTokensChangedEventHandler)
     */
    public void addRoadTokenChangedEventHandler(
            RoadTokensChangedEventHandler handler)
    {
        getEventBus().addHandler(RoadTokensChangedEvent.TYPE, handler);
    }

    @Override
    public User getUser()
    {
        return user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return user.getId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GamePlayerImpl other = (GamePlayerImpl) obj;
        if (user == null)
        {
            if (other.user != null)
                return false;
        }
        else if (!user.equals(other.getUser()))
            return false;
        return true;
    }
}
