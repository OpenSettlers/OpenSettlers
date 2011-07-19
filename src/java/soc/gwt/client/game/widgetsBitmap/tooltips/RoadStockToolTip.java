package soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import soc.common.board.pieces.Road;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwt.client.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;
import soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class RoadStockToolTip extends AbstractPlayerInfoToolTip implements
                PlayerPieceListChangedEventHandler<Road>
{
    private Map<Road, Image> roadImages = new HashMap<Road, Image>();

    public RoadStockToolTip(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        for (Road road : player.getStock().getRoads())
        {
            Image roadImage = new Image(Resources.icons().road16());
            roadImages.put(road, roadImage);
            rootPanel.add(roadImage);
        }

        player.getStock().getRoads().addRoadsChangedEventHandler(this);
    }

    @Override
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Road> event)
    {
        if (event.getRemovedPiece() != null)
        {
            Image roadImage = roadImages.get(event.getRemovedPiece());
            rootPanel.remove(roadImage);
        }
        if (event.getAddedPiece() != null)
        {
            Image roadImage = new Image(Resources.icons().road16());
            roadImages.put(event.getAddedPiece(), roadImage);
            rootPanel.add(roadImage);
        }
    }
}