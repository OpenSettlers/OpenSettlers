package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

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
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;

public class ActionDetailBitmapWidgetFactory implements
        ActionDetailWidgetFactory
{
    private GameWidget gameWidget;

    public ActionDetailBitmapWidgetFactory(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;
    }

    @Override
    public ActionDetailWidget getBuildCityDetailWidget(BuildCity buildCity)
    {
        return new BuildCityDetailBitmapWidget(gameWidget, buildCity);
    }

    @Override
    public ActionDetailWidget getBuildRoadDetailWidget(BuildRoad buildRoad)
    {
        return new BuildRoadDetailWidget(gameWidget, buildRoad);
    }

    @Override
    public ActionDetailWidget getBuildTownDetailWidget(BuildTown buildTown)
    {
        return new BuildTownDetailWidget(gameWidget, buildTown);
    }

    @Override
    public ActionDetailWidget getBuyDevelopmentCardDetailWidget(
            BuyDevelopmentCard buyDevelopmentcard)
    {
        return new BuyDevelopmentCardDetailBitmapWidget(gameWidget,
                buyDevelopmentcard);
    }

    @Override
    public ActionDetailWidget getClaimVictoryDetailWidget(
            ClaimVictory claimVictory)
    {
        return null;
    }

    @Override
    public ActionDetailWidget getEndTurnDetailWidget(EndTurn endTurn)
    {
        return new EndTurnDetailWidget(gameWidget, endTurn);
    }

    @Override
    public ActionDetailWidget getPlayDevelopmentCardDetailWidget(
            PlayDevelopmentCard playDevelopmentcard)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ActionDetailWidget getRobPlayerDetailWidget(RobPlayer robPlayer)
    {
        return new RobPlayerDetailWidget(gameWidget, robPlayer);
    }

    @Override
    public ActionDetailWidget getRollDiceDetailWidget(RollDice rollDice)
    {
        return new RollDiceDetailWidget(gameWidget, rollDice);
    }

    @Override
    public ActionDetailWidget getRolledSameDetailWidget(RolledSame rolledSame)
    {
        return new RolledSameDetailBitmapWidget(gameWidget, rolledSame);
    }

    @Override
    public ActionDetailWidget getTradeBankDetailWidget(TradeBank tradebank)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ActionDetailWidget getTradePlayerDetailWidget(TradePlayer tradePlayer)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ActionDetailWidget getMoveRobberDetailWidget(PlaceRobber placeRobber)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
