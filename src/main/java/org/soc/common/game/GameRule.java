package org.soc.common.game;

import org.soc.common.game.Dice.CitiesKnightsDice;
import org.soc.common.game.Dice.StandardDice;
import org.soc.common.game.GamePhase.DetermineFirstPlayerGamePhase;
import org.soc.common.game.GamePhase.EndedGamePhase;
import org.soc.common.game.GamePhase.InitialPlacementGamePhase;
import org.soc.common.game.GamePhase.LobbyGamePhase;
import org.soc.common.game.GamePhase.PlayTurnsGamePhase;
import org.soc.common.game.Port.ClayPort;
import org.soc.common.game.Port.FourToOnePort;
import org.soc.common.game.Port.OrePort;
import org.soc.common.game.Port.SheepPort;
import org.soc.common.game.Port.ThreeToOnePort;
import org.soc.common.game.Port.TimberPort;
import org.soc.common.game.Port.WheatPort;
import org.soc.common.game.Resource.Clay;
import org.soc.common.game.Resource.Gold;
import org.soc.common.game.Resource.Ore;
import org.soc.common.game.Resource.Sheep;
import org.soc.common.game.Resource.Timber;
import org.soc.common.game.Resource.Wheat;
import org.soc.common.game.actions.BuildCity;
import org.soc.common.game.actions.BuildRoad;
import org.soc.common.game.actions.BuildTown;
import org.soc.common.game.actions.BuyDevelopmentCard;
import org.soc.common.game.actions.ClaimVictory;
import org.soc.common.game.actions.EndTurn;
import org.soc.common.game.actions.PlayDevelopmentCard;
import org.soc.common.game.actions.RollDice;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.hexes.ClayHex;
import org.soc.common.game.hexes.DesertHex;
import org.soc.common.game.hexes.DiscoveryHex;
import org.soc.common.game.hexes.GoldHex;
import org.soc.common.game.hexes.NoneHex;
import org.soc.common.game.hexes.OreHex;
import org.soc.common.game.hexes.RandomHex;
import org.soc.common.game.hexes.SeaHex;
import org.soc.common.game.hexes.SheepHex;
import org.soc.common.game.hexes.TimberHex;
import org.soc.common.game.hexes.WheatHex;
import org.soc.common.game.pieces.Army;
import org.soc.common.game.pieces.City;
import org.soc.common.game.pieces.IslandBonus;
import org.soc.common.game.pieces.Road;
import org.soc.common.game.pieces.Robber;
import org.soc.common.game.pieces.Ship;
import org.soc.common.game.pieces.Town;
import org.soc.common.game.pieces.Wall;
import org.soc.common.game.trading.TradeBank;
import org.soc.common.game.trading.TradePlayer;

public interface GameRule {
  public String description();
  public void set(GameRules rules);

  public class AddDiscoveryHex implements GameRule {
    @Override public void set(GameRules rules) {
      rules.hexTypes().add(new DiscoveryHex());
    }
    @Override public String description() {
      return "Play with a Discovery Hex, a hex flipped when built adjacent to it";
    }
  }

  public class AddGold implements GameRule {
    @Override public void set(GameRules rules) {
      rules.hexTypes().add(new GoldHex());
      rules.supportedResources().add(new Gold());
    }
    @Override public String description() {
      // TODO fix message
      return null;
    }
  }

  /** Add support for ships */
  public class AddShip implements GameRule {
    @Override public String description() {
      // TODO fix message
      return null;
    }
    @Override public void set(GameRules rules) {
      rules.playablePieces().add(new Ship());
      rules.setStockShipAmount(15);
    }
  }

  public class BasicGamePhases implements GameRule {
    @Override public String description() {
      // TODO fix message
      return null;
    }
    @Override public void set(GameRules rules) {
      rules.supportedPhases().add(new LobbyGamePhase());
      rules.supportedPhases().add(new DetermineFirstPlayerGamePhase());
      rules.supportedPhases().add(new InitialPlacementGamePhase());
      rules.supportedPhases().add(new PlayTurnsGamePhase());
      rules.supportedPhases().add(new EndedGamePhase());
    }
  }

  public class BasicHexes implements GameRule {
    @Override public void set(GameRules rules) {
      rules.hexTypes().add(new TimberHex());
      rules.hexTypes().add(new WheatHex());
      rules.hexTypes().add(new OreHex());
      rules.hexTypes().add(new ClayHex());
      rules.hexTypes().add(new SheepHex());
      rules.hexTypes().add(new NoneHex());
      rules.hexTypes().add(new RandomHex());
      rules.hexTypes().add(new SeaHex());
      rules.hexTypes().add(new DesertHex());
    }
    @Override public String description() {
      // TODO fix message
      return null;
    }
  }

  /** Allow a city, town and road to be played with */
  public class BasicPlayerPieces implements GameRule {
    @Override public String description() {
      // TODO fix message
      return null;
    }
    @Override public void set(GameRules rules) {
      rules.playablePieces().add(new Town());
      rules.playablePieces().add(new City());
      rules.playablePieces().add(new Road());
    }
  }

