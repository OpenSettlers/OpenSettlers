package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.Road;
import org.soc.common.game.pieces.Town;
import org.soc.common.game.pieces.Piece.PlayerPiece;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEvent;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEventHandler;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget;
import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractStockItemWidget;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.TownStockDetailWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockTownBitmapWidget extends AbstractStockItemWidget implements
        PlayerPieceListChangedEventHandler<Road>
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
    player.stock().roads().addRoadsChangedEventHandler(this);
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
  @Override public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Road> event)
  {
    updateUI();
  }
  @Override protected ToolTip createToolTip()
  {
    return new TownStockDetailWidget(gameWidget, player);
  }
}