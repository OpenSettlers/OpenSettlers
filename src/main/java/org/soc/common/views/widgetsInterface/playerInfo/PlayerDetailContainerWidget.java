package org.soc.common.views.widgetsInterface.playerInfo;

import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget;

import com.google.gwt.user.client.ui.IsWidget;

public interface PlayerDetailContainerWidget extends IsWidget
{
    public void hide();

    public void hideCurrentWidget();

    public void showActionWidget(ActionDetailWidget actionDetailWidget);
}
