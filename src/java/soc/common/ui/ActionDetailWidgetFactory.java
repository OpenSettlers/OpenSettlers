package soc.common.ui;

import soc.common.actions.gameAction.standard.BuildCity;
import soc.common.actions.gameAction.standard.BuildRoad;
import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import soc.common.actions.gameAction.standard.ClaimVictory;
import soc.common.actions.gameAction.standard.PlayDevelopmentCard;
import soc.common.actions.gameAction.standard.RobPlayer;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.actions.gameAction.trading.TradeBank;
import soc.common.actions.gameAction.trading.TradePlayer;
import soc.common.actions.gameAction.turns.EndTurn;
import soc.common.actions.gameAction.turns.RolledSame;
import soc.gwtClient.game.widgetsInterface.playerDetail.ActionDetailWidget;

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
}
