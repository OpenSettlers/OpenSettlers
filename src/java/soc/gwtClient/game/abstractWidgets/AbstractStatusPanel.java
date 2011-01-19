package soc.gwtClient.game.abstractWidgets;

import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.StatusChangedEventHandler;
import soc.common.game.TurnChangedEventHandler;
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedHandler;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStatusPanel implements StatusPanel,
        GamePhaseChangedEventHandler, TurnPhaseChangedHandler,
        TurnChangedEventHandler, StatusChangedEventHandler
{
    protected VerticalPanel rootPanel = new VerticalPanel();
    protected Label lblAction = new Label("Action todo");
    protected Label lblGamePhase = new Label("Current game phase goes here");
    protected Label lblTurnPhase = new Label("Current turn phase goes here");
    protected Label lblStatus = new Label("Status of the game");
    protected Label lblTurn = new Label("Status of the game");
    protected Image imgGamePhase = new Image();
    protected Image imgTurnPhase = new Image();
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
        gameTurnPhasePanel.add(imgGamePhase);
        gameTurnPhasePanel.add(lblGamePhase);
        gameTurnPhasePanel.add(new Label(" --> "));
        gameTurnPhasePanel.add(imgTurnPhase);
        gameTurnPhasePanel.add(lblTurnPhase);
        gameTurnPhasePanel
                .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

        turnTodoPanel.add(lblTurn);
        turnTodoPanel.add(lblAction);

        rootPanel.add(turnTodoPanel);
        rootPanel.add(gameTurnPhasePanel);

        lblGamePhase.setStyleName("gamePhaseLabel");
        lblStatus.setStyleName("gameStatusLabel");

        gamePanel.getGame().getActionsQueue().addQueueChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);
        gamePanel.getGame().addTurnPhaseChangedHandler(this);
        gamePanel.getGame().addTurnChangedEventHandler(this);
        gamePanel.getGame().addStatusChangedEventHandler(this);
        gameTurnPhasePanel.setHeight("2em");
        turnTodoPanel.setHeight("2em");
        rootPanel.setWidth("100%");
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}