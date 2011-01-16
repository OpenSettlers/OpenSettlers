package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.board.ports.PortListChangedEvent;
import soc.common.board.ports.PortListChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.PortAmountWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PortAmountBitmapWidget implements PortAmountWidget,
        PortListChangedEventHandler
{
    private VerticalPanel rootPanel = new VerticalPanel();
    private Image imgPort = new Image(Resources.icons().port());
    private Label lblPortAmount = new Label();
    private GamePlayer player;

    public PortAmountBitmapWidget(GamePlayer player)
    {
        super();
        this.player = player;

        updateUI();

        rootPanel.add(imgPort);
        rootPanel.add(lblPortAmount);

        player.getPorts().addPortListChangedEventHandler(this);
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

}
