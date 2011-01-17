package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.board.pieces.PlayerPiece;
import soc.common.board.pieces.PlistChangedEvent;
import soc.common.board.pieces.PlistChangedEventHandler;
import soc.common.board.pieces.Road;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractStockItemWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StockRoadBitmapWidget extends AbstractStockItemWidget implements
        PlistChangedEventHandler<Road>

{
    private Road road = new Road();
    private Image roadImage = new Image(Resources.icons().roadSmall());
    private Label roadAmount = new Label();

    public StockRoadBitmapWidget(GamePlayer player)
    {
        super(player);

        roadImage.setSize("16px", "16px");
        updateUI();

        rootPanel.add(roadImage);
        rootPanel.add(roadAmount);

        player.getStock().getRoads().addRoadsChangedEventHandler(this);
    }

    @Override
    public PlayerPiece getStockPiece()
    {
        return road;
    }

    private void updateUI()
    {
        roadAmount.setText(Integer
                .toString(player.getStock().getRoads().size()));
    }

    @Override
    public void onPlistChanged(PlistChangedEvent<Road> event)
    {
        updateUI();
    }

}
