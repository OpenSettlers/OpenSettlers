package org.soc.gwt.client.game;

import org.soc.common.presenters.DevelopmentCardAmountPresenter.DevelopmentCardAmountView;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DevelopmentCardAmountGwt implements DevelopmentCardAmountView {
  private Image devcardImage = new Image(R.icons().developmentCardBack16());
  private Label labelAmountDevcards = new Label();
  private VerticalPanel rootPanel = new VerticalPanel();

  public DevelopmentCardAmountGwt() {
    devcardImage.setSize("16px", "16px");
    rootPanel.add(devcardImage);
    rootPanel.add(labelAmountDevcards);
  }
  @Override public void setAmountDevelopmentCards(int amount) {
    labelAmountDevcards.setText(Integer.toString(amount));
  }
}
