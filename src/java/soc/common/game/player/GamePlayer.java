package soc.common.game.player;

import soc.common.board.pieces.Army;
import soc.common.board.pieces.CityList;
import soc.common.board.pieces.PointPieceList;
import soc.common.board.pieces.ProducableList;
import soc.common.board.pieces.RoadList;
import soc.common.board.pieces.ShipList;
import soc.common.board.pieces.SidePieceList;
import soc.common.board.pieces.Stock;
import soc.common.board.pieces.TownList;
import soc.common.board.ports.PortList;
import soc.common.board.resources.ResourceList;
import soc.common.game.RoadTokensChangedEventHandler;
import soc.common.game.VictoryPointsList;
import soc.common.game.developmentCards.DevelopmentCardList;
import soc.common.server.data.User;

public interface GamePlayer
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

    public void addRoadTokenChangedEventHandler(
            RoadTokensChangedEventHandler handler);

    public Army getArmy();
}