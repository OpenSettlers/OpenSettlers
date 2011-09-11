package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.Road;
import org.soc.common.game.pieces.Piece.PlayerPiece;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEvent;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEventHandler;
import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractStockItemWidget;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.RoadStockToolTip;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockRoadBitmapWidget extends AbstractStockItemWidget implements
        PlayerPieceListChangedEventHandler<Road>
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
    player.stock().roads().addRoadsChangedEventHandler(this);
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
  @Override public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Road> event)
  {
    updateUI();
  }
  @Override protected ToolTip createToolTip()
  {
    return new RoadStockToolTip(gameWidget, player);
  }
}
