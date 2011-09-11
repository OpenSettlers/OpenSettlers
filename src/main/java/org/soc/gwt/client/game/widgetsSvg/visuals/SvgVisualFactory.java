package org.soc.gwt.client.game.widgetsSvg.visuals;

import org.soc.common.game.board.Board;
import org.soc.common.game.board.GraphPoint;
import org.soc.common.game.board.GraphSide;
import org.soc.common.game.pieces.City;
import org.soc.common.game.pieces.IslandBonus;
import org.soc.common.game.pieces.Pirate;
import org.soc.common.game.pieces.Road;
import org.soc.common.game.pieces.Robber;
import org.soc.common.game.pieces.Ship;
import org.soc.common.game.pieces.Town;
import org.soc.common.game.pieces.Wall;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.CityVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.IslandBonusVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.PirateVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.RoadVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.RobberVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.ShipVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.TownVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.WallVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;
import org.soc.gwt.client.game.widgetsAbstract.visuals.AbstractSideVisual;

public class SvgVisualFactory implements VisualFactory
{
  private GameBoardSvg parent;

  public SvgVisualFactory(GameBoardSvg parent)
  {
    super();
    this.parent = parent;
  }
  @Override public BoardVisual createBoardVisual(Board board)
  {
    return new BoardSvg(800, 500, board);
  }
  public PirateVisual createPirateVisual(Pirate pirate)
  {
    return new PirateSvg(parent, pirate);
  }
  @Override public RobberVisual createRobberVisual(Robber robber)
  {
    return new RobberSvg(parent, robber);
  }
  @Override public CityVisual createCityVisual(City city)
  {
    return new CitySvg(parent, city);
  }
  @Override public RoadVisual createRoadVisual(Road road)
  {
    return new RoadSvg(parent, road);
  }
  @Override public TownVisual createTownVisual(Town town)
  {
    return new TownSvg(parent, town);
  }
  @Override public AbstractPointVisual createPointVisual(GraphPoint point)
  {
    return new PointSvg(parent, point.hexPoint(), parent.getBoardSvg()
            .calculatePosition(point.hexPoint()));
  }
  @Override public AbstractSideVisual createSideVisual(GraphSide side)
  {
    return new SideSvg(parent, side.side());
  }
  @Override public IslandBonusVisual createIslandBonus(IslandBonus islandBonus)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public ShipVisual createShipVisual(Ship ship)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public WallVisual createWallVisual(Wall wall)
  {
    // TODO Auto-generated method stub
    return null;
  }
}