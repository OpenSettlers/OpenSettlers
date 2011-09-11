package org.soc.common.game.hexes;

import java.io.Serializable;

import org.soc.common.board.hexes.PortChangedEvent.HasPortChangedHandlers;
import org.soc.common.game.Chit;
import org.soc.common.game.Port;
import org.soc.common.game.Resource;
import org.soc.common.game.Territory;
import org.soc.common.game.board.HasLocation;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.hexes.ChitChangedEvent.HasChitChangedHandlers;
import org.soc.common.game.hexes.TerritoryChangedEvent.HasTerritoryChangedHandlers;
import org.soc.common.views.meta.Meta;

import com.google.gwt.resources.client.ImageResource;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Optional;
import com.gwtplatform.dispatch.annotation.Order;

public interface Hex extends Meta, HasLocation, Serializable, HasChitChangedHandlers,
        HasPortChangedHandlers, HasTerritoryChangedHandlers {
  /** Literal non-translatable name of this Hex */
  public String getName();
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
