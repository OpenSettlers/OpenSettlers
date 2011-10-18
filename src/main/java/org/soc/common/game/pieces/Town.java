package org.soc.common.game.pieces;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.board.*;
import org.soc.common.game.hexes.*;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.game.pieces.Piece.Producer;
import org.soc.common.game.pieces.Piece.StockPiece;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.playerInfo.*;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget.StockItemWidgetFactory;
import org.soc.common.views.widgetsInterface.visuals.*;
import org.soc.gwt.client.images.*;

import static org.soc.common.game.Resource.*;

import static org.soc.common.game.Resources.*;

public class Town extends AbstractPlayerPiece<Integer> implements VictoryPointItem,
        HasPoint, Producer, StockPiece {
  private HexPoint pointLocation;

  @Override public Icon icon() {
    return new IconImpl(R.icons().town16(), R.icons().town32(), R.icons().town48());
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().town());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().townDescription());
  }
  @Override public String toString() {
    return "Town";
  }
  @Override public ResourceList cost() {
    return newResources(Resource.wheat, ore, sheep);
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
    player.towns().move(player.stock().towns(), this);
    player.pointPieces().add(this);
    player.producers().add(this);
    player.victoryPoints().add(this);
  }
  @Override public void removeFrom(GamePlayer player) {
    player.stock().towns().move(player.towns(), this);
    player.pointPieces().remove(this);
    player.producers().remove(this);
    player.victoryPoints().remove(this);
  }
  @Override public ResourceList produce(Hex hex, GameRules rules) {
    if (hex.producesResource())
      return newResources(hex.resource().copy());
    return emptyResources();
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
