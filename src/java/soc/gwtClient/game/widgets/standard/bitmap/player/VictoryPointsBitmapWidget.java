package soc.gwtClient.game.widgets.standard.bitmap.player;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.game.Player;
import soc.common.game.VictoryPointsChangedEvent;
import soc.gwtClient.game.abstractWidgets.AbstractVictoryPointsWidget;

public class VictoryPointsBitmapWidget extends AbstractVictoryPointsWidget
{
    Image victoryPointsImage = new Image("iconz/48/Cup48.png");
    Label lblVictoryPointsAmount = new Label();
    
    public VictoryPointsBitmapWidget(Player player)
    {
        super(player);
        
        victoryPointsImage.setSize("24px", "24px");
        lblVictoryPointsAmount.setText(
                Integer.toString(player.getVictoryPoints().getTotalPoints()));
        
        rootPanel.add(victoryPointsImage);
        rootPanel.add(lblVictoryPointsAmount);
    }

    @Override
    public void onVictoryPointsChanged(VictoryPointsChangedEvent event)
    {
        lblVictoryPointsAmount.setText(
                Integer.toString(player.getVictoryPoints().getTotalPoints()));        
    }

}
