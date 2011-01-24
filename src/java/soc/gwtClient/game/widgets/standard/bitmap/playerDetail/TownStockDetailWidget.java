package soc.gwtClient.game.widgets.standard.bitmap.playerDetail;

import soc.common.board.pieces.pieceLists.PiecesChangedEvent;
import soc.common.board.pieces.pieceLists.PiecesChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerDetailWidget;

public class TownStockDetailWidget extends AbstractPlayerDetailWidget implements
        PiecesChangedEventHandler
{

    public TownStockDetailWidget(GamePlayer player)
    {
        super(player);

        update();
    }

    private void update()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPiecesChanged(PiecesChangedEvent list)
    {

    }

}
