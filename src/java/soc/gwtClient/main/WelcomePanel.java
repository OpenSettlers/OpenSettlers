package soc.gwtClient.main;

import soc.gwtClient.game.ICenterWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WelcomePanel extends HorizontalPanel implements ICenterWidget
{
    private VerticalPanel rootPanel = new VerticalPanel();
    private PushButton btnMapCreator;
    private final MainWindow mainWindow;
    
    public WelcomePanel(MainWindow mainWindow)
    {
        this.mainWindow=mainWindow;
        
        initialize();
    }

    private void initialize()
    {
        initializeMapEditorButton();
        initializeHotSeatButton();
    }

    private void initializeHotSeatButton()
    {
        PushButton btnHotseatGame = new PushButton();
        btnHotseatGame.setHTML("<H1>HotSeat game</H1>Play a mock hotseat game");
        btnHotseatGame.addClickHandler(new ClickHandler()
        {
            
            @Override
            public void onClick(ClickEvent event)
            {
                mainWindow.setCurrentWidget(mainWindow.getHotseatGame());
            }
        });
        rootPanel.add(btnHotseatGame);
    }

    private void initializeMapEditorButton()
    {
        btnMapCreator = new PushButton();
        btnMapCreator.setHTML("<H1>MapCreator</H1>create a new map");
        btnMapCreator.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                mainWindow.setCurrentWidget(mainWindow.getMapEditor());
            }
        });
        rootPanel.add(btnMapCreator);
    }

    @Override
    public Panel getRootWidget()
    {
        return rootPanel;
    }
}
