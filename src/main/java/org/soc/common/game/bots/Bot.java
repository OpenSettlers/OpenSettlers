package org.soc.common.game.bots;

import java.util.List;

import org.soc.common.game.ResourceList;
import org.soc.common.game.Variant;
import org.soc.common.game.actions.BuildRoad;
import org.soc.common.game.actions.BuildTown;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.LooseCards;
import org.soc.common.game.actions.PlaceRobber;
import org.soc.common.game.actions.RobPlayer;
import org.soc.common.game.trading.TradeOffer;
import org.soc.common.server.entities.User;

/** Represents a bot. Server is responsible for calling the bot his methods. */
public interface Bot extends User {
  public void handTurn(BotPrincipal principal);
  public GameAction respondToTrade(TradeOffer offer);
  public ResourceList pickGold(int amount);
  public BuildTown pickFirstTown();
  public BuildTown pickSecondTown();
  public BuildRoad pickFirstRoad();
  public BuildRoad pickSecondRoad();
  public LooseCards looseCards(int amount);
  public PlaceRobber placeRobber();
  public RobPlayer robPlayer();
  public List<Class<? extends Variant>> getSupportedVariants();

  public abstract class AbstractBot implements Bot {
    private static final long serialVersionUID = 4696130339931789360L;
    protected User botUser;

    @Override public int amountGamesPlayed() {
      return botUser.amountGamesPlayed();
    }
    @Override public int id() {
      return botUser.id();
    }
    @Override public String name() {
      return botUser.name();
    }
    @Override public String password() {
      return botUser.password();
    }
    @Override public boolean isRegistered() {
      return botUser.isRegistered();
    }
    @Override public User setGamesPlayed(int gamesPlayed) {
      return botUser.setGamesPlayed(gamesPlayed);
    }
    @Override public User setId(int id) {
      return botUser.setId(id);
    }
    @Override public User setName(String name) {
      return botUser.setName(name);
    }
    @Override public User setPassword(String password) {
      return botUser.setPassword(password);
    }
    @Override public User setRegistered(boolean isRegistered) {
      return botUser.setRegistered(isRegistered);
    }
  }
}
