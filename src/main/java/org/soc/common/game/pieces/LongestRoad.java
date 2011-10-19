package org.soc.common.game.pieces;

import org.soc.common.core.Props.IsChild;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.*;
import org.soc.common.game.*;
import org.soc.common.game.LongestRoadChangedEvent.LongestRoadChangedHandler;
import org.soc.common.game.board.*;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.views.widgetsInterface.visuals.*;

import com.google.gwt.event.shared.*;

public class LongestRoad
        extends
        AbstractPlayerPiece<Integer>
        implements
        VictoryPointItem {
  private Route route;
  private transient SimpleEventBus eventBus = new SimpleEventBus();

  public Route route() {
    return route;
  }
  public LongestRoad setRoute(Route route) {
    Route oldRoute = route;
    this.route = route;
    this.player = this.route.player();
    eventBus.fireEvent(new LongestRoadChangedEvent(oldRoute, route));
    return this;
  }
  @Override public int victoryPoints() {
    return 2;
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
  public HandlerRegistration addLongestRoadChangedEventHandler(
          LongestRoadChangedHandler handler) {
    return eventBus.addHandler(LongestRoadChangedEvent.getType(), handler);
  }
  @Override public boolean affectsRoad() {
    return false;
  }
  @Override public PieceVisual createPiece(VisualFactory visualFactory) {
    return null;
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
