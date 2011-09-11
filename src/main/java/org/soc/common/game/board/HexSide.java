package org.soc.common.game.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface HexSide extends HasNeighbours {
  public HexLocation hex1();
  public HexLocation hex2();
  public HexPoint getHexPoint1();
  public HexPoint getHexPoint2();
  public HexLocation getHighestOrLeftestHex();
  /* Returns a list of (maximum) three points neighboring this */
  public List<HexPoint> neighbourPoints();
  /* Returns the direction this side is pointing to */
  public SideDirection getDirection();
  public HexPoint getOtherPoint(HexPoint first);
  public HexLocation getOtherLocation(HexLocation first);

  public enum SideDirection {
    // "/"
    SLOPEUP,
    // "\"
    SLOPEDOWN,
    // "|"
    UPDOWN;
  }

  /** Represents a side, determined by two HexLocations. */
  /* TODO: Abstract into interface */
  public class HexSideImpl implements Serializable, HexSide {
    private static final long serialVersionUID = 8459387416372761641L;
    // A HexSide may be constructed using a set of two HexLocations or
    // a set of two HexPoints
    // The two HexLocations the HexSide is represented by
    private HexLocation hex1;
    private HexLocation hex2;
    // The two HexPoints a hexside is represented by
    private HexPoint hexPoint1;
    private HexPoint hexPoint2;
    // Direction the side points to
    private SideDirection sideDirection;

    @Override public HexLocation hex1() {
      return hex1;
    }
    @Override public HexLocation hex2() {
      return hex2;
    }
    @Override public HexPoint getHexPoint1() {
      return hexPoint1;
    }
    @Override public HexPoint getHexPoint2() {
      return hexPoint2;
    }
    public HexSideImpl() { /* Empty */}
    HexSideImpl(HexLocation hex1, HexLocation hex2) {
      this.hex1 = hex1;
      this.hex2 = hex2;
      calculatePoints();
    }
    public HexSideImpl(HexPoint point1, HexPoint point2) {
      this.hexPoint1 = point1;
      this.hexPoint2 = point2;
      calculateHexes();
    }
    @Override public HexLocation getHighestOrLeftestHex() {
      if (hex1.h() == hex2.h())
        // both on same row, return leftest
        return hex1.w() < hex2.w() ? hex1 : hex2;
      else
        // different rows, return highest
        return hex1.h() > hex2.h() ? hex2 : hex1;
    }
    /** Returns a list of (maximum) three points neighbouring this */
    @Override public List<HexPoint> neighbourPoints() {
      List<HexPoint> result = new ArrayList<HexPoint>();
      HexLocation top = getHighestOrLeftestHex();
      // TODO: headache code 
      // either visualize using pics or rewrite
      switch (getDirection())
      {
        case UPDOWN:
          int offset = top.h() % 2 == 0 ? 1 : 0;
          result.add(new HexPoint(hex1, hex2,
                  new HexLocation(top.w() + offset, top.h() - 1)));
          result.add(new HexPoint(hex1, hex2,
                  new HexLocation(top.w() + offset, top.h() + 1)));
          break;
        case SLOPEDOWN:
          int offset2 = top.h() % 2 == 0 ? 1 : 0;
          result.add(new HexPoint(hex1, hex2, new HexLocation(
                  top.w() - 1, top.h())));
          // generates bad hex
          result.add(new HexPoint(hex1, hex2, new HexLocation(top.w()
                  + offset2, top.h() + 1)));
          break;
        case SLOPEUP:
          int offset3 = top.h() % 2 == 0 ? 0 : 1;
          result.add(new HexPoint(hex1, hex2, new HexLocation(top.w()
                  - offset3, top.h() + 1)));
          result.add(new HexPoint(hex1, hex2, new HexLocation(
                  top.w() + 1, top.h())));
          break;
      }
      return result;
    }
    /** Creates the two HexLocations this HexSide is primarily represented by */
    private void calculateHexes() {
      List<HexLocation> locations = new ArrayList<HexLocation>();
      locations.add(hexPoint1.hex1());
      locations.add(hexPoint1.hex2());
      locations.add(hexPoint1.hex3());
      locations.add(hexPoint2.hex1());
      locations.add(hexPoint2.hex2());
      locations.add(hexPoint2.hex3());
      /* TODO: port to java
       * 
       * 
       * var x = from l in locations group l by l into lunique where lunique.Count() == 2 select
       * lunique.Key; */
      // first of resultset
      hex1 = locations.get(0);
      // last of resultset
      hex2 = locations.get(5);
    }
    /** Returns the direction this side is pointing to */
    @Override public SideDirection getDirection() {
      // lazy init of direction variable
      if (sideDirection == null) {
        // |
        // both hexes are on the same row, so the side is updown
        if (hex1.h() == hex2.h())
          return SideDirection.UPDOWN;
        if (getHighestOrLeftestHex().h() % 2 == 0) {  // even rows
          if (hex1.w() == hex2.w())
            sideDirection = SideDirection.SLOPEDOWN;
          else
            sideDirection = SideDirection.SLOPEUP;
        } else { // uneven rows  
          if (hex1.w() == hex2.w())
            sideDirection = SideDirection.SLOPEUP;
          else
            sideDirection = SideDirection.SLOPEDOWN;
        }
      }
      return sideDirection;
    }
    /** Creates two HexPoints, each consisting of three HexLocations. TODO: copy+paste image
     * reference from paper */
    private void calculatePoints() {
      HexLocation loc1 = null;
      HexLocation loc2 = null;
      HexLocation lefttop = getHighestOrLeftestHex();
      int offset = lefttop.h() % 2 == 0 ? 1 : 0;
      switch (getDirection()) {
        case UPDOWN:
          loc1 = new HexLocation(offset + lefttop.w(), lefttop.h() - 1);
          loc2 = new HexLocation(offset + lefttop.w(), lefttop.h() + 1);
          break;
        case SLOPEDOWN:
          loc1 = new HexLocation(offset + lefttop.w(), lefttop.h() + 1);
          loc2 = new HexLocation(lefttop.w() - 1, lefttop.h());
          break;
        case SLOPEUP:
          loc1 = new HexLocation(lefttop.w() + 1, lefttop.h());
          loc2 = new HexLocation(lefttop.w() - 1 + offset, lefttop.h() + 1);
          break;
      }
      hexPoint1 = new HexPoint(hex1, hex2, loc1);
      hexPoint2 = new HexPoint(hex1, hex2, loc2);
    }
    @Override public HexPoint getOtherPoint(HexPoint first) {
      if (first.equals(hexPoint1))
        return hexPoint2;
      else
        return hexPoint1;
    }
    @Override public HexLocation getOtherLocation(HexLocation first) {
      if (first.equals(hex1))
        return hex2;
      else
        return hex1;
    }
    /** Returns true when given location is contained by this HexSide */
    public boolean hasLocation(HexLocation check) {
      return hex1.equals(check) || hex2.equals(check);
    }
    public boolean equals(Object other) {
      if (other instanceof HexSideImpl) {
        HexSide otherSide = (HexSide) other;
        return (hex1.equals(otherSide.hex1()) && hex2.equals(otherSide.hex2()))
                || (hex1.equals(otherSide.hex2()) && hex2.equals(otherSide.hex1()));
      }
      return false;
    }
    /* True when both HexLocations fall inside the board bounds */
    public boolean fallsWithinBoardBounds(int width, int height) {
      if ((!hex1.fallsInsideBoardBounds(width, height))
              || (!hex2.fallsInsideBoardBounds(width, height))) {
        return false;
      }
      return true;
    }
    @Override public int hashCode() {
      return hex1.hashCode() * hex2.hashCode();
    }
    @Override public List<HexLocation> neighbourLocations() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public List<HexSideImpl> neighbourSides() {
      // TODO Auto-generated method stub
      return null;
    }
  }
}