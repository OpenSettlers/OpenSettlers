package soc.common.views.widgetsInterface.actions;

/*
 * Widget interface to create UI based on an action which is allowed
 * in a game.
 */
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
