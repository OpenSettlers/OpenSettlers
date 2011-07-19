package org.soc.common.game.player;

import java.io.Serializable;

import org.soc.common.board.pieces.Army;
import org.soc.common.board.pieces.pieceLists.CityList;
import org.soc.common.board.pieces.pieceLists.PointPieceList;
import org.soc.common.board.pieces.pieceLists.ProducableList;
import org.soc.common.board.pieces.pieceLists.RoadList;
import org.soc.common.board.pieces.pieceLists.ShipList;
import org.soc.common.board.pieces.pieceLists.SidePieceList;
import org.soc.common.board.pieces.pieceLists.Stock;
import org.soc.common.board.pieces.pieceLists.TownList;
import org.soc.common.board.ports.PortList;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.bots.Bot;
import org.soc.common.game.RoadTokensChangedEventHandler;
import org.soc.common.game.VictoryPointsList;
import org.soc.common.game.developmentCards.DevelopmentCardList;
import org.soc.common.server.entities.User;


import com.google.gwt.event.shared.HandlerRegistration;

/*
 * Represents a player playing a game
 */
public interface GamePlayer extends Serializable
{
    public User getUser();

    public GamePlayer setColor(String color);

    public String getColor();

    public TownList getTowns();

    public CityList getCities();

    public RoadList getRoads();

    public ShipList getShips();

    public ProducableList getProducables();

    public SidePieceList getSidePieces();

    public PointPieceList getPointPieces();

    public Stock getStock();

    public int getRoadBuildingTokens();

    public GamePlayer setRoadBuildingTokens(int roadBuildingTokens);

    public VictoryPointsList getVictoryPoints();

    public DevelopmentCardList getPlayedDevelopmentCards();

    public boolean isOnTurn();

    public GamePlayer setStock(Stock stock);

    public PortList getPorts();

    public DevelopmentCardList getDevelopmentCards();

    public GamePlayer setOnTurn(boolean isOnTurn);

    public int getMaximumCardsInHandWhenSeven();

    public void setMaximumCardsInHandWhenSeven(int maximumCardsInHandWhenSeven);

    public ResourceList getResources();

    public HandlerRegistration addRoadTokenChangedEventHandler(
                    RoadTokensChangedEventHandler handler);

    public Army getArmy();

    public Bot getBot();
}