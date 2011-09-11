package org.soc.common.game.pieces;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.LongestRoadChangedEvent;
import org.soc.common.game.LongestRoadChangedEvent.LongestRoadChangedHandler;
import org.soc.common.game.VictoryPointItem;
import org.soc.common.game.board.Route;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class LongestRoad extends AbstractPlayerPiece implements VictoryPointItem {
  private Route route;
  private transient SimpleEventBus eventBus = new SimpleEventBus();

  @Override public Icon icon() {
    return new IconImpl(R.icons().longestRoad16(),
            R.icons().longestRoad32(), null, R
                    .icons().longestRoad48());
  }
  @Override public String name() {
    return "LongestRoad";
  }
  @Override public String getLocalizedName() {
    return I.get().constants().longestRoad();
  }
  @Override public String getDescription() {
    return I.get().constants().longestRoadDescription();
  }
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
}
