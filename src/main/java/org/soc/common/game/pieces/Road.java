package org.soc.common.game.pieces;

import org.soc.common.core.Props.IsChild;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.*;
import org.soc.common.game.*;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.board.*;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.game.pieces.Piece.StockPiece;
import org.soc.common.views.widgetsInterface.playerInfo.*;
import org.soc.common.views.widgetsInterface.playerInfo.StockItemWidget.StockItemWidgetFactory;
import org.soc.common.views.widgetsInterface.visuals.*;

import static org.soc.common.game.Resource.*;

import static org.soc.common.game.Resources.*;

public class Road extends AbstractPlayerPiece<Integer> implements HasSide, StockPiece {
  private HexSide sideLocation;

  @Override public ResourceList cost() {
    return newResources(timber, clay);
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
    return (player.equals(graphPoint.player())
            || graphPoint.player() == null) && otherPiece.connectsWithRoad();
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
  @Override public IsChild getProp(StaticProperty type) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public PropertyTypeList properties() {
    // TODO Auto-generated method stub
    return null;
  }
}
