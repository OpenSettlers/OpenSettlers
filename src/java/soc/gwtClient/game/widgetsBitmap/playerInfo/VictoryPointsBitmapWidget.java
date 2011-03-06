package soc.gwtClient.game.widgetsBitmap.playerInfo;

import soc.common.game.VictoryPointsChangedEvent;
import soc.common.game.VictoryPointsChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.playerInfo.AbstractVictoryPointsWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class VictoryPointsBitmapWidget extends AbstractVictoryPointsWidget
                implements VictoryPointsChangedEventHandler
{
    private Image victoryPointsImage = new Image(Resources.icons()
                    .victoryPoints16());
    private Label lblVictoryPointsAmount = new Label();

    public VictoryPointsBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

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

}