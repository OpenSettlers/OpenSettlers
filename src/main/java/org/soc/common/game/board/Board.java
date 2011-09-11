package org.soc.common.game.board;

import java.io.Serializable;

import org.soc.common.board.HexChangedEvent.HasHexChangedHandlers;
import org.soc.common.board.HexChangedEvent.HexChangedHandler;
import org.soc.common.game.Game;
import org.soc.common.game.Port;
import org.soc.common.game.Port.PossiblePort;
import org.soc.common.game.PortList;
import org.soc.common.game.Random;
import org.soc.common.game.Random.ClientRandom;
import org.soc.common.game.Territory.TerritoryImpl;
import org.soc.common.game.TerritoryList;
import org.soc.common.game.board.HexLayout.HexGrid;
import org.soc.common.game.board.LayoutStrategy.RedsFirstLayout;
import org.soc.common.game.hexes.AbstractHex;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.hexes.SeaHex;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

/** Represents a Board, a playingfield which in essence is a collection of Hexes. Does not contain
 * pieces, edge/vertex graphs and other game related data which are contained in GameBoard. */
public class Board implements Serializable, HasHexChangedHandlers {
  private static final long serialVersionUID = 5182386418039607317L;
  // list of hexes this board is made of
  protected HexLayout hexes;
  // graph containing all the GraphPoints and GraphSides
  protected transient BoardGraph graph;
  // Specific settings for this board
  protected BoardSettings boardSettings;
  protected transient Random random = new ClientRandom();
  private transient LayoutStrategy layoutStrategy = new RedsFirstLayout();
  // Name of the designer of the board
  private String designerName = "Unknown player";
  // Global unique identifier for this board (UUID/GUID)
  private String id;
  // data fields
  private String name = "New Board";
  // List of territories associated with this board 
  protected TerritoryList territories = new TerritoryList()
          .addNew(new TerritoryImpl().setMainland(true));

