package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.core.GenericList.Adds.Added;
import org.soc.common.core.GenericList.Removes.Removed;
import org.soc.common.game.*;
import org.soc.common.game.pieces.Piece.PlayerPiece;
import org.soc.common.game.pieces.*;
import org.soc.common.views.widgetsInterface.actions.*;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.*;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;

public class StockTownBitmapWidget extends AbstractStockItemWidget
{
  private Image townImage = new Image(R.icons().town16());
  private Label townAmount = new Label();
  private Town town = new Town();
  protected ActionDetailWidget townStockDetail;

  public StockTownBitmapWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    townImage.setSize("16px", "16px");
    rootPanel.add(townImage);
    rootPanel.add(townAmount);
    updateUI();
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
    return town;
  }
  private void updateUI()
  {
    townAmount.setText(Integer
            .toString(player.stock().towns().size()));
  }
  @Override protected ToolTip createToolTip()
  {
    return new TownStockDetailWidget(gameWidget, player);
  }
}