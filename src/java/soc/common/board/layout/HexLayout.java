package soc.common.board.layout;

import java.io.Serializable;
import java.util.List;

import soc.common.board.HexChangedEventHandler;
import soc.common.board.hexes.Hex;

import com.google.gwt.event.shared.HandlerRegistration;

/*
 * Represents the board data structure.
 * 
 * A board is made up of hexes in a 2D matrix. The even rows of the matrix
 * have an indentation length on the left side half the width of a hex.
 * For example, a 5x5 sized board will have the layout of:
 * 
 * <code>
 * 
 * width
 * 0 1 2 3 4
 * |H| |H| |H| |H| |H| 0
 * |H| |H| |H| |H| |H| 1
 * |H| |H| |H| |H| |H| 2
 * |H| |H| |H| |H| |H| 3
 * |H| |H| |H| |H| |H| 4
 * height
 * </code>
 * 
 * 
 * Other possible layouts (which are currently not supported):
 * 
 * <code>
 * |H| |H| |H| |H| |H| 0
 * |H| |H| |H| |H| 1
 * |H| |H| |H| |H| |H| 2
 * |H| |H| |H| |H| 3
 * |H| |H| |H| |H| |H| 4
 * </code>
 * 
 * 
 * <code>
 * |H| |H| |H| |H| 0
 * |H| |H| |H| |H| |H| 1
 * |H| |H| |H| |H| 2
 * |H| |H| |H| |H| |H| 3
 * |H| |H| |H| |H| 4
 * </code>
 * 
 * 
 * <code>
 * |H| |H| |H| |H| |H| 0
 * |H| |H| |H| |H| 1
 * |H| |H| |H| |H| |H| 2
 * |H| |H| |H| |H| 3
 * |H| |H| |H| |H| |H| 4
 * </code>
 * 
 * 
 * <code>
 * |H| |H| |H| |H| 0
 * |H| |H| |H| |H| 1
 * |H| |H| |H| |H| 2
 * |H| |H| |H| |H| 3
 * |H| |H| |H| |H| 4
 * </code>
 * 
 * 
 * <code>
 * |H| |H| |H| |H| |H| 0
 * |H| |H| |H| |H| 1
 * |H| |H| |H| |H| |H| 2
 * |H| |H| |H| |H| 3
 * |H| |H| |H| |H| |H| 4
 * </code>
 */
public interface HexLayout extends Iterable<Hex>, Serializable
{
    public Hex get(HexLocation hexLocation);

    public boolean isValid(HexLocation hexLocation);

    public boolean isAtEdge(HexLocation location);

    public HandlerRegistration addHexChangedHandler(
                    HexChangedEventHandler handler);

    public List<Hex> getValidNeighbours(Hex hex);

    public void set(HexLocation location, Hex newHex);
}
