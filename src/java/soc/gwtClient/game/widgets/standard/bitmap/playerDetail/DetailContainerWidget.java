package soc.gwtClient.game.widgets.standard.bitmap.playerDetail;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.actions.gameAction.turnActions.standard.TradeBank;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.PlayerDetailContainerWidget;
import soc.gwtClient.game.abstractWidgets.PlayerDetailWidget;
import soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions.BuildCityWidget;
import soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions.BuildRoadDetailWidget;
import soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions.BuildTownDetailWidget;
import soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions.EndTurnDetailWidget;
import soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions.MoveRobberDetailWidget;
import soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions.RollDiceDetailWidget;
import soc.gwtClient.game.widgets.standard.bitmap.playerDetail.actions.TradeBankDetailWidget;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/*
 * Popup shown at the right side from the player details widget. Manages PlayerDetailWidgets 
 * who give clues in what is currently happening in the game. Supports a mouseover widget to 
 * give details about a player's possessions when moused over a player widget.
 */
public class DetailContainerWidget extends PopupPanel implements
        PlayerDetailContainerWidget
{
    // Player of the container
    private GamePlayer player;

    // When mousing over, the moused over widget is overlayed on the current
    // widget if present
    private HorizontalPanel rootPanel = new HorizontalPanel();

    // Panel containing all the widgets for permanent viewing
    private HorizontalPanel actionPanel = new HorizontalPanel();
    private SimplePanel mouseOverPanel = new SimplePanel();
    private GamePanel gamePanel;

    private PlayerDetailWidget playerDetailWidget;
    private ResourcesGainedDetailWidget resourcesGained;
    private static List<Command> commands = new ArrayList<Command>();

    public DetailContainerWidget(GamePlayer player, GamePanel gamePanel)
    {
        super();
        this.player = player;
        this.gamePanel = gamePanel;

        rootPanel.add(actionPanel);
        rootPanel.add(mouseOverPanel);
        add(rootPanel);
    }

    /*
     * Shows a list of resources gained by a player. Only displays when there
     * are one or more resources contained in given ResourceList.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void showActionWidget(GameAction action)
    {
        PlayerDetailWidget widget = createDetailWidget(action);
        if (widget != null)
        {
            actionPanel.add(widget);
            Command command = new TimedCommand(widget,
                    (commands.size() + 1) * 2000);
            System.out.println((commands.size() + 1) * 2000);
            DeferredCommand.addCommand(command);
            commands.add(command);
            setPositionAndShow();
        }
    }

    private PlayerDetailWidget createDetailWidget(GameAction gameAction)
    {
        if (gameAction instanceof EndTurn)
            return new EndTurnDetailWidget(gamePanel, (EndTurn) gameAction);

        if (gameAction instanceof BuildTown)
            return new BuildTownDetailWidget(gamePanel, (BuildTown) gameAction);

        if (gameAction instanceof BuildRoad)
            return new BuildRoadDetailWidget(gamePanel, (BuildRoad) gameAction);

        if (gameAction instanceof BuildCity)
            return new BuildCityWidget(gamePanel, (BuildCity) gameAction);

        if (gameAction instanceof PlaceRobber)
            return new MoveRobberDetailWidget(gamePanel,
                    (PlaceRobber) gameAction);

        if (gameAction instanceof TradeBank)
            return new TradeBankDetailWidget(gamePanel, (TradeBank) gameAction);

        if (gameAction instanceof RollDice)
            return new RollDiceDetailWidget(gamePanel, (RollDice) gameAction);

        return null;
    }

    public void setPositionAndShow()
    {
        Point2D location = gamePanel.getTopRightPlayerInfoBoxPosition(player);
        setPopupPosition(location.getX(), location.getY());
        this.show();
    }

    public void hideMouseOverWidget()
    {
        mouseOverPanel.setVisible(false);
        mouseOverPanel.remove(mouseOverPanel);
        hide();
    }

    public void showMouseOverWidget(PlayerDetailWidget playerDetailWidget)
    {
        this.playerDetailWidget = playerDetailWidget;
        mouseOverPanel.setWidget(playerDetailWidget);
        setPositionAndShow();
    }

    @Override
    public void hide()
    {
        if (mouseOverPanel.getWidget() == null
                && actionPanel.getWidgetCount() == 0)
            super.hide();
    }

    @Override
    public void hideCurrentWidget()
    {
        hide();
    }

    private class TimedCommand implements Command
    {
        PlayerDetailWidget widget;

        public TimedCommand(PlayerDetailWidget widget, int delay)
        {
            super();
            this.widget = widget;
            timer.schedule(delay);
        }

        Timer timer = new Timer()
        {
            @Override
            public void run()
            {
                actionPanel.remove(widget);
                commands.remove(this);
            }
        };

        @Override
        public void execute()
        {
            hide();
        }
    }
}
