package org.soc.common.views.widgetsInterface.actions;

import org.soc.common.actions.gameAction.standard.BuildCity;
import org.soc.common.actions.gameAction.standard.BuildRoad;
import org.soc.common.actions.gameAction.standard.BuildTown;
import org.soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import org.soc.common.actions.gameAction.standard.ClaimVictory;
import org.soc.common.actions.gameAction.standard.PlaceRobber;
import org.soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import org.soc.common.actions.gameAction.standard.RobPlayer;
import org.soc.common.actions.gameAction.standard.RollDice;
import org.soc.common.actions.gameAction.trading.TradeBank;
import org.soc.common.actions.gameAction.trading.TradePlayer;
import org.soc.common.actions.gameAction.turns.EndTurn;
import org.soc.common.actions.gameAction.turns.RolledSame;
import org.soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;

/*
 * Creates a widget visualizing a GameAction
 */
public interface ActionDetailWidgetFactory
{
    public ActionDetailWidget getBuildTownDetailWidget(BuildTown buildTown);

    public ActionDetailWidget getBuildRoadDetailWidget(BuildRoad buildRoad);

    public ActionDetailWidget getBuildCityDetailWidget(BuildCity buildCity);

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
