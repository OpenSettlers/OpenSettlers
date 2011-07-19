package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.board.pieces.Town;
import org.soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import org.soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;
import org.soc.gwt.client.images.Resources;


import com.google.gwt.user.client.ui.Image;

public class TownStockDetailWidget extends AbstractPlayerInfoToolTip implements
                PlayerPieceListChangedEventHandler<Town>
{
    private Map<Town, Image> townImages = new HashMap<Town, Image>();

    public TownStockDetailWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        for (Town town : player.getStock().getTowns())
        {
            Image townImage = new Image(Resources.icons().town16());
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
            Image townImage = new Image(Resources.icons().town16());
            townImages.put(event.getAddedPiece(), townImage);
            rootPanel.add(townImage);
        }
    }

}
