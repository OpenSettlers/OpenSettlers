package org.soc.gwt.client.game;

import org.soc.common.presenters.HandAmounPresenter.HandAmountView;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HandAmountGwt implements HandAmountView {
  private VerticalPanel rootPanel = new VerticalPanel();
  private Image cardImage = new Image(R.icons().blankCard16());
  private Label lblAmountResources = new Label();

  public HandAmountGwt() {
    rootPanel.add(cardImage);
    rootPanel.add(lblAmountResources);
  }
  @Override public void setAmountHandCards(int amount) {
    lblAmountResources.setText(Integer.toString(amount));
  }
}
