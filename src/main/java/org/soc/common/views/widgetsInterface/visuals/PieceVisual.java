package org.soc.common.views.widgetsInterface.visuals;

import org.soc.common.game.*;
import org.soc.common.game.board.*;
import org.soc.common.game.hexes.ChitChangedEvent.ChitChangedHandler;
import org.soc.common.game.hexes.*;
import org.soc.common.game.hexes.PortChangedEvent.PortChangedHandler;
import org.soc.common.game.hexes.TerritoryChangedEvent.TerritoryChangedHandler;
import org.soc.common.game.pieces.*;

/** Base interface of every visual representation of a piece Every piece on the board should be able
 * to be selected, enabled and visibility toggled on/off. */
public interface PieceVisual {
  public PieceVisual setSelected(boolean selected);
  public boolean isSelected();
  public PieceVisual setEnabled(boolean enabled);
  public boolean isEnabled();
  public PieceVisual setVisible(boolean visible);
  public boolean isVisible();
  public ChitVisual getChitVisual();
  public CityVisual getCityVisual();
  public HexVisual hexVisual();
  public PointVisual getPointVisual();
  public SideVisual sideVisual();
  public IslandBonusVisual getIslandBonusVisual();
  public PirateVisual getPirateVisual();
  public PortVisual portVisual();
  public RoadVisual getRoadVisual();
  public RobberVisual getRobberVisual();
  public ShipVisual getShipVisual();
  public TerritoryVisual getTerritoryVisual();
  public TownVisual getTownVisual();
  public WallVisual getWallVisual();

  public interface ChitVisual extends PieceVisual {
    public Chit getChit();
    public ChitVisual setChit(Chit chit);
  }

  public interface PortVisual extends PieceVisual {
    public PortVisual setPort(Port port);
    public Port getPort();
    // A PortVisual may showinvalid status, if the port is not allowed to be built
    // on the selected spot.
    public PortVisual setValid(boolean valid);
    public boolean isValid();
  }

  public interface CityVisual extends PieceVisual {
    public City getCity();
  }

  public interface PirateVisual extends PieceVisual {
    public Pirate getPirate();
  }

  public interface IslandBonusVisual extends PieceVisual {}

  public interface PointVisual extends PieceVisual {
    public HexPoint getHexPoint();
    public void addPieceVisual(PieceVisual pieceVisual);
  }

  public interface RoadVisual extends PieceVisual {
    public Road getRoad();
  }

  public interface RobberVisual extends PieceVisual {
    public Robber getRober();
  }

  public interface TownVisual extends PieceVisual {
    public Town getTown();
  }

  public interface ShipVisual extends PieceVisual {}

  public interface SideVisual extends PieceVisual {
    public HexSide getHexSide();
  }

  public interface TerritoryVisual extends PieceVisual {
    public TerritoryVisual setTerritory(Territory territory);
  }

  /** Visual interface for 6 triangles depicting a port possibility for a seahex */
  public interface PortPossibilitiesVisual extends PieceVisual {
    public void updatePossibilities();
  }

  public interface HexVisual extends PieceVisual, ChitChangedHandler,
          TerritoryChangedHandler, PortChangedHandler {
    public HexVisual setHex(Hex hex);
    public HexVisual setDarkened(boolean darkened);
    public Hex hex();
    public TerritoryVisual getTerritory();
    public ChitVisual getChitVisual();
    public PortPossibilitiesVisual getPortPossibilitiesVisual();
    public BoardVisual getParent();
  }

  public interface WallVisual extends PieceVisual
  {}
}
