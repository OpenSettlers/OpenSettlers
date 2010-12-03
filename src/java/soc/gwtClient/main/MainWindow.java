package soc.gwtClient.main;


import java.util.ArrayList;

import soc.common.board.Board;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.rules.IRuleSet;
import soc.common.game.rules.Standard;
import soc.gwtClient.editor.SvgMapEditor;
import soc.gwtClient.game.ICenterWidget;
import soc.gwtClient.game.widgets.standard.bitmap.BitmapGamePanel;

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
        hotseatGame = new BitmapGamePanel(createGame());
        
        Label lblSomeStatusLabel = new Label("Some text here to show we have an awesome statusbar");
        statusBar.add(lblSomeStatusLabel);
        
        currentWidget = welcomePanel;
        centerWidget.add(currentWidget.getRootWidget());
        rootPanel.addNorth(menu, 3);
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
        Label title = new Label("OpenSettlers");
        title.setStyleName("label-title");
        menu.add(title);
        
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
        ArrayList<IRuleSet> rules = new ArrayList<IRuleSet>();
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
        
        
        
        return result;
    }
}
