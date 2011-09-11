package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.DiceWidget.DiceWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;

/*
 * Constructs game UI using bitmap UI elements like png icons
 */
public class ActionWidgetBitmapFactory implements ActionWidgetFactory
{
  private GamePlayer player;
  private DiceWidgetFactory diceWidgetFactory;
  private GameWidget gameWidget;

  public ActionWidgetBitmapFactory(GameWidget gameWidget, GamePlayer player)
  {
    super();
    this.player = player;
    this.gameWidget = gameWidget;
    diceWidgetFactory = gameWidget.clientFactory().getDiceWidgetFactory(
            player);
  }
  @Override public ActionWidget createBuildCityWidget()
  {
    return new BuildCityBitmapWidget(gameWidget, player);
  }
  @Override public ActionWidget createBuildRoadWidget()
  {
    return new BuildRoadBitmapWidget(gameWidget, player);
  }
  @Override public ActionWidget createBuildTownWidget()
  {
    return new BuildTownBitmapWidget(gameWidget, player);
  }
  @Override public ActionWidget createBuyDevelopmentCardWidget()
  {
    return new BuyDevelopmentCardBitmapWidget(gameWidget, player);
  }
  @Override public ActionWidget createClaimVictoryWidget()
  {
    return new ClaimVictoryBitmapWidget(gameWidget, player);
  }
  @Override public ActionWidget createEndTurnWidget()
  {
    return new EndTurnBitmapWidget(gameWidget, player);
  }
  @Override public ActionWidget createPlayDevelopmentCardWidget()
  {
    return new PlayDevelopmentCardBitmapWidget(player, gameWidget);
  }
  @Override public ActionWidget createRollDiceWidget()
  {
    return gameWidget.game().rules().getDiceType().createDiceWidget(
            diceWidgetFactory);
  }
  @Override public ActionWidget createTradeBankWidget()
  {
    return new TradeBankBitmapWidget(gameWidget, player);
  }
  @Override public ActionWidget createTradePlayerWidget()
  {
    return new TradePlayerBitmapWidget(gameWidget, player);
  }
}
