package org.soc.gwt.client.game;

import org.soc.common.game.Port;
import org.soc.common.presenters.PortStockPresenter.PortStockView;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.PortListToolTip;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PortStockGwt implements PortStockView {
  private VerticalPanel rootPanel = new VerticalPanel();
  private Image imgPort = new Image(R.icons().port16());
  private Label lblPortAmount = new Label();
  private PortListToolTip portListDetailWidget;
  private int amount = 0;

  public PortStockGwt() {
    //    portListDetailWidget = new PortListToolTip(gameWidget, player);
    //    updateUI();
    rootPanel.add(imgPort);
    rootPanel.add(lblPortAmount);
    //    rootPanel.addDomHandler(this, MouseOutEvent.getType());
    //    rootPanel.addDomHandler(this, MouseOverEvent.getType());
  }
  @Override public void addPort(Port port) {
    amount++;
    updateAmount();
  }
  @Override public void removePort(Port port) {
    amount--;
    updateAmount();
  }
  private void updateAmount() {
    lblPortAmount.setText(Integer.toString(amount - 1));
  }
  //  @Override public void onMouseOut(MouseOutEvent event) {
  //    gameWidget.toolTipManager().hide(portListDetailWidget);
  //  }
  //  @Override public void onMouseOver(MouseOverEvent event) {
  //    gameWidget.toolTipManager().show(portListDetailWidget);
  //  }
}
