package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.game.VictoryPointsChangedEvent;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractVictoryPointsWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class VictoryPointsBitmapWidget extends AbstractVictoryPointsWidget
{
    Image victoryPointsImage = new Image(Resources.icons().victoryPointsSmall());
    Label lblVictoryPointsAmount = new Label();

    public VictoryPointsBitmapWidget(GamePlayer player)
    {
        super(player);

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
