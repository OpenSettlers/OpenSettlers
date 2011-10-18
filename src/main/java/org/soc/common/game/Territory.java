package org.soc.common.game;

import org.soc.common.core.GenericList.HasId;
import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.core.property.*;
import org.soc.common.game.Chits.ChitList;
import org.soc.common.game.Chits.ChitListImpl;
import org.soc.common.game.Ports.MutablePortList;
import org.soc.common.game.Ports.MutablePortListImpl;
import org.soc.common.game.Ports.PortList;
import org.soc.common.game.hexes.Hexes.MutableHexList;
import org.soc.common.game.hexes.Hexes.MutableHexListImpl;
import org.soc.common.views.meta.*;

/** Represents a group of hexes on a Board. Each hex is assigned a territory.
 * 
 * At playtime, territories enable Trade Routes, bonus island vicotory points, and chit swapping. At
 * designtime, territories keep lists of chits, ports and hexes for LayoutStrategies, to transform
 * the designtime board to a playable board. */
public interface Territory extends OsModel<Integer> {
  public interface Supported {
    public final static MainLand mainland = new MainLand();
    public final static Island island = new Island();
  }

  public boolean isBonus();
  public Territory setBonus(boolean isBonus);
  public PortList ports();
  public Territory setPorts(PortList ports);
  public void setName(String name);
  public boolean isMainland();
  public Territory setMainland(boolean isMainland);
  public boolean isIsland();
  public Territory setIsland(boolean isIsland);
  public ChitList chits();
  public Territory setChits(ChitList chits);
  public MutableHexList hexes();
  public Port grabPort(Random random, PortList supportedPorts);
  public Territory copy();

  /** Represents a group of LandHexes. A territory is useful for: - Trade routes - Chit swapping -
   * Bonus island VPs */
  public abstract class TerritoryImpl implements Territory {
    private static final long serialVersionUID = 4568924008718927137L;
    private String name;

    public String getName() {
      return name;
    }

    private int ID;
    private boolean isMainland;
    private boolean isIsland;
    // Whether or not a bonus vp can be earned while building on it
    private boolean isBonus;

    public boolean isBonus() {
      return isBonus;
    }

    // A territory may have a list of ports associated with them.
    // These may be placed by players in a PlacePortGamePhase,
    // or before start by the server, to replace randomports with.
    private MutablePortList ports = new MutablePortListImpl();
    /* List of chits for this territory, used at board generation time */
    private ChitList chits = new ChitListImpl();
    /* List of hexes belonging to this territory. Used for: - Board generation time to replace
     * RandomHexes with - DiscoveryHexes to replace DiscoveryHexes with */
    private MutableHexList hexes = new MutableHexListImpl();

    public Territory setBonus(boolean isBonus) {
      this.isBonus = isBonus;
      return this;
    }
    public PortList ports() {
      return ports;
    }
    public Territory setPorts(PortList ports) {
      this.ports = new MutablePortListImpl(ports);
      return this;
    }
    public void setName(String name) {
      this.name = name;
    }
    public boolean isMainland() {
      return isMainland;
    }
    public TerritoryImpl setMainland(boolean isMainland) {
      this.isMainland = isMainland;
      isIsland = !isMainland();
      return this;
    }
    public boolean isIsland() {
      return isIsland;
    }
    public Territory setIsland(boolean isIsland) {
      this.isIsland = isIsland;
      isIsland = !isMainland();
      return this;
    }
    @Override public ChitList chits() {
      return chits;
    }
    @Override public MutableHexList hexes() {
      return hexes;
    }
    /* Returns and removes a port from the internal list of ports. When no ports are available,
     * picks a random port chosen from a list of supported port types */
    @Override public Port grabPort(Random random, PortList supportedPorts) {
      if (ports.size() > 0) {
        Port p = ports.get(random.nextInt(ports.size(), false));
        ports.remove(p);
        return p;
      } else {
        return supportedPorts.get(
                random.nextInt(supportedPorts.size(), false)).copy();
      }
    }
    @Override public Territory setChits(ChitList chits) {
      this.chits = chits;
      return this;
    }
    @Override public Name name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Description description() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Integer id() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public HasId setId(Integer id) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public org.soc.common.core.GenericList.HasId.IdScope scope() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class MainLand extends TerritoryImpl {
    @Override public MainLand copy() {
      return new MainLand();
    }
    @Override public boolean isMainland() {
      return true;
    }
    @Override public Property getProp(Property type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Description description() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class Island extends TerritoryImpl {
    @Override public Island copy() {
      return new Island();
    }
    @Override public Property getProp(Property type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Description description() {
      // TODO Auto-generated method stub
      return null;
    }
  }
}
