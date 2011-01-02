package soc.gwtClient.main;

import soc.gwtClient.game.ICenterWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WelcomePanel extends HorizontalPanel implements ICenterWidget
{
    private VerticalPanel rootPanel = new VerticalPanel();
    private Button btnMapCreator;
    private final MainWindow mainWindow;

    public WelcomePanel(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;

        initialize();
    }

    private void initialize()
    {
        Button btnHotseatGame = new Button();
        btnHotseatGame.setHTML("<H3>HotSeat game</H3>Play a mock hotseat game");
        btnHotseatGame.addClickHandler(new ClickHandler()
        {

            @Override
            public void onClick(ClickEvent event)
            {
                mainWindow.setCurrentWidget(mainWindow.getHotseatGame());
            }
        });
        rootPanel.setSpacing(10);
        rootPanel.add(btnHotseatGame);

        Button btnvisitWikihelpWrite = new Button(
                "<H3>Visit Wiki</H3>Help write our UI using wiki");
        btnvisitWikihelpWrite.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent arg0)
            {
                mainWindow.setCurrentWidget(mainWindow.getWikiPage());
            }
        });
        rootPanel.add(btnvisitWikihelpWrite);

        btnMapCreator = new Button();
        btnMapCreator.setHTML("<H3>MapCreator</H3>create a new map");
        btnMapCreator.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                mainWindow.setCurrentWidget(mainWindow.getMapEditor());
            }
        });
        rootPanel.add(btnMapCreator);

        Button btnJavadoc = new Button();
        btnJavadoc.setHTML("<H3>View Javadoc</H3>create a new map");
        btnJavadoc.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                mainWindow.setCurrentWidget(mainWindow.getJavadoc());
            }
        });
        rootPanel.add(btnJavadoc);

        Button btnLobby = new Button();
        btnLobby.setHTML("<H3>Lobby</H3>Find games and players");
        btnLobby.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                mainWindow.setCurrentWidget(mainWindow.getLobby());
            }
        });
        rootPanel.add(btnLobby);
    }

    @Override
    public Panel getRootWidget()
    {
        return rootPanel;
    }
}
