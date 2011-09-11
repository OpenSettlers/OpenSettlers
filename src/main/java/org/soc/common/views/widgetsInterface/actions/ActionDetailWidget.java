package org.soc.common.views.widgetsInterface.actions;

import org.soc.common.game.actions.BuildCity;
import org.soc.common.game.actions.BuildRoad;
import org.soc.common.game.actions.BuildTown;
import org.soc.common.game.actions.BuyDevelopmentCard;
import org.soc.common.game.actions.ClaimVictory;
import org.soc.common.game.actions.EndTurn;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.PlaceRobber;
import org.soc.common.game.actions.PlayDevelopmentCard;
import org.soc.common.game.actions.RobPlayer;
import org.soc.common.game.actions.RollDice;
import org.soc.common.game.actions.RolledSame;
import org.soc.common.game.trading.TradeBank;
import org.soc.common.game.trading.TradePlayer;

import com.google.gwt.user.client.ui.IsWidget;

/*
 * Shows details for an action played
 */
public interface ActionDetailWidget extends IsWidget {
  public GameAction getGameAction();

  public interface ActionDetailWidgetFactory {
    public ActionDetailWidget getBuildTownDetailWidget(BuildTown buildTown);
    public ActionDetailWidget getBuildRoadDetailWidget(BuildRoad buildRoad);
    public ActionDetailWidget buildCityDetailWidget(BuildCity buildCity);
    public ActionDetailWidget getPlayDevelopmentCardDetailWidget(
            PlayDevelopmentCard playDevelopmentcard);
    public ActionDetailWidget getBuyDevelopmentCardDetailWidget(
            BuyDevelopmentCard buyDevelopmentcard);
    public ActionDetailWidget getEndTurnDetailWidget(EndTurn endTurn);
    public ActionDetailWidget getClaimVictoryDetailWidget(
            ClaimVictory claimVictory);
    public ActionDetailWidget getRobPlayerDetailWidget(RobPlayer robPlayer);
    public ActionDetailWidget getRollDiceDetailWidget(RollDice rollDice);
    public ActionDetailWidget getTradeBankDetailWidget(TradeBank tradebank);
    public ActionDetailWidget getTradePlayerDetailWidget(TradePlayer tradePlayer);
    public ActionDetailWidget getRolledSameDetailWidget(RolledSame rolledSame);
    public ActionDetailWidget getMoveRobberDetailWidget(PlaceRobber placeRobber);
  }
}
