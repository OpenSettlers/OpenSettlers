package org.soc.common.game.pieces;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.Territory;
import org.soc.common.game.VictoryPointItem;
import org.soc.common.game.board.HexPoint;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;

public class IslandBonus extends AbstractPlayerPiece implements VictoryPointItem {
  private Territory territory;
  private HexPoint location;

  @Override public Icon icon() {
    return IconImpl.nullIcon();
  }
  @Override public String getLocalizedName() {
    return I.get().constants().islandBonus();
  }
  @Override public String getDescription() {
    return I.get().constants().islandBonusDescription();
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
