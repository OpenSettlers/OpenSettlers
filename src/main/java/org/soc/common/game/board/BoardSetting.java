package org.soc.common.game.board;

import java.io.Serializable;

import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.Meta;

/** A setting specific to a board, to be used in the game */
public interface BoardSetting extends Meta, Serializable {
  public void set(BoardSettings boardSettings);

  public class AmountPlayers implements BoardSetting {
    private int amountPlayers = 4;

    @Override public void set(BoardSettings boardSettings) {
      boardSettings.amountPlayers().setAmountPlayers(amountPlayers);
    }
    public int amountPlayers() {
      return amountPlayers;
    }
    public AmountPlayers setAmountPlayers(int amountPlayers) {
      if (amountPlayers > 2 && amountPlayers < 7)
        this.amountPlayers = amountPlayers;
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

  public class UsePlacePortsPhase implements BoardSetting {
    private boolean placePortsPhase;

    @Override public void set(BoardSettings boardSettings) {
      if (boardSettings.usePlacePortsPhase() == null)
        boardSettings.setUsePlacePortsPhase(this);
      boardSettings.usePlacePortsPhase().setPlacePortsPhase(placePortsPhase);
    }
    public UsePlacePortsPhase setPlacePortsPhase(boolean placePortsPhase) {
      this.placePortsPhase = placePortsPhase;
      return this;
    }
    public boolean hasPlacePortsPhase() {
      return placePortsPhase;
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

  /** Whether or not players can earn traderoute points to connect territories */
  public class UsesTradeRoutes implements BoardSetting {
    private boolean tradeRoutes;

    @Override public void set(BoardSettings boardSettings) {
      if (boardSettings.useTradeRoutes() == null)
        boardSettings.setUseTradeRoutes(this);
      boardSettings.useTradeRoutes().setTradeRoutes(tradeRoutes);
    }
    public boolean isTradeRoutes() {
      return tradeRoutes;
    }
    public UsesTradeRoutes setTradeRoutes(boolean tradeRoutes) {
      this.tradeRoutes = tradeRoutes;
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

  /** Amount of vp to win on this board */
  public class VpToWin implements BoardSetting {
    private static final long serialVersionUID = -3806494586457665700L;
    private int vpToWin = 10;

    @Override public void set(BoardSettings boardSettings) {
      boardSettings.getVpToWin().setVpToWin(vpToWin);
    }
    public int vpToWin() {
      return vpToWin;
    }
    public VpToWin setVpToWin(int vpToWin) {
      this.vpToWin = vpToWin;
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
}
