package soc.gwtClient.visuals.svg;

import soc.common.board.Board;
import soc.common.board.pieces.City;
import soc.common.board.pieces.Pirate;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Robber;
import soc.common.board.pieces.Town;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.client.visuals.board.BoardVisual;
import soc.common.client.visuals.game.AbstractVisualFactory;
import soc.common.client.visuals.game.CityVisual;
import soc.common.client.visuals.game.PirateVisual;
import soc.common.client.visuals.game.PointVisual;
import soc.common.client.visuals.game.RoadVisual;
import soc.common.client.visuals.game.RobberVisual;
import soc.common.client.visuals.game.SideVisual;
import soc.common.client.visuals.game.TownVisual;

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
        return new PirateSvg(pirate);
    }

    @Override
    public RobberVisual createRobberVisual(Robber robber)
    {
        return new RobberSvg(robber);
    }

    @Override
    public CityVisual createCityVisual(City city)
    {
        return new CitySvg(city);
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
        return new SvgPointVisual(parent, point.getPoint(), parent
                .getBoardSvg().CalculatePosition(point.getPoint()));
    }

    @Override
    public SideVisual createSideVisual(GraphSide side)
    {
        return new SvgSideVisual(parent, side.getSide());
    }

}
