package soc.gwtClient.game.widgetsBitmap.playerDetail;

import soc.common.board.pieces.Road;
import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractStockItemWidget;
import soc.gwtClient.game.widgetsBitmap.tooltips.RoadStockToolTip;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockRoadBitmapWidget extends AbstractStockItemWidget implements
        PlayerPieceListChangedEventHandler<Road>

{
    private Road road = new Road();
    private Image roadImage = new Image(Resources.icons().roadSmall());
    private Label roadAmount = new Label();

    public StockRoadBitmapWidget(GameWidget gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

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
        return new RoadStockToolTip(gamePanel, player);
    }
}
