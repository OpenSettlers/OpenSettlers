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
import soc.common.views.widgetsInterface.visuals.BoardVisual;
import soc.common.views.widgetsInterface.visuals.CityVisual;
import soc.common.views.widgetsInterface.visuals.IslandBonusVisual;
import soc.common.views.widgetsInterface.visuals.PirateVisual;
import soc.common.views.widgetsInterface.visuals.RoadVisual;
import soc.common.views.widgetsInterface.visuals.RobberVisual;
import soc.common.views.widgetsInterface.visuals.ShipVisual;
import soc.common.views.widgetsInterface.visuals.TownVisual;
import soc.common.views.widgetsInterface.visuals.VisualFactory;
import soc.common.views.widgetsInterface.visuals.WallVisual;
import soc.gwtClient.game.widgetsAbstract.visuals.AbstractSideVisual;

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
        return new PirateSvg(parent, pirate);
    }

    @Override
    public RobberVisual createRobberVisual(Robber robber)
    {
        return new RobberSvg(parent, robber);
    }

    @Override
    public CityVisual createCityVisual(City city)
    {
        return new CitySvg(parent, city);
    }

    @Override
    public RoadVisual createRoadVisual(Road road)
    {
        return new RoadSvg(parent, road);
    }

    @Override
    public TownVisual createTownVisual(Town town)
    {
        return new TownSvg(parent, town);
    }

    @Override
    public AbstractPointVisual createPointVisual(GraphPoint point)
    {
        return new PointSvg(parent, point.getPoint(), parent.getBoardSvg()
                .calculatePosition(point.getPoint()));
    }

    @Override
    public AbstractSideVisual createSideVisual(GraphSide side)
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