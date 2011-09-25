package org.soc.gwt.client.game.pieces;

import org.soc.common.presenters.ArmyPresenter.ArmyView;
import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ArmyGwt implements ArmyView, MouseOutHandler, MouseOverHandler {
  private Image largestAmryImage = new Image(R.icons().robber16());
  private Label amountSoldiers = new Label();
  private ToolTip toolTip;
  protected VerticalPanel rootPanel = new VerticalPanel();

  public ArmyGwt() {
    largestAmryImage.setSize("16px", "16px");
    rootPanel.add(largestAmryImage);
    rootPanel.add(amountSoldiers);
    rootPanel.addDomHandler(this, MouseOutEvent.getType());
    rootPanel.addDomHandler(this, MouseOverEvent.getType());
  }
  @Override public void onMouseOut(MouseOutEvent event) {
    //    gameWidget.toolTipManager().hide(toolTip);
  }
  @Override public void onMouseOver(MouseOverEvent event) {
    //    gameWidget.toolTipManager().show(toolTip);
  }
  @Override public void setSize(int size) {
    amountSoldiers.setText(Integer.toString(size));
  }
  @Override public void setHasBiggestArmy() {
    // TODO Auto-generated method stub
  }
  @Override public void setLostBiggestArmy() {
    // TODO Auto-generated method stub
  }
}
