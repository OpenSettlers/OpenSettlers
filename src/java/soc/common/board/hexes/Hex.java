package soc.common.board.hexes;

import soc.common.board.HexLocation;
import soc.common.board.territories.Territory;

public interface Hex
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
    
    /*
     * Null of no territory associated, a reference to a territory
     * when this hex is part of a territory
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
    public AbstractHex copy();
}
