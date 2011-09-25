package org.soc.gwt.client.game;

import org.soc.common.game.pieces.City;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockCityGwt extends AbstractStockPieceView<City> {
  private Image imageCity = new Image(R.icons().city16());
  private Label labelAmountCities = new Label();
  private int amountCities = 0;

  public StockCityGwt() {
    imageCity.setSize("16px", "16px");
    updateUI();
    rootPanel.add(imageCity);
    rootPanel.add(labelAmountCities);
  }
  private void updateUI() {
    labelAmountCities.setText(Integer.toString(amountCities));
  }
  @Override public void addPiece(City piece) {
    updateUI();
  }
  @Override public void removePiece(City piece) {
    updateUI();
  }
}
