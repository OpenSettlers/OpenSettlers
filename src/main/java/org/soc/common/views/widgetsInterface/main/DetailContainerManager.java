package org.soc.common.views.widgetsInterface.main;

import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget;

public interface DetailContainerManager
{
    public void showActionWidget(ActionDetailWidget actionDetailWidget);

    public void hideCurrentWidget();
}
