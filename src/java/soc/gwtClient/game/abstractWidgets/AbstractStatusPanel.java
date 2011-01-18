package soc.gwtClient.game.abstractWidgets;

import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.StatusChangedEventHandler;
import soc.common.game.TurnChangedEventHandler;
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedHandler;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStatusPanel implements StatusPanel,
        GamePhaseChangedEventHandler, TurnPhaseChangedHandler,
        TurnChangedEventHandler, StatusChangedEventHandler
{
    protected DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    protected Label lblAction = new Label("Action todo");
    protected Label lblGamePhase = new Label("Current game phase goes here");
    protected Label lblTurnPhase = new Label("Current turn phase goes here");
    protected Label lblStatus = new Label("Status of the game");
    protected Label lblTurn = new Label("Status of the game");
    protected HorizontalPanel gameTurnPhasePanel = new HorizontalPanel();
    protected HorizontalPanel turnTodoPanel = new HorizontalPanel();
    protected GamePanel gamePanel;

    protected abstract ComplexPanel createRootPanel();

    public AbstractStatusPanel(GamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

        gameTurnPhasePanel.add(lblStatus);
        gameTurnPhasePanel.add(new Label(" "));
        gameTurnPhasePanel.add(lblGamePhase);
        gameTurnPhasePanel.add(new Label(" --> "));
        gameTurnPhasePanel.add(lblTurnPhase);

        turnTodoPanel.add(lblTurn);
        turnTodoPanel.add(lblAction);

        rootPanel.addSouth(gameTurnPhasePanel, 1);
        rootPanel.add(turnTodoPanel);

        lblGamePhase.setStyleName("gamePhaseLabel");
        lblStatus.setStyleName("gameStatusLabel");

        gamePanel.getGame().getActionsQueue().addQueueChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);
        gamePanel.getGame().addTurnPhaseChangedHandler(this);
        gamePanel.getGame().addTurnChangedEventHandler(this);
        gamePanel.getGame().addStatusChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}