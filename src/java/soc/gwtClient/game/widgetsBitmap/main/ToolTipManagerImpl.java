package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.views.widgetsInterface.generic.ToolTip;
import soc.common.views.widgetsInterface.generic.ToolTipManager;

import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;

public class ToolTipManagerImpl implements ToolTipManager, PositionCallback
{

    public ToolTipManagerImpl()
    {
    }

    @Override
    public void hideToolTip(ToolTip toolTip)
    {
        // toolTip.hide();
    }

    @Override
    public void showToolTip(ToolTip toolTip)
    {
        // toolTip.setPopupPositionAndShow(this);
    }

    @Override
    public void setPosition(int offsetWidth, int offsetHeight)
    {

    }

}
