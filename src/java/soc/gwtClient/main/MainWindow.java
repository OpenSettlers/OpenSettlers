package soc.gwtClient.main;

import soc.common.internationalization.ClientInternationalization;
import soc.common.internationalization.I18n;
import soc.gwtClient.editor.SvgMapEditor;
import soc.gwtClient.game.widgetsBitmap.main.HotSeatGameWidget;
import soc.gwtClient.lobby.GameLobby;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class MainWindow implements EntryPoint
{
    DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    HorizontalPanel menu = new HorizontalPanel();
    LayoutPanel centerWidget = new LayoutPanel();
    CenterWidget mapEditor;
    CenterWidget welcomePanel;
    CenterWidget currentWidget;
    HotSeatGameWidget hotseatGame;
    CenterWidget wikiPage;
    CenterWidget lobby;
    CenterWidget javadoc;

    private final MenuBar rootMenuBar = new MenuBar(true);
    private MenuItem editorMenutItem;
    private MenuItem hotseatMenuItem;
    private MenuItem rootMenuItem;

    /**
     * @return the hotseatGame
     */
    public HotSeatGameWidget getHotseatGame()
    {
        return hotseatGame;
    }

    @Override
    public void onModuleLoad()
    {
        I18n.initialize(new ClientInternationalization());
        createMenu();
        mapEditor = new SvgMapEditor();
        welcomePanel = new WelcomePanel(this);
        hotseatGame = new HotSeatGameWidget();
        wikiPage = new WikiPanel();
        lobby = new GameLobby();
        javadoc = new JavadocPanel();

        currentWidget = welcomePanel;
        centerWidget.add(currentWidget.getRootWidget());
        rootPanel.addNorth(menu, 3);
        rootMenuBar.setAutoOpen(false);
        menu.add(rootMenuBar);
        MenuBar firstMenu = new MenuBar(true);

        rootMenuItem = new MenuItem("New menu", false, firstMenu);
        rootMenuItem.setHTML("OpenSettlers");

        editorMenutItem = new MenuItem("New item", false, (Command) null);
        firstMenu.addItem(editorMenutItem);
        editorMenutItem.setHTML("MapCreator");

        hotseatMenuItem = new MenuItem("New item", false, (Command) null);
        firstMenu.addItem(hotseatMenuItem);
        hotseatMenuItem.setHTML("Hotseat game");
        rootMenuBar.addItem(rootMenuItem);

        PushButton btnWelcomePanel = new PushButton("welcomePanel");
        btnWelcomePanel.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                setCurrentWidget(welcomePanel);
            }
        });
        menu.add(btnWelcomePanel);
        rootPanel.add(centerWidget);

        RootLayoutPanel.get().add(rootPanel);
    }

    /**
     * @return the javadoc
     */
    public CenterWidget getJavadoc()
    {
        return javadoc;
    }

    /**
     * @return the mapEditor
     */
    public CenterWidget getMapEditor()
    {
        return mapEditor;
    }

    /**
     * @return the lobby
     */
    public CenterWidget getLobby()
    {
        return lobby;
    }

    private void createMenu()
    {

    }

    /**
     * @return the wikiPage
     */
    public CenterWidget getWikiPage()
    {
        return wikiPage;
    }

    public void setCurrentWidget(CenterWidget newCenterWidget)
    {
        centerWidget.remove(currentWidget.getRootWidget());

        currentWidget = newCenterWidget;

        centerWidget.add(currentWidget.getRootWidget());
    }
}
