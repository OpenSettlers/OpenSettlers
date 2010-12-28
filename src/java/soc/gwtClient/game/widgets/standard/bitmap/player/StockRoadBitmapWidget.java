package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.Road;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractStockItemWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockRoadBitmapWidget extends AbstractStockItemWidget implements
        PiecesChangedEventHandler
{
    private Road road = new Road();
    private Image roadImage = new Image(Resources.icons().roadSmall());
    private Label roadAmount = new Label();

    public StockRoadBitmapWidget(Player player)
    {
        super(player);

        roadImage.setSize("16px", "16px");
        roadAmount.setText(Integer.toString(player.getStock().ofType(Road.ROAD)
                .size()));

        rootPanel.add(roadImage);
        rootPanel.add(roadAmount);

        player.getStock().addPiecesChangedEventHandler(this);
    }

    @Override
    public PlayerPiece getStockPiece()
    {
        return road;
    }

    @Override
    public void onPiecesChanged(PiecesChangedEvent event)
    {
        if (event.getChangedPiece() instanceof Road)
        {
            roadAmount.setText(Integer.toString(player.getStock().ofType(
                    Road.ROAD).size()));
        }
    }
}
