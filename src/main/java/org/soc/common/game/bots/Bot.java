package org.soc.common.game.bots;

import java.util.*;

import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.*;
import org.soc.common.game.actions.*;
import org.soc.common.game.trading.*;
import org.soc.common.server.entities.*;

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
