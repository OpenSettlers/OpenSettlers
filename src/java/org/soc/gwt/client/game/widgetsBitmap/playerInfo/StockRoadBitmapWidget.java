package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.board.pieces.Road;
import org.soc.common.board.pieces.abstractPieces.PlayerPiece;
import org.soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import org.soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractStockItemWidget;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.RoadStockToolTip;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockRoadBitmapWidget extends AbstractStockItemWidget implements
                PlayerPieceListChangedEventHandler<Road>

{
    private Road road = new Road();
    private Image roadImage = new Image(Resources.icons().road16());
    private Label roadAmount = new Label();

    public StockRoadBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        roadImage.setSize("16px", "16px");
        updateUI();

        rootPanel.add(roadImage);
        rootPanel.add(roadAmount);

        player.getStock().getRoads().addRoadsChangedEventHandler(this);
    }

    @Override
    public PlayerPiece getStockPiece()
    {
        return road;
    }

    private void updateUI()
    {
        roadAmount.setText(Integer
                        .toString(player.getStock().getRoads().size()));
    }

    @Override
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Road> event)
    {
        updateUI();
    }

    @Override
    protected ToolTip createToolTip()
    {
        return new RoadStockToolTip(gameWidget, player);
    }
}
