package org.soc.gwt.client.game;

import org.soc.common.board.PortListChangedEvent;
import org.soc.common.board.PortListChangedEvent.PortListChangedHandler;
import org.soc.common.game.Port;
import org.soc.common.presenters.PortStockPresenter.PortStockView;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.PortListToolTip;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PortsGwt implements PortStockView,
        PortListChangedHandler, MouseOutHandler, MouseOverHandler, IsWidget
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
  @Override public void onPortListChanged(PortListChangedEvent event) {
    updateUI();
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
