package org.soc.common.game;

import java.io.Serializable;

import org.soc.common.utils.SafeEventBus;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.Meta;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public interface GameSetting extends Meta, Serializable {
  public void set(GameSettings gameSettings);

  public abstract class AbstractGameSetting implements GameSetting {
    protected EventBus eventBus = new SafeEventBus();
  }

  public class IsLadder extends AbstractGameSetting {
    private boolean ladder = true;

    @GenEvent public class LadderChanged {
      @Order(0) boolean isLadder;
    }

    @Override public void set(GameSettings gameSettings) {
      gameSettings.setIsLadder(this);
    }
    public IsLadder setLadder(boolean ladder) {
      if (this.ladder != ladder) {
        this.ladder = ladder;
        eventBus.fireEvent(new LadderChangedEvent(ladder));
      }
      return this;
    }
    public boolean isLadder() {
      return ladder;
    }
    @Override public String name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getLocalizedName() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getDescription() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class MaxCardsInHandWhen7 implements GameSetting {
    private int maxCardsInHand = 7;

    @Override public void set(GameSettings gameSettings) {
      gameSettings.getMaxCardsInHandWhen7().setMaxCardsInHand(maxCardsInHand);
    }
    public MaxCardsInHandWhen7 setMaxCardsInHand(int maxCardsInHand) {
      this.maxCardsInHand = maxCardsInHand;
      return this;
    }
    public int getMaxCardsInHand() {
      return maxCardsInHand;
    }
    @Override public String name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getLocalizedName() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getDescription() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class MaximumTradesPerTurn implements GameSetting {
    private int maxTradesPerTurn = 3;

    @Override public void set(GameSettings gameSettings) {
      gameSettings.setMaximumTradesPerTurn(this);
    }
    public int getMaxTradesPerTurn() {
      return maxTradesPerTurn;
    }
    public MaximumTradesPerTurn setMaxTradesPerTurn(int maxTradesPerTurn) {
      this.maxTradesPerTurn = maxTradesPerTurn;
      return this;
    }
    @Override public String name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getLocalizedName() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getDescription() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class No2VpPlayersRobbing implements GameSetting {
    private boolean no2VpPlayersRobbing = true;

    public boolean isNo2VpPlayersRobbing() {
      return no2VpPlayersRobbing;
    }
    public No2VpPlayersRobbing setNo2VpPlayersRobbing(boolean no2VpPlayersRobbing) {
      this.no2VpPlayersRobbing = no2VpPlayersRobbing;
      return this;
    }
    @Override public void set(GameSettings gameSettings) {
      gameSettings.setNo2vpPlayersRobbing(this);
    }
    @Override public String name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getLocalizedName() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getDescription() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class NoSevenRounds implements GameSetting {
    private int noSevenRounds = 0;

    @Override public void set(GameSettings gameSettings) {
      gameSettings.setNoSevenRounds(this);
    }
    public NoSevenRounds setNoSevenRounds(int noSevenRounds) {
      this.noSevenRounds = noSevenRounds;
      return this;
    }
    public int getNoSevenRounds() {
      return noSevenRounds;
    }
    @Override public String name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getLocalizedName() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getDescription() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  /* Whether or not deserts will be replaced by given Hex, which can be either a volcano or a
   * Jungle. */
  public class ReplaceDeserts implements GameSetting
  {
    @Override public void set(GameSettings gameSettings) {
      gameSettings.setReplaceDeserts(this);
    }
    @Override public String name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getLocalizedName() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getDescription() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class ShowChitsAfterPlacing implements GameSetting
  {
    @Override public void set(GameSettings gameSettings) {
      gameSettings.setShowChitsAfterPlacing(this);
    }
    @Override public String name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getLocalizedName() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getDescription() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class TournamentStart implements GameSetting
  {
    @Override public void set(GameSettings gameSettings) {
      gameSettings.setTournamentStart(this);
    }
    @Override public String name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getLocalizedName() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getDescription() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class TradingAfterBuilding implements GameSetting
  {
    @Override public void set(GameSettings gameSettings) {
      gameSettings.setTradingAfterBuilding(this);
    }
    @Override public String name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getLocalizedName() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String getDescription() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
  }
}
