package soc.gwtClient.client.game.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractLongestRoadWidget;

public class BitmapLongestRoadWidget extends AbstractLongestRoadWidget
{
    private Image longestRoadImage = new Image("icons/48/LongestRoad48.png");
    private Label lblLongestRoadLength = new Label();
    
    public BitmapLongestRoadWidget(Player player)
    {
        super(player);
        
        longestRoadImage.setSize("24px", "24px");
        
        //TODO: add player.getLongestRoad()
        lblLongestRoadLength.setText("0");
        
        rootPanel.add(longestRoadImage);
        rootPanel.add(lblLongestRoadLength);
    }

}
