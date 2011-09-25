package org.soc.common.game.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.GameStatus;
import org.soc.common.game.ResourceList;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.Criterium.Allowed;
import org.soc.common.game.actions.Criterium.DevelopmentcardAvailable;
import org.soc.common.game.actions.Criterium.Exists;
import org.soc.common.game.actions.Criterium.HasAtLeast;
import org.soc.common.game.actions.Criterium.HasPlayerWithId;
import org.soc.common.game.actions.Criterium.HasSideAt;
import org.soc.common.game.actions.Criterium.HaveTownAt;
import org.soc.common.game.actions.Criterium.Is;
import org.soc.common.game.actions.Criterium.LandHexAt;
import org.soc.common.game.actions.Criterium.NotBlocking;
import org.soc.common.game.actions.Criterium.NotNull;
import org.soc.common.game.actions.Criterium.SpotNotTaken;
import org.soc.common.game.board.HexPoint;
import org.soc.common.game.board.HexSide;

public class Criteria {
  private List<Criterium> constraints = new ArrayList<Criterium>();
  private Criteria failed;
  private Criteria.CriteriaBuilder builder;
  private StringBuilder failedReasons;

  public boolean areMet() {
    failed = new Criteria();
    for (Criterium constraint : constraints)
      if (!constraint.is())
        failed.add(constraint);
    return failed.size() == 0;
  }
  public void add(Criterium criterium) {
    constraints.add(criterium);
  }
  public int size() {
    return constraints.size();
  }
  public Criteria.CriteriaBuilder builder() {
    return builder;
  }
  public String failedReasons() {
    return failedReasons.toString();
  }

  public static class CriteriaBuilder {
    private Criteria criteria = new Criteria();
    private GamePlayer player;
    private GameAction action;
    private Game game;
    private HashSet<HexPoint> spotAndNeighbours;
    private SpotNotTaken spotNotTaken;

    private CriteriaBuilder(Game game) {
      this.game = game;
    }
    public static Criteria.CriteriaBuilder require(Game game) {
      return new CriteriaBuilder(game);
    }
    public Criteria.CriteriaBuilder must(Criterium criterium) {
      criteria.add(criterium);
      return this;
    }
    public Criteria.CriteriaBuilder hasPlayerWith(int id) {
      criteria.add(new HasPlayerWithId(game, id));
      return this;
    }
    public Criteria.CriteriaBuilder notNull(Object... objects) {
      criteria.add(new NotNull(objects));
      return this;
    }
    public Criteria.CriteriaBuilder notNull(int i) {
      criteria.add(new NotNull(i));
      return this;
    }
    public Criteria.CriteriaBuilder hasTownAt(HexPoint point) {
      criteria.add(new HaveTownAt(player, point));
      return this;
    }
    public Criteria.CriteriaBuilder notBlocking(GameStatus gameStatus) {
      criteria.add(new NotBlocking(gameStatus));
      return this;
    }
    public Criteria.CriteriaBuilder allowedWith(GameStatus status) {
      criteria.add(new Allowed(status));
      return this;
    }
    public Criteria.CriteriaBuilder allowedIn(GamePhase gamePhase) {
      criteria.add(new Allowed(action, gamePhase));
      return this;
    }
    public Criteria.CriteriaBuilder allowed(TurnPhase turnPhase) {
      criteria.add(new Allowed(action, turnPhase));
      return this;
    }
    public Criteria.CriteriaBuilder canPayFor(ResourceList resources) {
      criteria.add(new HasAtLeast(player, resources));
      return this;
    }
    public Criteria.CriteriaBuilder existsOnBoard(HexPoint point) {
      criteria.add(new Exists(game, point));
      return this;
    }
    public Criteria.CriteriaBuilder existsOnBoard(HexSide side) {
      criteria.add(new Exists(game, side));
      return this;
    }
    public Criteria.CriteriaBuilder isLandHexAt(HexPoint point) {
      criteria.add(new LandHexAt(game, point));
      return this;
    }
    public Criteria.CriteriaBuilder devCardAvailable() {
      criteria.add(new DevelopmentcardAvailable(game));
      return this;
    }
    public Criteria.CriteriaBuilder spotAndNeighbours(HexPoint spot) {
      this.spotAndNeighbours = new HashSet<HexPoint>();
      spotAndNeighbours.add(spot);
      spotAndNeighbours.addAll(spot.getNeighbours());
      spotNotTaken = new SpotNotTaken(game, spotAndNeighbours);
      return this;
    }
    public Criteria.CriteriaBuilder hasSideAt(HexPoint point) {
      criteria.add(new HasSideAt(game, player, point));
      return this;
    }
    public Criteria.CriteriaBuilder notTaken() {
      criteria.add(spotNotTaken);
      return this;
    }
    public Criteria set() {
      return criteria;
    }
    public boolean areAllMet() {
      return criteria.areMet();
    }
    public Criteria.CriteriaBuilder player(GamePlayer player) {
      this.player = player;
      return this;
    }
    public Criteria.CriteriaBuilder game(Game game) {
      this.game = game;
      return this;
    }
    public Criteria.CriteriaBuilder action(GameAction action) {
      this.action = action;
      return this;
    }
    public Criteria.CriteriaBuilder is(boolean bool) {
      criteria.add(new Is(bool));
      return this;
    }
    public Criteria.CriteriaBuilder spot(HexSide sideLocation) {
      spotNotTaken = new SpotNotTaken(game, sideLocation);
      return this;
    }
  }
}