package org.soc.common.views.widgetsInterface.actions;

import org.soc.common.game.GamePlayer;

import com.google.gwt.user.client.ui.IsWidget;

/*
 * Interface for a widget that shows UI elements representing actions
 * in a turn, such as building a town or ending turn
 */
public interface ActionPresenter extends IsWidget
{
  public GamePlayer getPlayer();
  public ActionPresenter setEnabled(boolean enabled);

  /* Widget interface to create UI based on an action which is allowed in a game. */
  public interface ActionWidgetFactory
  {
    public ActionPresenter createBuildTownWidget();
    public ActionPresenter createBuildCityWidget();
    public ActionPresenter createBuildRoadWidget();
    public ActionPresenter createRollDiceWidget();
    public ActionPresenter createEndTurnWidget();
    public ActionPresenter createBuyDevelopmentCardWidget();
    public ActionPresenter createPlayDevelopmentCardWidget();
    public ActionPresenter createTradeBankWidget();
    public ActionPresenter createTradePlayerWidget();
    public ActionPresenter createClaimVictoryWidget();
  }
}
