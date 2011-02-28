package soc.common.board.layouts;

import java.util.List;

import soc.common.board.HexChangedEventHandler;
import soc.common.board.HexLocation;
import soc.common.board.hexes.Hex;

import com.google.gwt.event.shared.HandlerRegistration;

public interface HexLayout extends Iterable<Hex>
{
    public Hex get(HexLocation hexLocation);

    public boolean isValid(HexLocation hexLocation);

    public boolean isAtEdge(HexLocation location);

    public HandlerRegistration addHexChangedHandler(
            HexChangedEventHandler handler);

    public List<Hex> getValidNeighbours(Hex hex);

    public void set(HexLocation location, Hex newHex);
}
