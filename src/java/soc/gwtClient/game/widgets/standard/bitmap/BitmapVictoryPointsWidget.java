package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.game.Player;
import soc.common.game.VictoryPointsChangedEvent;
import soc.gwtClient.game.abstractWidgets.AbstractVictoryPointsWidget;

public class BitmapVictoryPointsWidget extends AbstractVictoryPointsWidget
{
    Image victoryPointsImage = new Image("icons/48/Cup48.png");
    Label lblVictoryPointsAmount = new Label();
    
    public BitmapVictoryPointsWidget(Player player)
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
