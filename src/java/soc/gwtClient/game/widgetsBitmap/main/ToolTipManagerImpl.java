package soc.gwtClient.game.widgetsBitmap.main;

import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.generic.ToolTipManager;

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
