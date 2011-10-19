package org.soc.common.game.pieces;

import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.core.Props.IsChild;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.*;
import org.soc.common.game.*;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.board.*;
import org.soc.common.game.hexes.*;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.game.pieces.Piece.Producer;
import org.soc.common.game.pieces.Piece.StockPiece;
import org.soc.common.views.widgetsInterface.playerInfo.*;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget.StockItemWidgetFactory;
import org.soc.common.views.widgetsInterface.visuals.*;

import static org.soc.common.game.Resources.*;

public class City
        extends
        AbstractPlayerPiece<Integer>
        implements
        VictoryPointItem,
        HasPoint,
        Producer,
        StockPiece,
        OsModel<Integer> {
  private HexPoint pointLocation;

  @Override public ResourceList cost() {
    return newResources(Resource.wheat);
  }
  @Override public boolean canBuild(Board board, GamePlayer player) {
    if (player.stock().cities().size() == 0) // We need a city in stock...
      return false;
    if (player.towns().size() == 0) // And we need a town to replace.
      return false;
    return true;
  }
  /** City is worth 2 victory points */
  @Override public int victoryPoints() {
    return 2;
  }
  public HexPoint point() {
    return pointLocation;
  }
  @Override public HasPoint setPoint(HexPoint point) {
    pointLocation = point;
    return this;
  }
  @Override public boolean isStockPiece() {
    return true;
  }
  @Override public void addTo(GamePlayer player) {
    player.cities().move(player.stock().cities(), this);
    player.pointPieces().add(this);
    player.producers().add(this);
    player.victoryPoints().add(this);
  }
  @Override public void removeFrom(GamePlayer player) {
    // TODO: implement for volcano's
  }
  @Override public ResourceList produce(Hex hex, GameRules rules) {
    assert hex.producesResource();
    return newResources(hex.resource().copy(), hex.resource().copy());
  }
  @Override public boolean affectsRoad() {
    return false;
  }
  @Override public void addToBoard(Board board) {
    board.graph().addCity(this);
  }
  @Override public void removeFromBoard(Board board) {
    // TODO: Implement for volcano's
  }
  @Override public PieceVisual createPiece(VisualFactory visualFactory) {
    return visualFactory.createCityVisual(this);
  }
  @Override public StockItemWidget createStockItemWidget(StockItemWidgetFactory factory) {
    return factory.createCityStockWidget();
  }
  @Override public IsChild getProp(StaticProperty type) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public PropertyTypeList properties() {
    // TODO Auto-generated method stub
    return null;
  }
}