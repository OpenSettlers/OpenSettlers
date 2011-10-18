package org.soc.common.game.hexes;

import org.soc.common.core.GenericList.HasId;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.*;
import org.soc.common.game.*;
import org.soc.common.game.board.*;
import org.soc.common.game.hexes.ChitChangedEvent.ChitChangedHandler;
import org.soc.common.game.hexes.PortChangedEvent.PortChangedHandler;
import org.soc.common.game.hexes.TerritoryChangedEvent.TerritoryChangedHandler;

import com.google.gwt.event.shared.*;

/** Represents the base type for each hex.
 * @seealso cref="http://www.codeproject.com/KB/cs/hexagonal_part1.aspx"/>
 * @seealso cref="http://gmc.yoyogames.com/index.php?showtopic=336183"/> */
public abstract class AbstractHex implements Hex {
  protected HexLocation hexLocation;
  protected Territory territory;
  protected SimpleEventBus eventBus = new SimpleEventBus();

  @Override public Property getProp(Property type) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public PropertyTypeList properties() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public HexLocation id() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public HasId setId(HexLocation id) {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public IdScope scope() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Territory territory() {
    return territory;
  }
  @Override public Hex setTerritory(Territory t) {
    if (t != territory)
    {
      this.territory = t;
      eventBus.fireEvent(new TerritoryChangedEvent(t));
    }
    return this;
  }
  public AbstractHex() {}
  public Hex setLocation(HexLocation hexLocation) {
    this.hexLocation = hexLocation;
    return this;
  }
  public HexLocation location() {
    return hexLocation;
  }
  @Override public String toString() {
    return this.getClass().toString() + " [hexLocation=" + hexLocation
            + "]";
  }
  @Override public Resource resource() {
    return null;
  }
  @Override public Port port() {
    return null;
  }
  @Override public boolean canHavePort() {
    return false;
  }
  @Override public Hex setPort(Port port) {
    return this;
  }
  @Override public HandlerRegistration addChitChangedHandler(ChitChangedHandler handler) {
    return eventBus.addHandler(ChitChangedEvent.getType(), handler);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
  @Override public HandlerRegistration addPortChangedHandler(PortChangedHandler handler) {
    return eventBus.addHandler(PortChangedEvent.getType(), handler);
  }
  @Override public HandlerRegistration addTerritoryChangedHandler(TerritoryChangedHandler handler) {
    return eventBus.addHandler(TerritoryChangedEvent.getType(), handler);
  }
}