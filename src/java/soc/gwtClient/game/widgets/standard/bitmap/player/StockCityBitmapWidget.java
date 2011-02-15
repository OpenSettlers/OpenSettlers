package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.board.pieces.City;
import soc.common.board.pieces.abstractPieces.PlayerPiece;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEvent;
import soc.common.board.pieces.pieceLists.PlayerPieceListChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractStockItemWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.PlayerDetailWidget;
import soc.gwtClient.game.widgets.standard.bitmap.playerDetail.CityStockDetailWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockCityBitmapWidget extends AbstractStockItemWidget implements
        PlayerPieceListChangedEventHandler<City>
{
    Image cityImage = new Image(Resources.icons().citySmall());
    Label amountCities = new Label();
    City city = new City();

    public StockCityBitmapWidget(GamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

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
    public PlayerDetailWidget createDetailWidget()
    {
        return new CityStockDetailWidget(gamePanel, player);
    }

}
