package soc.gwtClient.game.widgetsInterface.generic;

import com.google.gwt.user.client.ui.PopupPanel;

public interface ToolTip
{
    public void setPopupPositionAndShow(PopupPanel.PositionCallback callBack);

    public void hide();
}
