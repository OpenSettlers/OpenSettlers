package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractStockItemWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockTownBitmapWidget extends AbstractStockItemWidget implements
        PlayerPieceListChangedEventHandler<Road>
{
    private Image townImage = new Image(Resources.icons().townSmall());
    private Label townAmount = new Label();
    private Town town = new Town();

    public StockTownBitmapWidget(GamePlayer player)
    {
        super(player);

        townImage.setSize("16px", "16px");

        rootPanel.add(townImage);
        rootPanel.add(townAmount);
        updateUI();

        player.getStock().getRoads().addRoadsChangedEventHandler(this);
    }

    @Override
    public PlayerPiece getStockPiece()
    {
        return town;
    }

    private void updateUI()
    {
        townAmount.setText(Integer
                .toString(player.getStock().getTowns().size()));
    }

    @Override
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Road> event)
    {
        updateUI();
    }
}