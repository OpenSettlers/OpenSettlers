package soc.gwtClient.game.widgetsSvg.visuals;

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
import soc.gwtClient.game.widgetsInterface.visuals.BoardVisual;
import soc.gwtClient.game.widgetsInterface.visuals.CityVisual;
import soc.gwtClient.game.widgetsInterface.visuals.IslandBonusVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PirateVisual;
import soc.gwtClient.game.widgetsInterface.visuals.PointVisual;
import soc.gwtClient.game.widgetsInterface.visuals.RoadVisual;
import soc.gwtClient.game.widgetsInterface.visuals.RobberVisual;
import soc.gwtClient.game.widgetsInterface.visuals.ShipVisual;
import soc.gwtClient.game.widgetsInterface.visuals.SideVisual;
import soc.gwtClient.game.widgetsInterface.visuals.TownVisual;
import soc.gwtClient.game.widgetsInterface.visuals.VisualFactory;
import soc.gwtClient.game.widgetsInterface.visuals.WallVisual;

public class SvgVisualFactory implements VisualFactory
{
    private GameBoardSvg parent;

    public SvgVisualFactory(GameBoardSvg parent)
    {
        super();
        this.parent = parent;
    }

    @Override
    public BoardVisual createBoardVisual(Board board)
    {
        return new BoardSvg(800, 500, board);
    }

    public PirateVisual createPirateVisual(Pirate pirate)
    {
        return new PirateSvg(pirate, parent);
    }

    @Override
    public RobberVisual createRobberVisual(Robber robber)
    {
        return new RobberSvg(robber, parent);
    }

    @Override
    public CityVisual createCityVisual(City city)
    {
        return new CitySvg(city, parent);
    }

    @Override
    public RoadVisual createRoadVisual(Road road)
    {
        return new RoadSvg(road, parent);
    }

    @Override
    public TownVisual createTownVisual(Town town)
    {
        return new TownSvg(town, parent);
    }

    @Override
    public PointVisual createPointVisual(GraphPoint point)
    {
        return new PointSvg(parent, point.getPoint(), parent.getBoardSvg()
                .calculatePosition(point.getPoint()));
    }

    @Override
    public SideVisual createSideVisual(GraphSide side)
    {
        return new SideSvg(parent, side.getSide());
    }

    @Override
    public IslandBonusVisual createIslandBonus(IslandBonus islandBonus)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ShipVisual createShipVisual(Ship ship)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WallVisual createWallVisual(Wall wall)
    {
        // TODO Auto-generated method stub
        return null;
    }

}