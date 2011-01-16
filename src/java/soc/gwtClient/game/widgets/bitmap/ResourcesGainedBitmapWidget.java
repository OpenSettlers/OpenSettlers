package soc.gwtClient.game.widgets.bitmap;

import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.behaviour.received.RollDiceResult;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcesGainedWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResourcesGainedBitmapWidget implements ResourcesGainedWidget,
        ClickHandler
{
    private PopupPanel popup = new PopupPanel();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private GamePanel gamePanel;
    private Button btnClose = new Button("Close");
    private RollDiceResult rollDiceResult;
    private Label lblAmountResources = new Label();

    public ResourcesGainedBitmapWidget(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;

        rootPanel.add(lblAmountResources);
        rootPanel.add(btnClose);
        rootPanel.setHeight("3em");
        btnClose.addClickHandler(this);

        popup.add(rootPanel);
    }

    public void update(RollDiceResult rollDiceResult)
    {
        this.rollDiceResult = rollDiceResult;
        setPositionAndShow();
    }

    private void setPositionAndShow()
    {
        popup.show();
        Point2D location = gamePanel.getTopLeftDiceWidgetPosition();
        popup.setPopupPosition(location.getX() - popup.getOffsetWidth(),
                location.getY());
    }

    @Override
    public Widget asWidget()
    {
        return popup;
    }

    @Override
    public void onClick(ClickEvent event)
    {
        popup.hide();
        rollDiceResult.doneResources();
    }

    @Override
    public void show()
    {
        popup.show();
    }
}
