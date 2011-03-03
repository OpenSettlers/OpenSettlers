package soc.common.board.hexes;

import soc.common.board.HexLocation;
import soc.common.board.chits.Chit;
import soc.common.board.layouts.HasLocation;
import soc.common.board.ports.Port;
import soc.common.board.ports.PortChangedEventHandler;
import soc.common.board.resources.Resource;
import soc.common.board.territories.Territory;
import soc.common.views.meta.HasMeta;

import com.google.gwt.event.shared.HandlerRegistration;

public interface Hex extends HasMeta, HasLocation
{
    /*
     * Literal non-translatable name of this Hex
     */
    public String getName();

    /*
     * Color of this hex
     */
    public String getColor();

    /*
     * Location in the grid of hexes
     */
    public HexLocation getLocation();

    public Hex setLocation(HexLocation location);

    /*
     * Null of no territory associated, a reference to a territory when this hex
     * is part of a territory
     */
    public Territory getTerritory();

    public Hex setTerritory(Territory territory);

    /*
     * True if a "land piece" may build on this hex
     */
    public boolean isBuildableLand();

    /*
     * True if a "sea piece" can build on this hex
     */
    public boolean isBuildableSea();

    /*
     * Should the hex be included in the game board graph?
     */
    public boolean isPartOfGame();

    /*
     * Can a robber be placed on this hex?
     */
    public boolean isRobberPlaceable();

    /*
     * Can a pirate be placed on this hex?
     */
    public boolean isPiratePlaceable();

    /*
     * Return a copy of itself
     */
    public Hex copy();

    /*
     * Returns true when this hex produces resource(s)
     */
    public boolean canHaveResource();

    /*
     * Does this hex have a chit?
     */
    public boolean hasChit();

    /*
     * Returns associated resource of the hex, null if the hex does not support
     * a resource
     */
    public Resource getResource();

    /*
     * Can a chit be put on this hex?
     */
    public boolean canHaveChit();

    /*
     * Returns the chit of this hex, null if hex does not have a chit
     */
    public Chit getChit();

    /*
     * Sets the chit of this hex to given when hex supports chits
     */
    public Hex setChit(Chit chit);

    public boolean canHavePort();

    public Port getPort();

    public Hex setPort(Port port);

    public HandlerRegistration addChitChangedEventHandler(
            ChitChangedEventHandler handler);

    public HandlerRegistration addPortChangedEventHandler(
            PortChangedEventHandler handler);

    public HandlerRegistration addTerritoryChangedEventHandler(
            TerritoryChangedEventHandler handler);
}
