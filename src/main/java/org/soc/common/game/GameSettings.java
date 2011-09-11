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

  @Override public Iterator<GameSetting> iterator()
  {
    return settings.iterator();
  }
  private void addSetting(GameSetting gameSetting)
  {
    settings.add(gameSetting);
  }
  private void removeSetting(GameSetting gameSetting)
  {
    if (settings.contains(gameSetting))
      settings.remove(gameSetting);
  }
  public static List<GameSetting> getSupportedSettings()
  {
    return supportedSettings;
  }
  /** @return the boardSettings */
  public BoardSettings getBoardSettings()
  {
    return boardSettings;
  }
  /** @param boardSettings the boardSettings to set */
  public void setBoardSettings(BoardSettings boardSettings)
  {
    this.boardSettings = boardSettings;
  }
  /** @return the maxCardsInHandWhen7 */
  public MaxCardsInHandWhen7 getMaxCardsInHandWhen7()
  {
    return maxCardsInHandWhen7;
  }
  /** @return the isLadder */
  public IsLadder getIsLadder()
  {
    return isLadder;
  }
  /** @param isLadder the isLadder to set */
  public GameSettings setIsLadder(IsLadder isLadder)
  {
    this.isLadder = isLadder;
    return this;
  }
  /** @return the maximumTradesPerTurn */
  public MaximumTradesPerTurn getMaximumTradesPerTurn()
  {
    return maximumTradesPerTurn;
  }
  /** @param maximumTradesPerTurn the maximumTradesPerTurn to set */
  public GameSettings setMaximumTradesPerTurn(
          MaximumTradesPerTurn maximumTradesPerTurn)
  {
    removeSetting(this.maximumTradesPerTurn);
    this.maximumTradesPerTurn = maximumTradesPerTurn;
    addSetting(maximumTradesPerTurn);
    return this;
  }
  /** @return the no2vpPlayersRobbing */
  public No2VpPlayersRobbing getNo2vpPlayersRobbing()
  {
    return no2vpPlayersRobbing;
  }
  /** @param no2vpPlayersRobbing the no2vpPlayersRobbing to set */
  public GameSettings setNo2vpPlayersRobbing(
          No2VpPlayersRobbing no2vpPlayersRobbing)
  {
    removeSetting(this.no2vpPlayersRobbing);
    this.no2vpPlayersRobbing = no2vpPlayersRobbing;
    addSetting(no2vpPlayersRobbing);
    return this;
  }
  /** @return the noSevenRounds */
  public NoSevenRounds getNoSevenRounds()
  {
    return noSevenRounds;
  }
  /** @param noSevenRounds the noSevenRounds to set */
  public GameSettings setNoSevenRounds(NoSevenRounds noSevenRounds)
  {
    removeSetting(this.noSevenRounds);
    this.noSevenRounds = noSevenRounds;
    addSetting(noSevenRounds);
    return this;
  }
  /** @return the replaceDeserts */
  public ReplaceDeserts getReplaceDeserts()
  {
    return replaceDeserts;
  }
  /** @param replaceDeserts the replaceDeserts to set */
  public GameSettings setReplaceDeserts(ReplaceDeserts replaceDeserts)
  {
    removeSetting(this.replaceDeserts);
    this.replaceDeserts = replaceDeserts;
    addSetting(replaceDeserts);
    return this;
  }
  /** @return the showChitsAfterPlacing */
  public ShowChitsAfterPlacing getShowChitsAfterPlacing()
  {
    return showChitsAfterPlacing;
  }
  /** @param showChitsAfterPlacing the showChitsAfterPlacing to set */
  public GameSettings setShowChitsAfterPlacing(
          ShowChitsAfterPlacing showChitsAfterPlacing)
  {
    removeSetting(showChitsAfterPlacing);
    this.showChitsAfterPlacing = showChitsAfterPlacing;
    addSetting(showChitsAfterPlacing);
    return this;
  }
  /** @return the tournamentStart */
  public TournamentStart getTournamentStart()
  {
    return tournamentStart;
  }
  /** @param tournamentStart the tournamentStart to set */
  public GameSettings setTournamentStart(TournamentStart tournamentStart)
  {
    removeSetting(this.tournamentStart);
    this.tournamentStart = tournamentStart;
    addSetting(tournamentStart);
    return this;
  }
  /** @return the tradingAfterBuilding */
  public TradingAfterBuilding getTradingAfterBuilding()
  {
    return tradingAfterBuilding;
  }
  /** @param tradingAfterBuilding the tradingAfterBuilding to set */
  public GameSettings setTradingAfterBuilding(
          TradingAfterBuilding tradingAfterBuilding)
  {
    removeSetting(this.tradingAfterBuilding);
    this.tradingAfterBuilding = tradingAfterBuilding;
    addSetting(tradingAfterBuilding);
    return this;
  }
}