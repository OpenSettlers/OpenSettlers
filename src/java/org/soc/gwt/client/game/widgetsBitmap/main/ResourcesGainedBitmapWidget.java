package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.views.behaviour.gameWidget.received.RollDiceResult;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.ResourcesGainedWidget;

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
    private GameWidget gameWidget;
    private Button btnClose = new Button("Close");
    private RollDiceResult rollDiceResult;
    private Label lblAmountResources = new Label();

    public ResourcesGainedBitmapWidget(GameWidget gameWidget)
    {
        this.gameWidget = gameWidget;

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
        Point2D location = gameWidget.getPlayerStuffWidget()
                .getDiceWidgetTopLeftPosition();
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
