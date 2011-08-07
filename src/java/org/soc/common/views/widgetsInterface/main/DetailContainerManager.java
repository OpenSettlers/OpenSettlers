package org.soc.common.views.widgetsInterface.main;

import org.soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;

public interface DetailContainerManager
{
    public void showActionWidget(ActionDetailWidget actionDetailWidget);

    public void hideCurrentWidget();
}
