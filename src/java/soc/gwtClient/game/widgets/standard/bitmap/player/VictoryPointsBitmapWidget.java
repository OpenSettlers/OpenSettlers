package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.game.VictoryPointsChangedEvent;
import soc.common.game.VictoryPointsChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractVictoryPointsWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.PlayerDetailWidget;
import soc.gwtClient.game.widgets.standard.bitmap.playerDetail.VictoryPointsDetailWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class VictoryPointsBitmapWidget extends AbstractVictoryPointsWidget
        implements VictoryPointsChangedEventHandler
{
    private Image victoryPointsImage = new Image(Resources.icons()
            .victoryPointsSmall());
    private Label lblVictoryPointsAmount = new Label();

    public VictoryPointsBitmapWidget(GamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

        victoryPointsImage.setSize("16px", "16px");
        lblVictoryPointsAmount.setText(Integer.toString(player
                .getVictoryPoints().getTotalPoints()));

        rootPanel.add(victoryPointsImage);
        rootPanel.add(lblVictoryPointsAmount);

        player.getVictoryPoints().addVictoryPointsChangedListener(this);
    }

    @Override
    public void onVictoryPointsChanged(VictoryPointsChangedEvent event)
    {
        lblVictoryPointsAmount.setText(Integer.toString(player
                .getVictoryPoints().getTotalPoints()));
    }

    @Override
    public PlayerDetailWidget createPlayerDetailWidget()
    {
        return new VictoryPointsDetailWidget(gamePanel, player);
    }
}
