package org.soc.common.game.pieces;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.GameRules;
import org.soc.common.game.Resource.Ore;
import org.soc.common.game.Resource.Wheat;
import org.soc.common.game.ResourceList;
import org.soc.common.game.VictoryPointItem;
import org.soc.common.game.board.Board;
import org.soc.common.game.board.HasPoint;
import org.soc.common.game.board.HexPoint;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.game.pieces.Piece.Producable;
import org.soc.common.game.pieces.Piece.StockPiece;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget.StockItemWidgetFactory;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;
import org.soc.gwt.client.images.R;

public class City extends AbstractPlayerPiece implements VictoryPointItem, HasPoint, Producable,
        StockPiece {
  private HexPoint pointLocation;

  @Override public Icon icon() {
    return new IconImpl(R.icons().city16(), R.icons().city32(), null, R.icons().city48());
  }
  @Override public String getLocalizedName() {
    return I.get().constants().city();
  }
  @Override public String getDescription() {
    return I.get().constants().cityDescription();
  }
  @Override public ResourceList cost() {
    ResourceList result = new ResourceList();
    result.add(new Wheat());
    result.add(new Wheat());
    result.add(new Ore());
    result.add(new Ore());
    result.add(new Ore());
    return result;
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
    player.cities().moveFrom(player.stock().cities(), this);
    player.pointPieces().add(this);
    player.producers().add(this);
    player.victoryPoints().add(this);
  }
  @Override public void removeFrom(GamePlayer player) {
    // TODO: implement for volcano's
  }
  @Override public ResourceList produce(Hex hex, GameRules rules) {
    ResourceList production = new ResourceList();
    production.add(hex.resource().copy());
    production.add(hex.resource().copy());
    return production;
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
}