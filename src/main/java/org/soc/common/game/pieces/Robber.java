package org.soc.common.game.pieces;

import org.soc.common.board.pieces.MovedEvent;
import org.soc.common.board.pieces.MovedEvent.HasMovedHandlers;
import org.soc.common.board.pieces.MovedEvent.MovedHandler;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.pieces.Piece.AbstractPiece;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class Robber extends AbstractPiece implements HasMovedHandlers {
  private HexLocation location;
  private transient SimpleEventBus eventBus = new SimpleEventBus();

  @Override public Icon icon() {
    return new IconImpl(R.icons().robber16(), R.icons().robber32(), R.icons().robber48());
  }
  @Override public String getLocalizedName() {
    return I.get().constants().robber();
  }
  @Override public String getDescription() {
    return I.get().constants().robberDescription();
  }
  public Robber(HexLocation hexLocation) {
    this.location = hexLocation;
  }
  public Robber() {}
  public HexLocation location() {
    return location;
  }
  public Robber setLocation(HexLocation location) {
    if (!this.location.equals(location)) {
      HexLocation oldLocation = this.location;
      this.location = location;
      eventBus.fireEvent(new MovedEvent(location, oldLocation));
    }
    return this;
  }
  @Override public PieceVisual createPiece(VisualFactory visualFactory) {
    return visualFactory.createRobberVisual(this);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    // TODO Auto-generated method stub
  }
  @Override public HandlerRegistration addMovedHandler(MovedHandler handler) {
    return eventBus.addHandler(MovedEvent.getType(), handler);
  }
}