  /** Allow basic resources to be played with */
  public class BasicResources implements GameRule {
    @Override public void set(GameRules rules) {
      rules.supportedResources().add(new Timber());
      rules.supportedResources().add(new Wheat());
      rules.supportedResources().add(new Ore());
      rules.supportedResources().add(new Clay());
      rules.supportedResources().add(new Sheep());
    }
    @Override public String description() {
      // TODO fix message
      return null;
    }
  }

  /** Players may gain a bonus victory point when they build on a territory the player has not yet
   * built on */
  public class NewTerritoryBonus implements GameRule {
    @Override public String description() {
      // TODO fix message
      return null;
    }
    @Override public void set(GameRules rules) {
      rules.playablePieces().add(new IslandBonus());
    }
  }

  /** Do not use a largest army */
  public class NoLargestArmy implements GameRule {
    /** Removes the LargestArmy if found */
    @Override public void set(GameRules rules) {
      rules.setLargestArmy(null);
    }
    @Override public String description() {
      // TODO fix message
      return null;
    }
  }

  public class NormalDices implements GameRule {
    @Override public String description() {
      // TODO fix message
      return null;
    }
    @Override public void set(GameRules rules) {
      rules.setDiceType(new StandardDice());
    }
  }

  public class StandardActions implements GameRule {
    @Override public String description() {
      // TODO fix message
      return null;
    }
    @Override public void set(GameRules rules) {
      // First, players might play a victory point or a soldier
      rules.possibleActions().add(new PlayDevelopmentCard());
      // Second is always rolling a dice
      rules.possibleActions().add(new RollDice());
      // Trading comes before building
      rules.possibleActions().add(new TradeBank());
      rules.possibleActions().add(new TradePlayer());
      // Build turnphase
      rules.possibleActions().add(new BuyDevelopmentCard());
      rules.possibleActions().add(new BuildRoad());
      rules.possibleActions().add(new BuildTown());
      rules.possibleActions().add(new BuildCity());
      // End turn...
      rules.possibleActions().add(new EndTurn());
      // Or win game.
      rules.possibleActions().add(new ClaimVictory());
    }
  }

  public class StandardDevelopmentCards implements GameRule {
    @Override public String description() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public void set(GameRules rules) {
      rules.setDevelopmentCardStack(DevelopmentCardList.standard());
    }
  }

  /** Uses standard amount of stock items: 4 cities, 5 towns, 15 roads */
  public class StandardStock implements GameRule {
    @Override public String description() {
      // TODO fix message
      return null;
    }
    @Override public void set(GameRules rules) {
      rules.setStockCityAmount(4);
      rules.setStockRoadAmount(15);
      rules.setStockTownAmount(5);
    }
  }

  /** Every player can trade 4:1 to the bank during {@link PlayTurnsGamePhase} */
  public class TradeBank41 implements GameRule {
    @Override public String description() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public void set(GameRules rules) {
      rules.portsAtStart().add(new FourToOnePort());
    }
  }

  public class UseBridges implements GameRule {
    @Override public String description() {
      // TODO fix message
      return null;
    }
    @Override public void set(GameRules rules) {
      rules.setStockBridgeAmount(3);
      // rules.getPlayablePieces().add(new Bridge());
    }
  }

  public class UseCitiesKnightsDice implements GameRule {
    @Override public String description() {
      // TODO fix message
      return null;
    }
    @Override public void set(GameRules rules) {
      rules.setDiceType(new CitiesKnightsDice());
    }
  }

  public class UseLargestArmy implements GameRule {
    @Override public void set(GameRules rules) {
      rules.playablePieces().add(new Army());
    }
    @Override public String description() {
      // TODO fix message
      return null;
    }
  }

  public class UseStandardPorts implements GameRule {
    @Override public String description() {
      // TODO fix message
      return "Use timber, wheat, ore, clay, sheep 2:1 ports and 3:1 port";
    }
    @Override public void set(GameRules rules) {
      rules.getSupportedPorts().add(new TimberPort());
      rules.getSupportedPorts().add(new WheatPort());
      rules.getSupportedPorts().add(new OrePort());
      rules.getSupportedPorts().add(new ClayPort());
      rules.getSupportedPorts().add(new SheepPort());
      rules.getSupportedPorts().add(new ThreeToOnePort());
    }
  }

  public class UseTradeRoutes implements GameRule {
    @Override public String description() {
      // TODO fix message
      return null;
    }
    @Override public void set(GameRules rules) {
      // rules.getPlayablePieces().add(new TradeRouteImpl());
    }
  }

  public class UseWalls implements GameRule {
    @Override public String description() {
      // TODO fix message
      return null;
    }
    @Override public void set(GameRules rules) {
      rules.playablePieces().add(new Wall());
      rules.setStockWallAmount(3);
    }
  }

  public class UseRobber implements GameRule {
    @Override public void set(GameRules rules) {
      rules.playablePieces().add(new Robber(new HexLocation(0, 0)));
    }
    @Override public String description() {
      // TODO fix message
      return null;
    }
  }
}
