package soc.gwtClient.game.widgets.standard.bitmap.player;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.Town;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractStockItemWidget;

public class StockTownBitmapWidget extends AbstractStockItemWidget implements PiecesChangedEventHandler
{
    private Image townImage = new Image("iconz/48/Town48.png");
    private Label townAmount = new Label();
    private Town town = new Town();
    
    public StockTownBitmapWidget(Player player)
    {
        super(player);
        
        townImage.setSize("24px", "24px");
        townAmount.setText(Integer.toString(player.getStock().ofType(Town.TOWN).size()));
        
        rootPanel.add(townImage);
        rootPanel.add(townAmount);
        
        player.getStock().addPiecesChangedEventHandler(this);
    }

    @Override
    public PlayerPiece getStockPiece()
    {
        return town;
    }

    @Override
    public void onPiecesChanged(PiecesChangedEvent event)
    {
        if (event.getChangedPiece() instanceof Town)
        {
            townAmount.setText(Integer.toString(player.getStock().ofType(Town.TOWN).size()));
        }
    }

}
