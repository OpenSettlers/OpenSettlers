package org.soc.gwt.client.game.widgetsSvg.visuals;

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
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.CityVisual;
import org.soc.common.views.widgetsInterface.visuals.IslandBonusVisual;
import org.soc.common.views.widgetsInterface.visuals.PirateVisual;
import org.soc.common.views.widgetsInterface.visuals.RoadVisual;
import org.soc.common.views.widgetsInterface.visuals.RobberVisual;
import org.soc.common.views.widgetsInterface.visuals.ShipVisual;
import org.soc.common.views.widgetsInterface.visuals.TownVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;
import org.soc.common.views.widgetsInterface.visuals.WallVisual;
import org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractSideVisual;

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