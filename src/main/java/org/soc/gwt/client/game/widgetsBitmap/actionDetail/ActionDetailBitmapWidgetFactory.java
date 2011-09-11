package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.game.actions.BuildCity;
import org.soc.common.game.actions.BuildRoad;
import org.soc.common.game.actions.BuildTown;
import org.soc.common.game.actions.BuyDevelopmentCard;
import org.soc.common.game.actions.ClaimVictory;
import org.soc.common.game.actions.EndTurn;
import org.soc.common.game.actions.PlaceRobber;
import org.soc.common.game.actions.PlayDevelopmentCard;
import org.soc.common.game.actions.RobPlayer;
import org.soc.common.game.actions.RollDice;
import org.soc.common.game.actions.RolledSame;
import org.soc.common.game.trading.TradeBank;
import org.soc.common.game.trading.TradePlayer;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public class ActionDetailBitmapWidgetFactory implements
        ActionDetailWidgetFactory
{
  private GameWidget gameWidget;

  public ActionDetailBitmapWidgetFactory(GameWidget gameWidget)
  {
    super();
    this.gameWidget = gameWidget;
  }
  @Override public ActionDetailWidget buildCityDetailWidget(BuildCity buildCity)
  {
    return new BuildCityDetailBitmapWidget(gameWidget, buildCity);
  }
  @Override public ActionDetailWidget getBuildRoadDetailWidget(BuildRoad buildRoad)
  {
    return new BuildRoadDetailWidget(gameWidget, buildRoad);
  }
  @Override public ActionDetailWidget getBuildTownDetailWidget(BuildTown buildTown)
  {
    return new BuildTownDetailWidget(gameWidget, buildTown);
  }
  @Override public ActionDetailWidget getBuyDevelopmentCardDetailWidget(
          BuyDevelopmentCard buyDevelopmentcard)
  {
    return new BuyDevelopmentCardDetailBitmapWidget(gameWidget,
            buyDevelopmentcard);
  }
  @Override public ActionDetailWidget getClaimVictoryDetailWidget(
          ClaimVictory claimVictory)
  {
    return null;
  }
  @Override public ActionDetailWidget getEndTurnDetailWidget(EndTurn endTurn)
  {
    return new EndTurnDetailWidget(gameWidget, endTurn);
  }
  @Override public ActionDetailWidget getPlayDevelopmentCardDetailWidget(
          PlayDevelopmentCard playDevelopmentcard)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public ActionDetailWidget getRobPlayerDetailWidget(RobPlayer robPlayer)
  {
    return new RobPlayerDetailWidget(gameWidget, robPlayer);
  }
  @Override public ActionDetailWidget getRollDiceDetailWidget(RollDice rollDice)
  {
    return new RollDiceDetailWidget(gameWidget, rollDice);
  }
  @Override public ActionDetailWidget getRolledSameDetailWidget(RolledSame rolledSame)
  {
    return new RolledSameDetailBitmapWidget(gameWidget, rolledSame);
  }
  @Override public ActionDetailWidget getTradeBankDetailWidget(TradeBank tradebank)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public ActionDetailWidget getTradePlayerDetailWidget(TradePlayer tradePlayer)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public ActionDetailWidget getMoveRobberDetailWidget(PlaceRobber placeRobber)
  {
    // TODO Auto-generated method stub
    return null;
  }
}
