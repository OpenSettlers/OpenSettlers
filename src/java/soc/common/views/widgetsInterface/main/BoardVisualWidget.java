package soc.common.views.widgetsInterface.main;

import soc.common.views.widgetsInterface.visuals.GameBoardVisual;

import com.google.gwt.user.client.ui.IsWidget;

public interface BoardVisualWidget extends IsWidget
{
    public GameBoardVisual getBoardVisual();
}
