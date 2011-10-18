package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.core.GenericList.Adds.Added;
import org.soc.common.core.GenericList.Removes.Removed;
import org.soc.common.game.*;
import org.soc.common.game.pieces.Piece.PlayerPiece;
import org.soc.common.game.pieces.*;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.*;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;

public class StockRoadBitmapWidget extends AbstractStockItemWidget
{
  private Road road = new Road();
  private Image roadImage = new Image(R.icons().road16());
  private Label roadAmount = new Label();

  public StockRoadBitmapWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    roadImage.setSize("16px", "16px");
    updateUI();
    rootPanel.add(roadImage);
    rootPanel.add(roadAmount);
    player.stock().roads().addAddedHandler(new Added<Road>() {
      @Override public void added(Road item) {
        updateUI();
      }
    });
    player.stock().roads().addRemovedHandler(new Removed<Road>() {
      @Override public void removed(Road item) {
        updateUI();
      }
    });
  }
  @Override public PlayerPiece getStockPiece()
  {
    return road;
  }
  private void updateUI()
  {
    roadAmount.setText(Integer
            .toString(player.stock().roads().size()));
  }
  @Override protected ToolTip createToolTip()
  {
    return new RoadStockToolTip(gameWidget, player);
  }
}
