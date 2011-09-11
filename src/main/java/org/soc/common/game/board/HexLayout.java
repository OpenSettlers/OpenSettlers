package org.soc.common.game.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.board.HexChangedEvent;
import org.soc.common.board.HexChangedEvent.HasHexChangedHandlers;
import org.soc.common.board.HexChangedEvent.HexChangedHandler;
import org.soc.common.game.hexes.Hex;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

/* Represents the board data structure.
 * 
 * A board is made up of hexes in a 2D matrix. The even rows of the matrix
 * have an indentation length on the left side half the width of a hex.
 * For example, a 5x5 sized board will have the layout of:
 * 
 * <code>
 * 
 * width
 *    0   1   2   3   4
 *   |H| |H| |H| |H| |H| 0
 * |H| |H| |H| |H| |H|   1
 *   |H| |H| |H| |H| |H| 2
 * |H| |H| |H| |H| |H|   3
 *   |H| |H| |H| |H| |H| 4
 * height
 * </code>
 * 
 * 
 * Other possible layouts (which are currently not supported):
 * 
 * <code>
 * |H| |H| |H| |H| |H| 0
 *   |H| |H| |H| |H| 1
 * |H| |H| |H| |H| |H| 2
 *   |H| |H| |H| |H| 3
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
public interface HexLayout extends Iterable<Hex>, Serializable, HasHexChangedHandlers
{
  public Hex get(HexLocation hexLocation);
  public boolean isValid(HexLocation hexLocation);
  public boolean isAtEdge(HexLocation location);
  public List<Hex> validNeighbours(Hex hex);
  public void set(HexLocation location, Hex newHex);
  public void add(Hex hex);
  public Hex get(int w, int h);
  public int height();
  public int width();

  /** Keeps hexes in a list of size = width * height */
  public class HexGrid implements HexLayout {
    private int width;
    private int height;
    private ArrayList<Hex> hexes = new ArrayList<Hex>();
    protected transient SimpleEventBus eventBus = new SimpleEventBus();

    public int height() {
      return width;
    }
    public int width() {
      return height;
    }
    public HexGrid() {
      // Default instantiable constructor
    }
    public HexGrid(int width, int height) {
      hexes.ensureCapacity(width * height);
      this.width = width;
      this.height = height;
    }
    public Hex get(int w, int h) {
      if (!checkInput(w, h))
        return null;
      return hexes.get((width * h) + w);
    }
    public void set(int w, int h, Hex value) {
      checkInput(w, h);
      if (hexes.size() - 1 < (width * h) + w) {
        hexes.set((width * h) + w, value);
      } else {
        Hex oldHex = hexes.get((width * h) + w);
        hexes.set((width * h) + w, value);
        hexes.get((width * h) + w).setLocation(new HexLocation(w, h));
        eventBus.fireEvent(new HexChangedEvent(oldHex, value));
      }
    }
    public boolean checkInput(int w, int h) {
      if (w < 0 || h < 0 || w >= width || h >= height) {
        throw new RuntimeException("Can't add location out of bounds array");
      }
      return true;
    }
    private boolean checkInput(HexLocation location) {
      return checkInput(location.w(), location.h());
    }
    public Hex get(HexLocation location) {
      if (!checkInput(location))
        return null;
      return get(location.w(), location.h());
    }
    public void set(HexLocation location, Hex value)
    {
      checkInput(location.w(), location.h());
      set(location.w(), location.h(), value);
    }
    /** Return true when specified HexLocation can be contained within the HexList */
    public boolean isValid(HexLocation hexLocation) {
      // when location falls outside west & north bounds, return invalid
      if (width < 0 || height < 0 || hexLocation.h() >= height
              || hexLocation.w() >= width)
        return false;
      else
        return true;
    }
    /* Returns true if given location is at the edge of the board. */
    public boolean isAtEdge(HexLocation location)
    {
      if (location.h() == 0 || location.w() == 0
              || location.h() >= height
              || location.w() >= width)
        return true;
      else
        return false;
    }
    public List<Hex> validNeighbours(Hex hex)
    {
      List<Hex> neighbours = new ArrayList<Hex>();
      for (HexLocation neighbour : hex.location().getNeighbours())
        if (isValid(neighbour))
          neighbours.add(get(neighbour));
      return neighbours;
    }
    @Override public Iterator<Hex> iterator()
    {
      return hexes.iterator();
    }
    public void add(Hex hex)
    {
      hexes.add(hex);
    }
    @Override public void fireEvent(GwtEvent<?> event) {
      eventBus.fireEvent(event);
    }
    @Override public HandlerRegistration addHexChangedHandler(HexChangedHandler handler) {
      return eventBus.addHandler(HexChangedEvent.getType(), handler);
    }
  }
}
