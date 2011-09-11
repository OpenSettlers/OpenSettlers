package org.soc.common.game.board;

import org.soc.common.game.hexes.AbstractHex;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.hexes.NoneHex;

/** When a user finished designing a board, he must check it against a set of rules. Such rules must
 * implement this interface. A server saving a board can set constraints for boards to be
 * submittable. Because of the many custom rules in the variants, it's necessary to be able to check
 * if a board meets certain criteria. */
public interface DesignRule {
  public boolean invoke(Board b);
  public String problem();
  public RuleSeverity severity();

  public enum RuleSeverity {
    ERROR,
    WARNING,
    INFORMATION
  }

  /** Reports an error if the board does not have a name */
  public class NoNameDesignRule implements DesignRule {
    @Override public String problem() {
      return "Board does not have a name";
    }
    @Override public RuleSeverity severity() {
      return RuleSeverity.ERROR;
    }
    @Override public boolean invoke(Board b) {
      if (b.name() != null)
        return (b.name().length() > 0);
      else
        return false;
    }
  }

  /** NoneHexes should always be bordered with SeaHexes, or other Hexes where the isPartOfGame is set
   * to false. */
  public class NoneHexOnlyBorderSea implements DesignRule {
    @Override public String problem() {
      // TODO: fix message
      return null;
    }
    @Override public RuleSeverity severity() {
      return RuleSeverity.ERROR;
    }
    /** Returns false when a NoneHex is found with a non-seahex as neighbour */
    @Override public boolean invoke(Board b) {
      for (Hex hex : b.hexes()) {
        if (hex instanceof NoneHex) {
          // Check each neighbour inside the board bounds if it's a
          // SeaHex.
          for (HexLocation neighbour : hex.location().getNeighbours()) {
            if (neighbour.fallsInsideBoardBounds(b.getWidth(),
                    b.getHeight())
                    && !(b.hexes().get(neighbour) instanceof AbstractHex)) {
              // Non-SeaHex found, this rule is not met.
              return false;
            }
          }
        }
      }
      // All NoneHexes have only SeaHexes as neighbours, evaulate to true
      return true;
    }
  }
}
