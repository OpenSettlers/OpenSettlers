package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.core.GenericList.Adds.Added;
import org.soc.common.core.GenericList.Removes.Removed;
import org.soc.common.game.*;
import org.soc.common.game.pieces.*;
import org.soc.common.game.pieces.Piece.PlayerPiece;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.*;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;

public class StockCityBitmapWidget extends AbstractStockItemWidget
{
  Image cityImage = new Image(R.icons().city16());
  Label amountCities = new Label();
  City city = new City();

  public StockCityBitmapWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    cityImage.setSize("16px", "16px");
    updateUI();
    rootPanel.add(cityImage);
    rootPanel.add(amountCities);
    player.stock().cities().addAddedHandler(new Added<City>() {
      @Override public void added(City item) {
        updateUI();
      }
    });
    player.stock().cities().addRemovedHandler(new Removed<City>() {
      @Override public void removed(City item) {
        updateUI();
      }
    });
  }
  @Override public PlayerPiece getStockPiece()
  {
    return city;
  }
  private void updateUI() {
    amountCities.setText(Integer.toString(player.stock().cities()
            .size()));
  }
  @Override protected ToolTip createToolTip()
  {
    return new CityStockToolTip(gameWidget, player);
  }
}
