package org.soc.common.game.pieces;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.Resource.Clay;
import org.soc.common.game.Resource.Timber;
import org.soc.common.game.ResourceList;
import org.soc.common.game.board.Board;
import org.soc.common.game.board.GraphPoint;
import org.soc.common.game.board.HasSide;
import org.soc.common.game.board.HexSide;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.game.pieces.Piece.StockPiece;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget.StockItemWidgetFactory;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;
import org.soc.gwt.client.images.R;

public class Road extends AbstractPlayerPiece implements HasSide, StockPiece {
  private HexSide sideLocation;

  @Override public Icon icon() {
    return new IconImpl(R.icons().road16(), R
            .icons().road32(), R.icons().road48());
  }
  @Override public String name() {
    return "Road";
  }
  @Override public String getLocalizedName() {
    return I.get().constants().longestRoad();
  }
  @Override public String getDescription() {
    return I.get().constants().longestRoadDescription();
  }
  @Override public String toString() {
    return "Road";
  }
  @Override public ResourceList cost() {
    ResourceList result = new ResourceList();
    result.add(new Timber());
    result.add(new Clay());
    return result;
  }
  @Override public boolean canBuild(Board board, GamePlayer player) {
    if (player.stock().roads().size() == 0)
      return false;
    return true;
  }
  @Override public HexSide getSide() {
    return sideLocation;
  }
  /** True when the player has enough resources to pay for the road or he has one or more road tokens */
  @Override public boolean canPay(GamePlayer player) {
    return super.canPay(player) || player.roadBuildingTokens() > 0;
  }
  @Override public boolean isStockPiece() {
    return true;
  }
  @Override public HasSide setSide(HexSide side) {
    sideLocation = side;
    return null;
  }
  @Override public void addTo(GamePlayer player) {
    player.roads().add(this);
    player.sidePieces().add(this);
    player.stock().roads().remove(this);
  }
  @Override public void removeFrom(GamePlayer player) {}
  @Override public boolean affectsRoad() {
    return true;
  }
  @Override public void addToBoard(Board board) {
    board.graph().addRoad(this);
  }
  @Override public void removeFromBoard(Board board) {}
  @Override public boolean canConnect(GraphPoint graphPoint, HasSide otherPiece) {
    return (player.equals(graphPoint.player()) || graphPoint.player() == null)
            && otherPiece.connectsWithRoad();
  }
  @Override public boolean connectsWithBridge() {
    return true;
  }
  @Override public boolean connectsWithRoad() {
    return true;
  }
  @Override public boolean connectsWithShip() {
    return false;
  }
  @Override public PieceVisual createPiece(VisualFactory visualFactory) {
    return visualFactory.createRoadVisual(this);
  }
  @Override public StockItemWidget createStockItemWidget(StockItemWidgetFactory factory) {
    return factory.createRoadStockWidget();
  }
}