  /** Creates a default new board by given width and height */
  public Board(int width, int height) {
    // Store hexes into some HexLayout
    this.hexes = new HexGrid(width, height);
    // Default empty board is filled with seahexes
    for (int h = 0; h < height; h++)
      for (int w = 0; w < width; w++)
        hexes.add(new SeaHex().setLocation(new HexLocation(w, h)));
  }
  /** Returns a standard 3/4 player board for testing purposes as board cannot be saved yet */
  public Board() {}
  public void initialize() {
    graph = new BoardGraph(this);
  }
  /** Broken, don't use or reimplement */
  public void resize(int newWidth, int newHeight, AbstractHex defaultHex) {
    // default on seahexes if we have no default
    if (defaultHex == null)
      defaultHex = new SeaHex();
    // return if there is nothing to resize
    if (hexes.width() == newWidth && hexes.height() == newHeight)
      return;
    // Instantiate a new board
    HexGrid newboard = new HexGrid(newWidth, newHeight);
    // HexList removedHexes = new HexList
    // loop through new sized matrix, row by row.
    for (int h = 0; h < newHeight; h++)
      for (int w = 0; w < newWidth; w++)
        // when width or height is bigger then original, add hexes
        if (w >= hexes.width() || h >= hexes.height()) {
          Hex newHex = null;
          // if outer bounds, put a SeaHex in place, otherwise a
          // defaulthex
          if (w == newWidth - 1 || w == 0 || h == newHeight - 1
                  || h == 0)
            newHex = new SeaHex();
          else
            newHex = defaultHex.copy();
          newHex.setLocation(new HexLocation(w, h));
          newboard.set(w, h, newHex);
        } else {
          // if outer bounds, put a seahex in place,
          // otherwise the defaulthex
          if (w == newWidth - 1 || w == 0 || h == newHeight - 1 || h == 0) {
            newboard.set(w, h, new SeaHex());
          } else {
            newboard.set(w, h, defaultHex.copy());
          }
          newboard.set(w, h, hexes.get(w, h).copy());
        }
    hexes = newboard;
  }
  public boolean isPortBuildable(HexSide side) {
    Hex hex1 = hexes.get(side.hex1());
    Hex hex2 = hexes.get(side.hex2());
    @SuppressWarnings("unused")
    Hex landHexLocation = null;
    Hex seaHexLocation = null;
    if (hex1.canBuildOnSea() && hex2.canBuildOnLand()) {
      landHexLocation = hex1;
      seaHexLocation = hex2;
    }
    if (hex2.canBuildOnSea() && hex1.canBuildOnLand()) {
      landHexLocation = hex2;
      seaHexLocation = hex1;
    }
    if (seaHexLocation != null && seaHexLocation.canHavePort()) {
      // A port found, invalid spot
      return false;
    }
    return true;
  }
  public PortList getAllowedPorts(AbstractHex seaHex) {
    PortList result = new PortList();
    // a list with all allowed ports allowed to be set at designtime
    HexLocation seaLocation = seaHex.location();
    // Each SeaHex has 6 possibilities.
    PortList possibilities = new PortList();
    possibilities.add(new PossiblePort(seaLocation, RotationPosition.DEG0));
    possibilities.add(new PossiblePort(seaLocation, RotationPosition.DEG60));
    possibilities.add(new PossiblePort(seaLocation, RotationPosition.DEG120));
    possibilities.add(new PossiblePort(seaLocation, RotationPosition.DEG180));
    possibilities.add(new PossiblePort(seaLocation, RotationPosition.DEG240));
    possibilities.add(new PossiblePort(seaLocation, RotationPosition.DEG300));
    // Test if the other hex of the hexside is a landhex, add it to the
    // allowed
    // hexes when so.
    for (Port possibility : possibilities) {
      HexLocation land = possibility.landLocation();
      // The location must be exist on the board
      if (!hexes.isValid(land))
        // A port is placeable when the land location is actual land
        if (this.hexes.get(land).canBuildOnLand())
          result.add(possibility);
    }
    return result;
  }
  public BoardSettings getBoardSettings()
  {
    return boardSettings;
  }
  public HexLayout hexes() {
    return hexes;
  }
  public int getWidth() {
    return hexes.width();
  }
  public int getHeight() {
    return hexes.height();
  }
  public TerritoryList getTerritories() {
    return territories;
  }
  public String getDesigner() {
    return designerName;
  }
  public Board setDesigner(String designer) {
    this.designerName = designer;
    return this;
  }
  public String getId() {
    return id;
  }
  public Board setId(String id) {
    this.id = id;
    return this;
  }
  public String name() {
    return name;
  }
  public Board setName(String name) {
    this.name = name;
    return this;
  }
  public BoardGraph graph() {
    return graph;
  }
  /** Returns true when target side can be built on for land pieces */
  public boolean isBuildableLand(HexSide side) {
    return hexes.get(side.hex1()).canBuildOnLand()
            || hexes.get(side.hex2()).canBuildOnLand();
  }
  /** Returns true when given point can be used to build a town, city, knight, wall on */
  public boolean includeInGame(HexPoint point) {
    // We dont't want to add null
    if (point == null)
      return false;
    // HexPoint should connect to three valid hexes
    if (!point.fallsWithinBoardBounds(hexes.width(), hexes.height()))
      return false;
    // Hex should be able to build either sea or land on
    for (HexLocation neighbour : point.hexLocations())
      if (hexes.get(neighbour).canBuildOnLand()
              && hexes.get(neighbour).canBuildOnSea())
        return false;
    return true;
  }
  public boolean fallsWithinBounds(HexSide side) {
    if (!fallsWithinBounds(side.hex1())
            || !fallsWithinBounds(side.hex2()))
      return false;
    else
      return true;
  }
  public boolean fallsWithinBounds(HexLocation location) {
    if (location.h() < 0 || location.w() < 0)
      return false;
    if (location.h() >= hexes.height()
            || location.w() >= hexes.width())
      return false;
    return true;
  }
  public boolean fallsWithinBounds(HexPoint point) {
    if (!fallsWithinBounds(point.hex1())
            || !fallsWithinBounds(point.hex2())
            || !fallsWithinBounds(point.hex3()))
      return false;
    else
      return true;
  }
  /** Returns true when given point can be used to build a town on */
  public boolean isTownBuildable(GraphPoint possibleCandidate) {
    for (HexLocation location : possibleCandidate.hexPoint().hexLocations()) {
      Hex hex = hexes.get(location);
      if (!hex.isPartOfGame())
        return false;
    }
    Hex hex1 = hexes.get(possibleCandidate.hexPoint().hex1());
    Hex hex2 = hexes.get(possibleCandidate.hexPoint().hex2());
    Hex hex3 = hexes.get(possibleCandidate.hexPoint().hex3());
    if (!hex1.canBuildOnLand()
            && !hex2.canBuildOnLand()
            && !hex3.canBuildOnLand())
      return false;
    return true;
  }
  /** Returns true when either one of the hexes of the given side is able to build for land side
   * pieces (road) */
  public boolean isRoadBuildable(GraphSide neighbour) {
    // Grab both hexes
    Hex hex1 = hexes.get(neighbour.side().hex1());
    Hex hex2 = hexes.get(neighbour.side().hex2());
    // Return true when either hex is capable of hosting land side pieces
    return hex1.canBuildOnLand() || hex2.canBuildOnLand();
  }
  public void layoutBoard(Game game) {
    layoutStrategy.layoutBoard(game);
  }

  @GenEvent public class HexChanged {
    @Order(0) Hex oldHex;
    @Order(1) Hex newHex;
  }

  @Override public void fireEvent(GwtEvent<?> event) {}
  @Override public HandlerRegistration addHexChangedHandler(HexChangedHandler handler) {
    // TODO Auto-generated method stub
    return null;
  }
}
