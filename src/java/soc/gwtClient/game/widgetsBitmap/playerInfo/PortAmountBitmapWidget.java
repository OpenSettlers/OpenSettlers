package soc.gwtClient.game.widgetsBitmap.playerInfo;

import soc.common.board.ports.PortListChangedEvent;
import soc.common.board.ports.PortListChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.PortAmountWidget;
import soc.gwtClient.game.widgetsBitmap.tooltips.PortListToolTip;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PortAmountBitmapWidget implements PortAmountWidget,
                PortListChangedEventHandler, MouseOutHandler, MouseOverHandler
{
    private VerticalPanel rootPanel = new VerticalPanel();
    private Image imgPort = new Image(Resources.icons().port16());
    private Label lblPortAmount = new Label();
    private GamePlayer player;
    private PortListToolTip portListDetailWidget;
    private GameWidget gameWidget;

    public PortAmountBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super();
        this.gameWidget = gameWidget;
        this.player = player;

        portListDetailWidget = new PortListToolTip(gameWidget, player);
        updateUI();

        rootPanel.add(imgPort);
        rootPanel.add(lblPortAmount);

        player.getPorts().addPortListChangedEventHandler(this);

        rootPanel.addDomHandler(this, MouseOutEvent.getType());
        rootPanel.addDomHandler(this, MouseOverEvent.getType());
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    private void updateUI()
    {
        lblPortAmount.setText(Integer.toString(player.getPorts().size() - 1));
    }

    @Override
    public void onPortsChanged(PortListChangedEvent event)
    {
        updateUI();
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        gameWidget.getToolTipManager().hideToolTip(portListDetailWidget);
    }

    @Override
    public void onMouseOver(MouseOverEvent event)
    {
        gameWidget.getToolTipManager().showToolTip(portListDetailWidget);
    }

}
