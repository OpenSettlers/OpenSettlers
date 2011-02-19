package soc.gwtClient.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import soc.common.board.pieces.City;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class CityStockDetailWidget extends AbstractPlayerDetailWidget implements
        PlayerPieceListChangedEventHandler<City>
{
    private Map<City, Image> cityImages = new HashMap<City, Image>();

    public CityStockDetailWidget(GameWidget gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

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
