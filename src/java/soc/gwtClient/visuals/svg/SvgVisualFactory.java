package soc.gwtClient.visuals.svg;

import soc.common.board.Board;
import soc.common.board.pieces.City;
import soc.common.board.pieces.Pirate;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Robber;
import soc.common.board.pieces.Town;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.gwtClient.visuals.abstractVisuals.AbstractVisualFactory;
import soc.gwtClient.visuals.abstractVisuals.BoardVisual;
import soc.gwtClient.visuals.abstractVisuals.CityVisual;
import soc.gwtClient.visuals.abstractVisuals.PirateVisual;
import soc.gwtClient.visuals.abstractVisuals.PointVisual;
import soc.gwtClient.visuals.abstractVisuals.RoadVisual;
import soc.gwtClient.visuals.abstractVisuals.RobberVisual;
import soc.gwtClient.visuals.abstractVisuals.SideVisual;
import soc.gwtClient.visuals.abstractVisuals.TownVisual;

public class SvgVisualFactory extends AbstractVisualFactory
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

}
