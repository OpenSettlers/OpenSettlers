package soc.gwt.client.game.widgetsBitmap.tooltips;

import org.adamtacy.client.ui.effects.impl.Fade;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.generic.Point2D;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;
import soc.common.views.widgetsInterface.payerInfo.PlayerDetailContainerWidget;
import soc.gwt.client.game.widgetsBitmap.playerInfo.ResourcesGainedDetailWidget;

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
    private GameWidget gameWidget;

    private ActionDetailWidget playerDetailWidget;
    private ResourcesGainedDetailWidget resourcesGained;

    public DetailContainerWidget(GamePlayer player, GameWidget gameWidget)
    {
        super();
        this.player = player;
        this.gameWidget = gameWidget;

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
    public void showActionWidget(ActionDetailWidget actionDetailWidget)
    {
        if (actionDetailWidget != null)
        {
            actionPanel.add(actionDetailWidget);
            Command command = new TimedCommand(actionDetailWidget, 2000);
            DeferredCommand.addCommand(command);
            setPositionAndShow();
        }
    }

    public void setPositionAndShow()
    {
        Point2D location = gameWidget.getTopRightPlayerInfoBoxPosition(player);
        setPopupPosition(location.getX(), location.getY());
        this.show();
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
        ActionDetailWidget widget;
        boolean effectEnded = false;

        public TimedCommand(ActionDetailWidget widget, int delay)
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
                if (effectEnded)
                {
                    actionPanel.remove(widget);
                }
                else
                {
                    Fade fade = new Fade(widget.asWidget().getElement());
                    fade.play();
                    effectEnded = true;
                    timer.schedule(2000);
                }
            }
        };

        @Override
        public void execute()
        {
            hide();
        }
    }
}
