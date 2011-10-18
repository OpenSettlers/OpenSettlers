package org.soc.common.game.pieces;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.board.*;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.visuals.*;

public class IslandBonus extends AbstractPlayerPiece<Integer> implements VictoryPointItem {
  private Territory territory;
  private HexPoint location;

  @Override public Icon icon() {
    return IconImpl.nullIcon();
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().islandBonus());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().islandBonusDescription());
  }
  public HexPoint getLocation() {
    return location;
  }
  public Territory getTerritory() {
    return territory;
  }
  public IslandBonus setTerritory(Territory territory) {
    this.territory = territory;
    return this;
  }
  @Override public int victoryPoints() {
    return 1;
  }
  @Override public boolean isStockPiece() {
    return false;
  }
  @Override public void addTo(GamePlayer player) {
    player.victoryPoints().add(this);
  }
  @Override public void removeFrom(GamePlayer player) {
    player.victoryPoints().remove(this);
  }
  @Override public boolean affectsRoad() {
    return false;
  }
  @Override public PieceVisual createPiece(VisualFactory visualFactory) {
    return visualFactory.createIslandBonus(this);
  }
}
