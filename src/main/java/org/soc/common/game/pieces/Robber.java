package org.soc.common.game.pieces;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.board.*;
import org.soc.common.game.pieces.MovedEvent.HasMovedHandlers;
import org.soc.common.game.pieces.MovedEvent.MovedHandler;
import org.soc.common.game.pieces.Piece.AbstractPiece;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.visuals.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.shared.*;

public class Robber extends AbstractPiece implements HasMovedHandlers {
  private HexLocation location;
  private transient SimpleEventBus eventBus = new SimpleEventBus();

  @Override public Icon icon() {
    return new IconImpl(R.icons().robber16(), R.icons().robber32(), R.icons().robber48());
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().robber());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().robberDescription());
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
