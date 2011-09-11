package org.soc.common.game;

import java.io.Serializable;

import org.soc.common.game.trading.TradeOfferList;

public interface Turn extends Serializable {
  public TradeOfferList getTradeOffers();
  public GamePlayer player();
  public int getID();
  public Turn setID(int id);
  public TurnPhase getTurnPhase();
  public void setTurnPhase(TurnPhase phase);
  public Turn setPlayer(GamePlayer player);

  public class TurnImpl implements Turn {
    private static final long serialVersionUID = -7392096314044521868L;
    private GamePlayer player;
    private int id = 0;
    private TradeOfferList tradeOffers = new TradeOfferList();
    private TurnPhase turnPhase;

    public TurnImpl(GamePlayer player, int id, TurnPhase turnPhase) {
      super();
      this.player = player;
      this.id = id;
      this.turnPhase = turnPhase;
    }
    public TurnImpl() {}
    public TurnImpl(GamePlayer player) {
      super();
      this.player = player;
    }
    public Turn setPlayer(GamePlayer player) {
      this.player = player;
      return this;
    }
    @Override public TradeOfferList getTradeOffers() {
      return tradeOffers;
    }
    public GamePlayer player() {
      return player;
    }
    @Override public int getID() {
      return id;
    }
    @Override public Turn setID(int id) {
      this.id = id;
      return this;
    }
    @Override public TurnPhase getTurnPhase() {
      return turnPhase;
    }
    @Override public void setTurnPhase(TurnPhase phase) {
      this.turnPhase = phase;
    }
  }
}