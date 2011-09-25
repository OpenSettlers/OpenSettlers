package org.soc.gwt.client.game.pieces;

import org.soc.common.game.GamePlayer;
import org.soc.common.presenters.LongestRoadPresenter.LongestRoadView;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LongestRoadGwt implements LongestRoadView {
  private Image longestRoadImage = new Image(R.icons().longestRoad16());
  private Label lblLongestRoadLength = new Label();
  private VerticalPanel rootPanel = new VerticalPanel();

  public LongestRoadGwt() {
    longestRoadImage.setSize("16px", "16px");
    rootPanel.add(longestRoadImage);
    rootPanel.add(lblLongestRoadLength);
  }
  @Override public void setLength(int length) {
    lblLongestRoadLength.setText(Integer.toString(length));
  }
  @Override public void setPlayer(GamePlayer player) {
    // TODO Adapt icon to players' color
  }
  @Override public void setHasLongestRoad() {
    // TODO: visualize if player has longest road
  }
  @Override public void setLostLongestRoad() {
    // TODO: visualize if player has longest road
  }
}
