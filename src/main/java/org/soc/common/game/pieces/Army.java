package org.soc.common.game.pieces;

import java.util.*;

import org.soc.common.game.DevelopmentCard.Soldier;
import org.soc.common.game.*;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.views.widgetsInterface.visuals.*;

import com.google.gwt.event.shared.*;

public class Army extends AbstractPlayerPiece<Integer> implements VictoryPointItem {
  public static Army LARGESTARMY = new Army();
  private List<Soldier> soldiers = new ArrayList<Soldier>();
  private boolean isLargest = false;
  private transient SimpleEventBus eventBus = new SimpleEventBus();

  public List<Soldier> getSoldiers() {
    return soldiers;
  }
  @Override public int victoryPoints() {
    return 2;
  }
  public int amountSoldiers() {
    return soldiers.size();
  }
  public boolean isBiggerThen(Army army) {
    return soldiers.size() > army.getSoldiers().size();
  }
  public void addSoldier(Soldier soldier) {
    boolean wasLargest = isLargest;
    int oldSoldierAmount = amountSoldiers();
    soldiers.add(soldier);
    ArmyChangedEvent event = new ArmyChangedEvent(amountSoldiers(),
            isLargest, wasLargest, oldSoldierAmount, null, soldier);
    eventBus.fireEvent(event);
  }
  @Override public boolean isStockPiece() {
    return false;
  }
  public Army setLargest(boolean isLargest) {
    this.isLargest = isLargest;
    return this;
  }
  public boolean isLargest() {
    return isLargest;
  }
  @Override public void addTo(GamePlayer player) {
    player.victoryPoints().add(this);
    setLargest(true);
  }
  @Override public void removeFrom(GamePlayer player) {
    player.victoryPoints().remove(this);
    setLargest(false);
  }
  @Override public boolean affectsRoad() {
    return false;
  }
  public HandlerRegistration addSoldiersChangedEventHandler(ArmyChangedEventHandler handler) {
    return eventBus.addHandler(ArmyChangedEvent.TYPE, handler);
  }
  @Override public PieceVisual createPiece(VisualFactory visualFactory) {
    return null;
  }

  public interface ArmyChangedEventHandler extends EventHandler {
    public void onArmyChanged(ArmyChangedEvent event);
  }

  public static class ArmyChangedEvent extends GwtEvent<ArmyChangedEventHandler> {
    public static Type<ArmyChangedEventHandler> TYPE = new Type<ArmyChangedEventHandler>();
    private int newSoldierAmount;
    private boolean isLargest;
    private boolean wasLargest;
    private int oldSoldierAmount;
    private Soldier addedSoldier;
    private Soldier removedSoldier;

    public ArmyChangedEvent(int newSoldierAmount, boolean isLargest,
            boolean wasLargest, int oldSoldierAmount, Soldier removedSoldier,
            Soldier addedSoldier) {
      super();
      this.newSoldierAmount = newSoldierAmount;
      this.isLargest = isLargest;
      this.wasLargest = wasLargest;
      this.oldSoldierAmount = oldSoldierAmount;
      this.removedSoldier = removedSoldier;
      this.addedSoldier = addedSoldier;
    }
    public int getNewSoldierAmount() {
      return newSoldierAmount;
    }
    public boolean isLargest() {
      return isLargest;
    }
    public boolean isWasLargest() {
      return wasLargest;
    }
    public int getOldSoldierAmount() {
      return oldSoldierAmount;
    }
    public Soldier getAddedSoldier() {
      return addedSoldier;
    }
    public Soldier getRemovedSoldier() {
      return removedSoldier;
    }
    @Override protected void dispatch(ArmyChangedEventHandler handler) {
      handler.onArmyChanged(this);
    }
    @Override public Type<ArmyChangedEventHandler> getAssociatedType() {
      return TYPE;
    }
  }
}
