package soc.common.views.widgetsInterface.main;

import soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;

public interface DetailContainerManager
{
    public void showActionWidget(ActionDetailWidget actionDetailWidget);

    public void hideCurrentWidget();
}
