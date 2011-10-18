package org.soc.gwt.client.game;

import org.soc.common.game.*;
import org.soc.common.presenters.PortStockPresenter.PortStockView;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class PortsGwt implements PortStockView,
        MouseOutHandler, MouseOverHandler, IsWidget
{
  private VerticalPanel rootPanel = new VerticalPanel();
  private Image imgPort = new Image(R.icons().port16());
  private Label lblPortAmount = new Label();
  private PortListToolTip portListDetailWidget;
  private GameWidget gameWidget;
  private int amountPorts = 0;

  public PortsGwt() {
    //portListDetailWidget = new PortListToolTip(gameWidget, player);
    rootPanel.add(imgPort);
    rootPanel.add(lblPortAmount);
    rootPanel.addDomHandler(this, MouseOutEvent.getType());
    rootPanel.addDomHandler(this, MouseOverEvent.getType());
  }
  @Override public Widget asWidget() {
    return rootPanel;
  }
  private void updateUI() {
    lblPortAmount.setText(Integer.toString(amountPorts - 1));
  }
  @Override public void onMouseOut(MouseOutEvent event) {
    gameWidget.toolTipManager().hide(portListDetailWidget);
  }
  @Override public void onMouseOver(MouseOverEvent event) {
    gameWidget.toolTipManager().show(portListDetailWidget);
  }
  @Override public void addPort(Port port) {
    updateUI();
  }
  @Override public void removePort(Port port) {
    updateUI();
  }
}
