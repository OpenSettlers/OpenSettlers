package soc.common.views.widgetsInterface.main;


import com.google.gwt.user.client.ui.Panel;

public interface GamePanelLayoutWidget
{
    public GameWidgetFactory getGameWidgetFactory();

    public void initialize();

    public Panel getRootPanel();
}
