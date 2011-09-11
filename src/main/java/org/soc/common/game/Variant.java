package org.soc.common.game;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.GameRule.AddDiscoveryHex;
import org.soc.common.game.GameRule.AddGold;
import org.soc.common.game.GameRule.BasicGamePhases;
import org.soc.common.game.GameRule.BasicHexes;
import org.soc.common.game.GameRule.BasicPlayerPieces;
import org.soc.common.game.GameRule.BasicResources;
import org.soc.common.game.GameRule.NewTerritoryBonus;
import org.soc.common.game.GameRule.NoLargestArmy;
import org.soc.common.game.GameRule.NormalDices;
import org.soc.common.game.GameRule.StandardActions;
import org.soc.common.game.GameRule.StandardDevelopmentCards;
import org.soc.common.game.GameRule.StandardStock;
import org.soc.common.game.GameRule.TradeBank41;
import org.soc.common.game.GameRule.UseBridges;
import org.soc.common.game.GameRule.UseCitiesKnightsDice;
import org.soc.common.game.GameRule.UseLargestArmy;
import org.soc.common.game.GameRule.UseRobber;
import org.soc.common.game.GameRule.UseWalls;

public interface Variant {
  public void setRules(GameRules gameRules);
  public List<GameRule> getRules();

  public abstract class AbstractRuleSet implements Variant {
    protected Game game;
    protected List<GameRule> rules = new ArrayList<GameRule>();

    public List<GameRule> getRules() {
      return rules;
    }
    public AbstractRuleSet(Game game) {
      this.game = game;
    }
    @Override public void setRules(GameRules gameRules) {
      for (GameRule rule : rules)
        rule.set(gameRules);
    }
  }

  public class Extended extends AbstractRuleSet {
    public Extended(Game game) {
      super(game);
    }
  }

  public class CitiesKnights extends AbstractRuleSet {
    private List<GameRule> rules = new ArrayList<GameRule>();

    public CitiesKnights(Game game) {
      super(game);
      rules.add(new NoLargestArmy());
      rules.add(new UseCitiesKnightsDice());
      rules.add(new UseWalls());
    }
  }

  /** A Pioneers ruleset adds two pieces: a wall and a bridge. - A wall can be purchased for 2 clay
   * to increase the maximum amount of cards for a player by two - A bridge behaves exactly like a
   * road, except that it can be built on water.
   * 
   * Those two pieces can be built in the usual BuildingTurnPhase */
  public class Pioneers extends AbstractRuleSet {
    public Pioneers(Game game) {
      super(game);
      rules.add(new UseWalls());
      rules.add(new UseBridges());
    }
  }

  public class Standard extends AbstractRuleSet {
    public Standard(Game game) {
      super(game);
      rules.add(new BasicHexes());
      rules.add(new BasicResources());
      rules.add(new BasicPlayerPieces());
      rules.add(new BasicGamePhases());
      rules.add(new StandardActions());
      rules.add(new UseLargestArmy());
      rules.add(new UseRobber());
      rules.add(new StandardStock());
      rules.add(new TradeBank41());
      rules.add(new StandardDevelopmentCards());
      rules.add(new NormalDices());
    }
  }

  public class SeaFarers extends AbstractRuleSet {
    public SeaFarers(Game game) {
      super(game);
      rules.add(new AddDiscoveryHex());
      rules.add(new NewTerritoryBonus());
      rules.add(new AddGold());
    }
  }

  public class Sea3D extends AbstractRuleSet {
    public Sea3D(Game game) {
      super(game);
    }
  }
}
