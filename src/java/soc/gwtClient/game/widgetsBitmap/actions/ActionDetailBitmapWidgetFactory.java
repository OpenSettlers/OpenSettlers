package soc.gwtClient.game.widgetsBitmap.actions;

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
import soc.common.ui.ActionDetailWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.ActionDetailWidget;

public class ActionDetailBitmapWidgetFactory implements
        ActionDetailWidgetFactory
{
    private GameWidget gamePanel;

    public ActionDetailBitmapWidgetFactory(GameWidget gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public ActionDetailWidget getBuildCityDetailWidget(BuildCity buildCity)
    {
        return new BuildCityDetailBitmapWidget(gamePanel, buildCity);
    }

    @Override
    public ActionDetailWidget getBuildRoadDetailWidget(BuildRoad buildRoad)
    {
        return new BuildRoadDetailWidget(gamePanel, buildRoad);
    }

    @Override
    public ActionDetailWidget getBuildTownDetailWidget(BuildTown buildTown)
    {
        return new BuildTownDetailWidget(gamePanel, buildTown);
    }

    @Override
    public ActionDetailWidget getBuyDevelopmentCardDetailWidget(
            BuyDevelopmentCard buyDevelopmentcard)
    {
        return new BuyDevelopmentCardDetailBitmapWidget(gamePanel,
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
        return new EndTurnDetailWidget(gamePanel, endTurn);
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
        return new RobPlayerDetailWidget(gamePanel, robPlayer);
    }

    @Override
    public ActionDetailWidget getRollDiceDetailWidget(RollDice rollDice)
    {
        return new RollDiceDetailWidget(gamePanel, rollDice);
    }

    @Override
    public ActionDetailWidget getRolledSameDetailWidget(RolledSame rolledSame)
    {
        return new RolledSameDetailBitmapWidget(gamePanel, rolledSame);
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

}
