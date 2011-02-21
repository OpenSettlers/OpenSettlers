package soc.gwtClient.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import soc.common.board.pieces.Town;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerInfoToolTip;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class TownStockDetailWidget extends AbstractPlayerInfoToolTip implements
        PlayerPieceListChangedEventHandler<Town>
{
    private Map<Town, Image> townImages = new HashMap<Town, Image>();

    public TownStockDetailWidget(GameWidget gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

        for (Town town : player.getStock().getTowns())
        {
            Image townImage = new Image(Resources.icons().townSmall());
            townImages.put(town, townImage);
            rootPanel.add(townImage);
        }

        player.getStock().getTowns().addTownsChangedEventHandler(this);
    }

    @Override
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Town> event)
    {
        if (event.getRemovedPiece() != null)
        {
            Image townImage = townImages.get(event.getRemovedPiece());
            rootPanel.remove(townImage);
        }
        if (event.getAddedPiece() != null)
        {
            Image townImage = new Image(Resources.icons().townSmall());
            townImages.put(event.getAddedPiece(), townImage);
            rootPanel.add(townImage);
        }
    }

}
