package org.soc.common.game;

import java.io.Serializable;

import org.soc.common.game.hexes.HexList;

/** Represents a group of hexes on a Board. Each hex is assigned a territory.
 * 
 * At playtime, territories enable Trade Routes, bonus island vicotory points, and chit swapping. At
 * designtime, territories keep lists of chits, ports and hexes for LayoutStrategies, to transform
 * the designtime board to a playable board. */
public interface Territory extends Serializable {
  public boolean isBonus();
  public Territory setBonus(boolean isBonus);
  public PortList ports();
  public Territory setPorts(PortList ports);
  public void setName(String name);
  public int id();
  public Territory setID(int iD);
  public boolean isMainland();
  public Territory setMainland(boolean isMainland);
  public boolean isIsland();
  public Territory setIsland(boolean isIsland);
  public ChitList chits();
  public Territory setChits(ChitList chits);
  public HexList hexes();
  public Port grabPort(Random random, PortList supportedPorts);

  /* Represents a group of LandHexes. A territory is useful for: - Trade routes - Chit swapping -
   * Bonus island VPs */
  public class TerritoryImpl implements Territory {
    private static final long serialVersionUID = 4568924008718927137L;
    private String name;
    private int ID;
    private boolean isMainland;
    private boolean isIsland;
    // Whether or not a bonus vp can be earned while building on it
    private boolean isBonus;
    // A territory may have a list of ports associated with them.
    // These may be placed by players in a PlacePortGamePhase,
    // or before start by the server, to replace randomports with.
    private PortList ports = new PortList();
    /* List of chits for this territory, used at board generation time */
    private ChitList chits = new ChitList();
    /* List of hexes belonging to this territory. Used for: - Board generation time to replace
     * RandomHexes with - DiscoveryHexes to replace DiscoveryHexes with */
    private HexList hexes = new HexList();

    public boolean isBonus() {
      return isBonus;
    }
    public Territory setBonus(boolean isBonus) {
      this.isBonus = isBonus;
      return this;
    }
    public String getName() {
      return name;
    }
    public PortList ports() {
      return ports;
    }
    public Territory setPorts(PortList ports) {
      this.ports = ports;
      return this;
    }
    public void setName(String name) {
      this.name = name;
    }
    public int id() {
      return ID;
    }
    public Territory setID(int iD) {
      ID = iD;
      return this;
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
    @Override public HexList hexes() {
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
  }
}
