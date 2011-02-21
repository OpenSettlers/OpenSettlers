package soc.gwtClient.game.widgetsInterface.playerInfo;

import com.google.gwt.user.client.ui.IsWidget;

public interface PlayerDetailContainerWidget extends IsWidget
{
    public void hide();

    public void hideCurrentWidget();

    public void showActionWidget(ActionDetailWidget actionDetailWidget);
}
