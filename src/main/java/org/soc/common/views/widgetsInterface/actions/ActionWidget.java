package org.soc.common.views.widgetsInterface.actions;

import org.soc.common.game.GamePlayer;

import com.google.gwt.user.client.ui.IsWidget;

/*
 * Interface for a widget that shows UI elements representing actions
 * in a turn, such as building a town or ending turn
 */
public interface ActionWidget extends IsWidget
{
  public GamePlayer getPlayer();
  public ActionWidget setEnabled(boolean enabled);

  /* Widget interface to create UI based on an action which is allowed in a game. */
  public interface ActionWidgetFactory
  {
    public ActionWidget createBuildTownWidget();
    public ActionWidget createBuildCityWidget();
    public ActionWidget createBuildRoadWidget();
    public ActionWidget createRollDiceWidget();
    public ActionWidget createEndTurnWidget();
    public ActionWidget createBuyDevelopmentCardWidget();
    public ActionWidget createPlayDevelopmentCardWidget();
    public ActionWidget createTradeBankWidget();
    public ActionWidget createTradePlayerWidget();
    public ActionWidget createClaimVictoryWidget();
  }
}
