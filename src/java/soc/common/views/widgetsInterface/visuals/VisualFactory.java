package soc.common.views.widgetsInterface.visuals;

import soc.common.board.Board;
import soc.common.board.pieces.City;
import soc.common.board.pieces.IslandBonus;
import soc.common.board.pieces.Pirate;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Robber;
import soc.common.board.pieces.Ship;
import soc.common.board.pieces.Town;
import soc.common.board.pieces.Wall;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;

public interface VisualFactory
{

    public RobberVisual createRobberVisual(Robber robber);

    public PirateVisual createPirateVisual(Pirate pirate);

    public TownVisual createTownVisual(Town town);

    public CityVisual createCityVisual(City city);

    public RoadVisual createRoadVisual(Road road);

    public BoardVisual createBoardVisual(Board board);

    public ISideVisual createSideVisual(GraphSide side);

    public IPointVisual createPointVisual(GraphPoint point);

    public IslandBonusVisual createIslandBonus(IslandBonus islandBonus);

    public ShipVisual createShipVisual(Ship ship);

    public WallVisual createWallVisual(Wall wall);
}
