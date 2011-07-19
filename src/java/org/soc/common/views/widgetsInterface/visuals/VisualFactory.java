package org.soc.common.views.widgetsInterface.visuals;

import org.soc.common.board.Board;
import org.soc.common.board.pieces.City;
import org.soc.common.board.pieces.IslandBonus;
import org.soc.common.board.pieces.Pirate;
import org.soc.common.board.pieces.Road;
import org.soc.common.board.pieces.Robber;
import org.soc.common.board.pieces.Ship;
import org.soc.common.board.pieces.Town;
import org.soc.common.board.pieces.Wall;
import org.soc.common.board.routing.GraphPoint;
import org.soc.common.board.routing.GraphSide;

public interface VisualFactory
{
    public RobberVisual createRobberVisual(Robber robber);

    public PirateVisual createPirateVisual(Pirate pirate);

    public TownVisual createTownVisual(Town town);

    public CityVisual createCityVisual(City city);

    public RoadVisual createRoadVisual(Road road);

    public BoardVisual createBoardVisual(Board board);

    public SideVisual createSideVisual(GraphSide side);

    public PointVisual createPointVisual(GraphPoint point);

    public IslandBonusVisual createIslandBonus(IslandBonus islandBonus);

    public ShipVisual createShipVisual(Ship ship);

    public WallVisual createWallVisual(Wall wall);
}