package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.Road;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractStockItemWidget;
import soc.gwtClient.game.widgets.bitmap.ImageLibrary;

public class BitmapStockRoadWidget extends AbstractStockItemWidget implements PiecesChangedEventHandler
{
    private Road road = new Road();
    private Image roadImage = new Image(ImageLibrary.getIcon(road, 48));
    private Label roadAmount = new Label();
    
    public BitmapStockRoadWidget(Player player)
    {
        super(player);
        
        roadImage.setSize("24px", "24px");
        roadAmount.setText(Integer.toString(player.getStock().ofType(new Road()).size()));
        
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
            roadAmount.setText(Integer.toString(player.getStock().ofType(road).size()));
        }
    }
}
