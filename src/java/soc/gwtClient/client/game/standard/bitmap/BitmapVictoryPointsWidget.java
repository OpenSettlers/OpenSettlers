package soc.gwtClient.client.game.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractVictoryPointsWidget;

public class BitmapVictoryPointsWidget extends AbstractVictoryPointsWidget
{
    Image victoryPointsImage = new Image("icons/48/Cup48.png");
    Label lblVictoryPointsAmount = new Label();
    
    public BitmapVictoryPointsWidget(Player player)
    {
        super(player);
        
        victoryPointsImage.setSize("24px", "24px");
        //TODO: add player.getVictoryPoints();
        //lblVictoryPointsAmount.setText(player.getVictoryPoints());
        
        rootPanel.add(victoryPointsImage);
        rootPanel.add(lblVictoryPointsAmount);
    }

}
