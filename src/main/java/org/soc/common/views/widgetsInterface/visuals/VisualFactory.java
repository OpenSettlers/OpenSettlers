package org.soc.common.views.widgetsInterface.visuals;

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
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.CityVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.IslandBonusVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.PirateVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.PointVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.RoadVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.RobberVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.ShipVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.SideVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.TownVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.WallVisual;

public interface VisualFactory {
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