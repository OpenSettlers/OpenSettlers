package soc.gwtClient.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import soc.common.board.pieces.City;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class CityStockToolTip extends AbstractPlayerInfoToolTip implements
        PlayerPieceListChangedEventHandler<City>
{
    private Map<City, Image> cityImages = new HashMap<City, Image>();

    public CityStockToolTip(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        for (City city : player.getStock().getCities())
        {
            Image cityImage = new Image(Resources.icons().citySmall());
            cityImages.put(city, cityImage);
            rootPanel.add(cityImage);
        }

        player.getStock().getCities().addCitiesChangedEventHandler(this);
    }

    @Override
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<City> event)
    {
        if (event.getRemovedPiece() != null)
        {
            Image cityImage = cityImages.get(event.getRemovedPiece());
            rootPanel.remove(cityImage);
        }
        if (event.getAddedPiece() != null)
        {
            Image cityImage = new Image(Resources.icons().citySmall());
            cityImages.put(event.getAddedPiece(), cityImage);
            rootPanel.add(cityImage);
        }
    }

}
