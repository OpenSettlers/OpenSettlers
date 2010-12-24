package soc.gwtClient.main;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;

import soc.gwtClient.game.ICenterWidget;

public class LobbyPanel implements ICenterWidget
{
    private HorizontalPanel rootPanel = new HorizontalPanel();
    
    @Override
    public Panel getRootWidget()
    {
        return rootPanel;
    }

}
