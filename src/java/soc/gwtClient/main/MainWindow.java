package soc.gwtClient.main;

import soc.common.board.ports.ThreeToOnePort;
import soc.common.board.ports.TwoToOneResourcePort;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.game.Game;
import soc.common.game.GameBoard;
import soc.common.game.GamePlayerImpl;
import soc.common.game.developmentCards.standard.Monopoly;
import soc.common.game.developmentCards.standard.RoadBuilding;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.common.game.developmentCards.standard.YearOfPlenty;
import soc.common.internationalization.ClientInternationalization;
import soc.common.internationalization.I18n;
import soc.common.server.data.UnregisteredUser;
import soc.gwtClient.editor.SvgMapEditor;
import soc.gwtClient.game.ICenterWidget;
import soc.gwtClient.game.widgets.HotSeatGamePanel;
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
    ICenterWidget mapEditor;
    ICenterWidget welcomePanel;
    ICenterWidget currentWidget;
    ICenterWidget hotseatGame;
    ICenterWidget wikiPage;
    ICenterWidget lobby;

    private final MenuBar rootMenuBar = new MenuBar(true);
    private MenuItem editorMenutItem;
    private MenuItem hotseatMenuItem;
    private MenuItem rootMenuItem;

    /**
     * @return the hotseatGame
     */
    public ICenterWidget getHotseatGame()
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
        hotseatGame = new HotSeatGamePanel(createGame());
        wikiPage = new WikiPanel();
        lobby = new GameLobby();

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
     * @return the mapEditor
     */
    public ICenterWidget getMapEditor()
    {
        return mapEditor;
    }

    /**
     * @return the lobby
     */
    public ICenterWidget getLobby()
    {
        return lobby;
    }

    private void createMenu()
    {

    }

    /**
     * @return the wikiPage
     */
    public ICenterWidget getWikiPage()
    {
        return wikiPage;
    }

    public void setCurrentWidget(ICenterWidget newCenterWidget)
    {
        centerWidget.remove(currentWidget.getRootWidget());

        currentWidget = newCenterWidget;

        centerWidget.add(currentWidget.getRootWidget());
    }

    private Game createGame()
    {
        Game result = new Game();
        result.getPlayers().add(
                (GamePlayerImpl) new GamePlayerImpl().setUser(
                        new UnregisteredUser().setId(1).setName("Piet"))
                        .setColor("yellow"));
        result.getPlayers().add(
                (GamePlayerImpl) new GamePlayerImpl().setUser(
                        new UnregisteredUser().setId(1).setName("Kees"))
                        .setColor("white"));
        result.getPlayers().add(
                (GamePlayerImpl) new GamePlayerImpl().setUser(
                        new UnregisteredUser().setId(1).setName("Truus"))
                        .setColor("green"));
        result.getPlayers().add(
                (GamePlayerImpl) new GamePlayerImpl().setUser(
                        new UnregisteredUser().setId(1).setName("Klaas"))
                        .setColor("red"));
        result.getPlayers().add(
                (GamePlayerImpl) new GamePlayerImpl().setUser(
                        new UnregisteredUser().setId(1).setName("Henk"))
                        .setColor("blue"));

        result.setBoard(new GameBoard(8, 8));

        result.getPlayers().get(0).getDevelopmentCards().add(new Soldier());
        result.getPlayers().get(0).getDevelopmentCards().add(new Monopoly());
        result.getPlayers().get(0).getDevelopmentCards()
                .add(new YearOfPlenty());
        result.getPlayers().get(0).getDevelopmentCards()
                .add(new RoadBuilding());
        result.getPlayers().get(0).getDevelopmentCards()
                .add(new VictoryPoint());

        result.getPlayers().get(0).getResources().add(new Wheat());
        result.getPlayers().get(0).getResources().add(new Wheat());
        result.getPlayers().get(0).getResources().add(new Wheat());
        result.getPlayers().get(0).getResources().add(new Wheat());

        result.getPlayers().get(0).getResources().add(new Ore());
        result.getPlayers().get(0).getResources().add(new Ore());
        result.getPlayers().get(0).getResources().add(new Ore());

        result.getPlayers().get(0).getResources().add(new Clay());
        result.getPlayers().get(0).getResources().add(new Clay());
        result.getPlayers().get(0).getResources().add(new Clay());
        result.getPlayers().get(0).getResources().add(new Clay());
        result.getPlayers().get(0).getResources().add(new Clay());
        result.getPlayers().get(0).getResources().add(new Clay());

        result.getPlayers().get(0).getResources().add(new Sheep());
        result.getPlayers().get(0).getResources().add(new Sheep());

        result.getPlayers().get(0).getPorts().add(
                new TwoToOneResourcePort(new Clay()));
        result.getPlayers().get(0).getPorts().add(
                new TwoToOneResourcePort(new Timber()));
        result.getPlayers().get(0).getPorts().add(new ThreeToOnePort());

        result.start();
        return result;
    }
}
