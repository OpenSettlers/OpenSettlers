package soc.gwtClient.game.widgetsAbstract;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsBitmap.tooltips.VictoryPointsToolTip;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.VictoryPointAmountWidget;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractVictoryPointsWidget implements
        VictoryPointAmountWidget, MouseOutHandler, MouseOverHandler
{
    protected ComplexPanel rootPanel;
    protected GamePlayer player;
    protected GameWidget gamePanel;
    protected ToolTip vpToolTip;

    public AbstractVictoryPointsWidget(GameWidget gamePanel, GamePlayer player)
    {
        this.gamePanel = gamePanel;
        this.player = player;

        rootPanel = createRootPanel();
        vpToolTip = new VictoryPointsToolTip(gamePanel, player);

        rootPanel.addDomHandler(this, MouseOutEvent.getType());
        rootPanel.addDomHandler(this, MouseOverEvent.getType());
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        gamePanel.getToolTipManager().hideToolTip(vpToolTip);
    }

    @Override
    public void onMouseOver(MouseOverEvent event)
    {
        gamePanel.getToolTipManager().showToolTip(vpToolTip);
    }
}