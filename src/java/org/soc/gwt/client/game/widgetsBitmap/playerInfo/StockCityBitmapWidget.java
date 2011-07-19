package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.board.pieces.City;
import org.soc.common.board.pieces.abstractPieces.PlayerPiece;
import org.soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import org.soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractStockItemWidget;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.CityStockToolTip;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockCityBitmapWidget extends AbstractStockItemWidget implements
                PlayerPieceListChangedEventHandler<City>
{
    Image cityImage = new Image(Resources.icons().city16());
    Label amountCities = new Label();
    City city = new City();

    public StockCityBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        cityImage.setSize("16px", "16px");
        updateUI();

        rootPanel.add(cityImage);
        rootPanel.add(amountCities);

        player.getStock().getCities().addCitiesChangedEventHandler(this);
    }

    @Override
    public PlayerPiece getStockPiece()
    {
        return city;
    }

    private void updateUI()
    {
        amountCities.setText(Integer.toString(player.getStock().getCities()
                        .size()));
    }

    @Override
    public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<City> event)
    {
        updateUI();
    }

    @Override
    protected ToolTip createToolTip()
    {
        return new CityStockToolTip(gameWidget, player);
    }
}
