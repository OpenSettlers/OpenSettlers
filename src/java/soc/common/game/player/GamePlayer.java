package soc.common.game.player;

import soc.common.board.pieces.Army;
import soc.common.board.pieces.PlayerPieceList;
import soc.common.board.ports.PortList;
import soc.common.board.resources.ResourceList;
import soc.common.game.RoadTokensChangedEventHandler;
import soc.common.game.VictoryPointsList;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.server.data.User;

public interface GamePlayer
{
    public User getUser();

    public abstract PlayerPieceList getTownsCities();

    /**
     * @return the roadBuildingTokens
     */
    public abstract int getRoadBuildingTokens();

    /**
     * @param roadBuildingTokens
     *            the roadBuildingTokens to set
     */
    public abstract GamePlayer setRoadBuildingTokens(int roadBuildingTokens);

    /**
     * @return the victoryPoints
     */
    public abstract VictoryPointsList getVictoryPoints();

    /**
     * @return the playedDevelopmentCards
     */
    public abstract DevelopmentCardList getPlayedDevelopmentCards();

    public abstract void removeResources(ResourceList resources);

    public abstract void addResources(ResourceList resources);

    public abstract void useDevelopmentCard(DevelopmentCard developmentCard);

    public abstract void addDevelopmentCard(DevelopmentCard developmentCard);

    public abstract int getDevelopmentCardsCount();

    public abstract int getResourcesCount();

    /**
     * @return the isOnTurn
     */
    public abstract boolean isOnTurn();

    /**
     * @return the stock
     */
    public abstract PlayerPieceList getStock();

    /**
     * @param stock
     *            the stock to set
     */
    public abstract GamePlayer setStock(PlayerPieceList stock);

    /**
     * @return the buildPieces
     */
    public abstract PlayerPieceList getBuildPieces();

    /**
     * @return the ports
     */
    public abstract PortList getPorts();

    /**
     * @return the developmentCards
     */
    public abstract DevelopmentCardList getDevelopmentCards();

    /*
     * Returns amount of gold a player can trade for at the bank using given
     * ResourceList
     */
    public abstract int amountGold(ResourceList resourcesToTradeWith);

    /*
     * Returns amount of gold a player can trade for
     */
    public abstract int amountGold();

    /**
     * @param isOnTurn
     *            the isOnTurn to set
     */
    public GamePlayer setOnTurn(boolean isOnTurn);

    public int getMaximumCardsInHandWhenSeven();

    public void setMaximumCardsInHandWhenSeven(int maximumCardsInHandWhenSeven);

    public ResourceList getResources();

    public void setResources(ResourceList resources);

    public GamePlayer setColor(String color);

    public String getColor();

    public void addRoadTokenChangedEventHandler(
            RoadTokensChangedEventHandler handler);

    public Army getArmy();
}