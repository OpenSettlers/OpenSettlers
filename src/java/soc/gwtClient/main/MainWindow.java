package soc.gwtClient.main;


import java.util.ArrayList;

import soc.common.board.Board;
import soc.common.board.ports.ThreeToOnePort;
import soc.common.board.ports.TwoToOneResourcePort;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.developmentCards.Monopoly;
import soc.common.game.developmentCards.RoadBuilding;
import soc.common.game.developmentCards.Soldier;
import soc.common.game.developmentCards.VictoryPoint;
import soc.common.game.developmentCards.YearOfPlenty;
import soc.common.game.variants.IVariant;
import soc.common.game.variants.Standard;
import soc.gwtClient.editor.SvgMapEditor;
import soc.gwtClient.game.ICenterWidget;
import soc.gwtClient.game.widgets.HotSeatGamePanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;

public class MainWindow implements EntryPoint
{
    
    DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    HorizontalPanel menu = new HorizontalPanel();
    LayoutPanel centerWidget = new LayoutPanel();
    HorizontalPanel statusBar = new HorizontalPanel();
    ICenterWidget mapEditor;
    ICenterWidget welcomePanel;
    ICenterWidget currentWidget;
    ICenterWidget hotseatGame;
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
        createMenu();
        mapEditor = new SvgMapEditor();
        welcomePanel = new WelcomePanel(this);
        hotseatGame = new HotSeatGamePanel(createGame());
        
        Label lblSomeStatusLabel = new Label("Some text here to show we have an awesome statusbar");
        statusBar.add(lblSomeStatusLabel);
        
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
        rootPanel.addSouth(statusBar, 2);
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
    private void createMenu()
    {
        
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
        ArrayList<IVariant> rules = new ArrayList<IVariant>();
        rules.add(new Standard(result));
        result.setRuleSets(rules);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add
        ((Player)
            new Player()
                .setColor("yellow")
                .setId(1)
                .setName("Piet")
        );
        players.add
        ((Player)
            new Player()
                .setColor("white")
                .setId(1)
                .setName("Kees")
        );
        players.add
        ((Player)
            new Player()
                .setColor("green")
                .setId(1)
                .setName("Truus")
        );
        players.add((Player)
            new Player()
                .setColor("red")
                .setId(1)
                .setName("Klaas")
        );
        players.add
        ((Player)
            new Player()
                .setColor("blue")
                .setId(1)
                .setName("Henk")
        );
        
        result.setBoard(new Board(8,8));

        result.setPlayers(players);
        
        result.makeRulesPermanent();
        
        result.getPlayers().get(0).getDevelopmentCards().add(new Soldier());
        result.getPlayers().get(0).getDevelopmentCards().add(new Monopoly());
        result.getPlayers().get(0).getDevelopmentCards().add(new YearOfPlenty());
        result.getPlayers().get(0).getDevelopmentCards().add(new RoadBuilding());
        result.getPlayers().get(0).getDevelopmentCards().add(new VictoryPoint());
        
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

        result.getPlayers().get(0).getPorts().add(new TwoToOneResourcePort(new Clay()));
        result.getPlayers().get(0).getPorts().add(new TwoToOneResourcePort(new Timber()));
        result.getPlayers().get(0).getPorts().add(new ThreeToOnePort());
        return result;
    }
}
