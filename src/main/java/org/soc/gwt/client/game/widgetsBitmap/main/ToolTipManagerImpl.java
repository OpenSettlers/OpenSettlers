package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.common.views.widgetsInterface.generic.ToolTipManager;

import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;

public class ToolTipManagerImpl implements ToolTipManager, PositionCallback
{

    public ToolTipManagerImpl()
    {
    }

    @Override
    public void hide(ToolTip toolTip)
    {
        // toolTip.hide();
    }

    @Override
    public void show(ToolTip toolTip)
    {
        // toolTip.setPopupPositionAndShow(this);
    }

    @Override
    public void setPosition(int offsetWidth, int offsetHeight)
    {

    }

}
