package org.soc.common.game.pieces;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.GameRules;
import org.soc.common.game.Resource.Sheep;
import org.soc.common.game.Resource.Timber;
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

public class Town extends AbstractPlayerPiece implements VictoryPointItem,
        HasPoint, Producable, StockPiece {
  private HexPoint pointLocation;

  @Override public Icon icon() {
    return new IconImpl(R.icons().town16(), R.icons().town32(), R.icons().town48());
  }
  @Override public String getLocalizedName() {
    return I.get().constants().town();
  }
  @Override public String getDescription() {
    return I.get().constants().townDescription();
  }
  @Override public String toString() {
    return "Town";
  }
  @Override public ResourceList cost() {
    ResourceList result = new ResourceList();
    result.add(new Timber());
    result.add(new Wheat());
    result.add(new Sheep());
    return result;
  }
  @Override public boolean canBuild(Board board, GamePlayer player) {
    // We need a town in stock...
    if (player.stock().towns().size() == 0)
      return false;
    return true;
  }
  @Override public int victoryPoints() {
    return 1;
  }
  @Override public HexPoint point() {
    return pointLocation;
  }
  @Override public HasPoint setPoint(HexPoint point) {
    this.pointLocation = point;
    return this;
  }
  @Override public boolean isStockPiece() {
    return true;
  }
  @Override public void addTo(GamePlayer player) {
    player.towns().moveFrom(player.stock().towns(), this);
    player.pointPieces().add(this);
    player.producers().add(this);
    player.victoryPoints().add(this);
  }
  @Override public void removeFrom(GamePlayer player) {
    player.stock().towns().moveFrom(player.towns(), this);
    player.pointPieces().remove(this);
    player.producers().remove(this);
    player.victoryPoints().remove(this);
  }
  @Override public ResourceList produce(Hex hex, GameRules rules) {
    ResourceList production = new ResourceList();
    production.add(hex.resource().copy());
    return production;
  }
  @Override public boolean affectsRoad() {
    return true;
  }
  @Override public void addToBoard(Board board) {
    board.graph().addTown(this);
  }
  @Override public void removeFromBoard(Board board) {}
  @Override public PieceVisual createPiece(VisualFactory visualFactory) {
    return visualFactory.createTownVisual(this);
  }
  @Override public StockItemWidget createStockItemWidget(StockItemWidgetFactory factory) {
    return factory.createTownStockWidget();
  }
}
