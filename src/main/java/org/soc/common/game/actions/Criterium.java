package org.soc.common.game.actions;

import java.util.*;

import org.soc.common.game.*;
import org.soc.common.game.Resource.Ore;
import org.soc.common.game.Resource.Sheep;
import org.soc.common.game.Resource.Wheat;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.board.*;

/** Defines validation for a GameAction to be met before performing the action. No
 * Internationalization is needed here, since invalidation occurs at designtime */
public interface Criterium {
  public boolean is();
  public String failReason();

  public abstract class AbstractCriterium implements Criterium {
    protected boolean is;
    protected StringBuilder failReason;

    @Override public boolean is() {
      return is;
    }
    @Override public String failReason() {
      return failReason.toString();
    }
  }

  public static class HasPlayerWithId extends AbstractCriterium {
    private int id;

    public HasPlayerWithId(Game game, int id) {
      this.id = id;
      is = game.playerById(id) != null;
    }
    @Override public String failReason() {
      return "No player in game with id = " + id;
    }
  }

  public static class NotNull implements Criterium {
    private boolean is;

    public NotNull(Object... objects) {
      for (Object o : objects)
        if (o == null)
          is = false;
      is = true;
    }
    public NotNull(int i) {
      is = i != 0;
    }
    @Override public boolean is() {
      return is;
    }
    @Override public String failReason() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class LandHexAt extends AbstractCriterium {
    public LandHexAt(Game game, HexPoint point) {
      is = game.board().oneOrMoreLandHexAt(point);
    }
    @Override public String failReason() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class DevelopmentcardAvailable extends AbstractCriterium {
    public DevelopmentcardAvailable(Game game) {
      is = game.developmentCardStack().size() > 0;
    }
    @Override public String failReason() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class Allowed implements Criterium {
    private boolean allowed;

    public Allowed(GameStatus status) {
      allowed = status.blocksGame();
    }
    public Allowed(GameAction action, GamePhase phase) {
      allowed = action.isAllowed(phase);
    }
    public Allowed(GameAction action, TurnPhase phase) {
      allowed = action.isAllowed(phase);
    }
    @Override public boolean is() {
      return allowed;
    }
    @Override public String failReason() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class NotBlocking implements Criterium {
    private GameStatus gameStatus;

    public NotBlocking(GameStatus gameStatus) {
      super();
      this.gameStatus = gameStatus;
    }
    @Override public boolean is() {
      return !gameStatus.blocksGame();
    }
    @Override public String failReason() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class MustHavePoint implements Criterium {
    private HasPoint hasPoint;

    public MustHavePoint(HasPoint hasPoint) {
      this.hasPoint = hasPoint;
    }
    public static MustHavePoint create(HasPoint hasPoint) {
      return new MustHavePoint(hasPoint);
    }
    @Override public boolean is() {
      return false;
    }
    @Override public String failReason() {
      return "Point cannot be null";
    }
  }

  public class HaveTownAt implements Criterium {
    private HexPoint point;
    private GamePlayer player;

    public HaveTownAt(GamePlayer player, HexPoint point) {
      this.player = player;
      this.point = point;
    }
    @Override public boolean is() {
      return player.towns().containsPoint(point);
    }
    @Override public String failReason() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class HasAtLeast implements Criterium {
    private ResourceList minimum;
    private GamePlayer player;

    public HasAtLeast(GamePlayer player, ResourceList minimum) {
      this.minimum = minimum;
      this.player = player;
    }
    @Override public boolean is() {
      return player.resources().hasAtLeast(minimum);
    }
    @Override public String failReason() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class Is implements Criterium {
    private boolean is;

    public Is(boolean is) {
      this.is = is;
    }
    @Override public boolean is() {
      // TODO Auto-generated method stub
      return false;
    }
    @Override public String failReason() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class SpotNotTaken implements Criterium {
    boolean is = false;

    public SpotNotTaken(Game game, Set<HexPoint> points) {
      //TODO implement
    }
    public SpotNotTaken(Game game, HexSide sideLocation) {
      //TODO implement
    }
    @Override public boolean is() {
      return is;
    }
    @Override public String failReason() {
      return "Already built on the given location";
    }
  }

  public class Exists implements Criterium {
    boolean is = false;

    public Exists(Game game, HexPoint point) {
      is = game.allPointPieces().contains(point);
    }
    public Exists(Game game, HexSide side) {
      is = game.allSidePieces().contains(side);
    }
    @Override public boolean is() {
      return is;
    }
    @Override public String failReason() {
      return "The spot and its neighbours is already used by anyone";
    }
  }

  // TODO: support diamonds
  public class CanBuyDevelopmentCardWith extends AbstractCriterium {
    public CanBuyDevelopmentCardWith(ResourceList resources) {
      is = resources.ofType(Wheat.class).size() == 1
              && resources.ofType(Ore.class).size() == 1
              && resources.ofType(Sheep.class).size() == 1;
    }
    @Override public String failReason() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class HasSideAt implements Criterium {
    boolean is = false;
    private GamePlayer player;
    private HexPoint point;

    public HasSideAt(Game game, GamePlayer player, HexPoint point) {
      this.player = player;
      this.point = point;
      if (game.phase().isInitialPlacement())
        is = player.sidePieces().containsNeighbourSide(point);
      else
        is = true;
    }
    @Override public boolean is() {
      return is;
    }
    private static String toString(StringBuilder builder) {
      return builder.toString();
    }
    @Override public String failReason() {
      return toString(new StringBuilder()
              .append("Game in in InitialPlacement phase, player [")
              .append(player.user().name())
              .append("] does not own any sides at ")
              .append(point));
    }
  }
}
