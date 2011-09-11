package org.soc.common.game.hexes;

import org.soc.common.board.hexes.PortChangedEvent;
import org.soc.common.board.hexes.PortChangedEvent.PortChangedHandler;
import org.soc.common.game.Port;
import org.soc.common.game.Resource;
import org.soc.common.game.Territory;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.hexes.ChitChangedEvent.ChitChangedHandler;
import org.soc.common.game.hexes.TerritoryChangedEvent.TerritoryChangedHandler;
import org.soc.common.utils.Util;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

/** Represents the base type for each hex.
 * @seealso cref="http://www.codeproject.com/KB/cs/hexagonal_part1.aspx"/>
 * @seealso cref="http://gmc.yoyogames.com/index.php?showtopic=336183"/> */
public abstract class AbstractHex implements Hex {
  private static final long serialVersionUID = -1737108038985903164L;
  protected HexLocation hexLocation;
  protected Territory territory;
  protected SimpleEventBus eventBus = new SimpleEventBus();
  protected String name;

  @Override public String name() {
    return Util.shortName(this.getClass());
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
  public String getName() {
    return name;
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