package org.soc.common.game.hexes;

import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.game.*;
import org.soc.common.game.board.*;
import org.soc.common.game.hexes.ChitChangedEvent.HasChitChangedHandlers;
import org.soc.common.game.hexes.PortChangedEvent.HasPortChangedHandlers;
import org.soc.common.game.hexes.TerritoryChangedEvent.HasTerritoryChangedHandlers;

import com.google.gwt.resources.client.*;
import com.gwtplatform.dispatch.annotation.*;

public interface Hex
        extends
        OsModel<HexLocation>,
        HasLocation,
        HasChitChangedHandlers,
        HasPortChangedHandlers,
        HasTerritoryChangedHandlers {
  public interface Supported {
    public static final WheatHex wheatHex = new WheatHex();
    public static final TimberHex timberHex = new TimberHex();
    public static final OreHex oreHex = new OreHex();
    public static final ClayHex clayHex = new ClayHex();
    public static final SheepHex sheepHex = new SheepHex();
    public static final RandomHex randomHex = new RandomHex();
    public static final DesertHex desertHex = new DesertHex();
    public static final SeaHex seaHex = new SeaHex();
    public static final DiamondHex diamondHex = new DiamondHex();
    public static final DiscoveryHex discoveryHex = new DiscoveryHex();
    public static final NoneHex noneHex = new NoneHex();
  }

  /** Literal non-translatable name of this Hex */
  public String getColor();
  public HexLocation location();
  public Hex setLocation(HexLocation location);
  /* Null of no territory associated, a reference to a territory when this hex is part of a
   * territory */
  public Territory territory();
  public Hex setTerritory(Territory territory);
  /* True if a "land piece" may build on this hex */
  public boolean canBuildOnLand();
  /* True if a "sea piece" can build on this hex */
  public boolean canBuildOnSea();
  /* Should the hex be included in the game board graph? */
  public boolean isPartOfGame();
  /* Can a robber be placed on this hex? */
  public boolean isRobberPlaceable();
  /* Can a pirate be placed on this hex? */
  public boolean isPiratePlaceable();
  /* Return a copy of itself */
  public Hex copy();
  /* Returns true when this hex produces resource(s) */
  public boolean producesResource();
  /* Does this hex have a chit? */
  public boolean hasChit();
  /* Returns associated resource of the hex, null if the hex does not support a resource */
  public Resource resource();
  /* Can a chit be put on this hex? */
  public boolean canHaveChit();
  /* Returns the chit of this hex, null if hex does not have a chit */
  public Chit chit();
  /* Sets the chit of this hex to given when hex supports chits */
  public Hex setChit(Chit chit);
  public boolean canHavePort();
  public Port port();
  public Hex setPort(Port port);
  public ImageResource texture();

  @GenEvent public class ChitChanged {
    @Order(0) Chit chit;
    @Order(1) @Optional boolean check;
  }

  @GenEvent public class PortChanged {
    @Order(0) Port port;
  }

  @GenEvent public class TerritoryChanged {
    @Order(0) Territory territory;
  }
}
