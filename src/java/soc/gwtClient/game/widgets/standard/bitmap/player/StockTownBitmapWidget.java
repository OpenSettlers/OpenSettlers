package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.Town;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractStockItemWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockTownBitmapWidget extends AbstractStockItemWidget implements
        PiecesChangedEventHandler
{
    private Image townImage = new Image(Resources.icons().townSmall());
    private Label townAmount = new Label();
    private Town town = new Town();

    public StockTownBitmapWidget(Player player)
    {
        super(player);

        townImage.setSize("16px", "16px");
        townAmount.setText(Integer.toString(player.getStock().ofType(Town.TOWN)
                .size()));

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
            townAmount.setText(Integer.toString(player.getStock().ofType(
                    Town.TOWN).size()));
        }
    }

}
