package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.game.GameSetting.IsLadder;
import org.soc.common.game.GameSetting.MaxCardsInHandWhen7;
import org.soc.common.game.GameSetting.MaximumTradesPerTurn;
import org.soc.common.game.GameSetting.No2VpPlayersRobbing;
import org.soc.common.game.GameSetting.NoSevenRounds;
import org.soc.common.game.GameSetting.ReplaceDeserts;
import org.soc.common.game.GameSetting.ShowChitsAfterPlacing;
import org.soc.common.game.GameSetting.TournamentStart;
import org.soc.common.game.GameSetting.TradingAfterBuilding;
import org.soc.common.game.board.BoardSettings;

/** A list and an object to get settings from at the same time. */
public class GameSettings implements Serializable, Iterable<GameSetting>
{
  private static final long serialVersionUID = -258750633772212293L;
  private static transient List<GameSetting> supportedSettings = new ArrayList<GameSetting>();
  private List<GameSetting> settings = new ArrayList<GameSetting>();
  // Game boardsettings may be overridden by the user
  // This implies not having a ladder game (where settings should equal
  // original boardsettings)
  private BoardSettings boardSettings = new BoardSettings();
  // Non-nullable settings
  private MaxCardsInHandWhen7 maxCardsInHandWhen7 = new MaxCardsInHandWhen7();
  // Nullable settings
  private IsLadder isLadder = null;
  private MaximumTradesPerTurn maximumTradesPerTurn = null;
  private No2VpPlayersRobbing no2vpPlayersRobbing = null;
  private NoSevenRounds noSevenRounds = null;
  private ReplaceDeserts replaceDeserts = null;
  private ShowChitsAfterPlacing showChitsAfterPlacing = null;
  private TournamentStart tournamentStart = null;
  private TradingAfterBuilding tradingAfterBuilding = null;
  static
  {
    supportedSettings.add(new No2VpPlayersRobbing());
    // supportedSettings.add(new NoSevensFirstRound());
  }

  @Override public Iterator<GameSetting> iterator() {
    return settings.iterator();
  }
  private void addSetting(GameSetting gameSetting) {
    settings.add(gameSetting);
  }
  private void removeSetting(GameSetting gameSetting) {
    if (settings.contains(gameSetting))
      settings.remove(gameSetting);
  }
  public static List<GameSetting> getSupportedSettings() {
    return supportedSettings;
  }
  public BoardSettings getBoardSettings() {
    return boardSettings;
  }
  public void setBoardSettings(BoardSettings boardSettings) {
    this.boardSettings = boardSettings;
  }
  public MaxCardsInHandWhen7 getMaxCardsInHandWhen7() {
    return maxCardsInHandWhen7;
  }
  public IsLadder getIsLadder() {
    return isLadder;
  }
  public GameSettings setIsLadder(IsLadder isLadder) {
    this.isLadder = isLadder;
    return this;
  }
  public MaximumTradesPerTurn getMaximumTradesPerTurn() {
    return maximumTradesPerTurn;
  }
  public GameSettings setMaximumTradesPerTurn(MaximumTradesPerTurn maximumTradesPerTurn) {
    removeSetting(this.maximumTradesPerTurn);
    this.maximumTradesPerTurn = maximumTradesPerTurn;
    addSetting(maximumTradesPerTurn);
    return this;
  }
  public No2VpPlayersRobbing getNo2vpPlayersRobbing() {
    return no2vpPlayersRobbing;
  }
  public GameSettings setNo2vpPlayersRobbing(No2VpPlayersRobbing no2vpPlayersRobbing) {
    removeSetting(this.no2vpPlayersRobbing);
    this.no2vpPlayersRobbing = no2vpPlayersRobbing;
    addSetting(no2vpPlayersRobbing);
    return this;
  }
  public NoSevenRounds getNoSevenRounds() {
    return noSevenRounds;
  }
  public GameSettings setNoSevenRounds(NoSevenRounds noSevenRounds) {
    removeSetting(this.noSevenRounds);
    this.noSevenRounds = noSevenRounds;
    addSetting(noSevenRounds);
    return this;
  }
  public ReplaceDeserts getReplaceDeserts() {
    return replaceDeserts;
  }
  public GameSettings setReplaceDeserts(ReplaceDeserts replaceDeserts) {
    removeSetting(this.replaceDeserts);
    this.replaceDeserts = replaceDeserts;
    addSetting(replaceDeserts);
    return this;
  }
  public ShowChitsAfterPlacing getShowChitsAfterPlacing() {
    return showChitsAfterPlacing;
  }
  public GameSettings setShowChitsAfterPlacing(ShowChitsAfterPlacing showChitsAfterPlacing) {
    removeSetting(showChitsAfterPlacing);
    this.showChitsAfterPlacing = showChitsAfterPlacing;
    addSetting(showChitsAfterPlacing);
    return this;
  }
  public TournamentStart getTournamentStart() {
    return tournamentStart;
  }
  public GameSettings setTournamentStart(TournamentStart tournamentStart) {
    removeSetting(this.tournamentStart);
    this.tournamentStart = tournamentStart;
    addSetting(tournamentStart);
    return this;
  }
  public TradingAfterBuilding getTradingAfterBuilding()
  {
    return tradingAfterBuilding;
  }
  public GameSettings setTradingAfterBuilding(
          TradingAfterBuilding tradingAfterBuilding)
  {
    removeSetting(this.tradingAfterBuilding);
    this.tradingAfterBuilding = tradingAfterBuilding;
    addSetting(tradingAfterBuilding);
    return this;
  }
}