package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.GameAction;

import com.google.gwt.user.client.ui.IsWidget;

public interface PlayerDetailContainerWidget extends IsWidget
{
    public void hide();

    public void showMouseOverWidget(PlayerDetailWidget playerDetailWidget);

    public void hideMouseOverWidget();

    public void hideCurrentWidget();

    public void showActionWidget(GameAction action);
}
