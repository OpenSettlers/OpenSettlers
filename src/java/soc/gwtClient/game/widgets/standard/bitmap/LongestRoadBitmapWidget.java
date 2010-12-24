package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractLongestRoadWidget;

public class LongestRoadBitmapWidget extends AbstractLongestRoadWidget
{
    private Image longestRoadImage = new Image("iconz/48/LongestRoad48.png");
    private Label lblLongestRoadLength = new Label();
    
    public LongestRoadBitmapWidget(Player player)
    {
        super(player);
        
        longestRoadImage.setSize("24px", "24px");
        
        //TODO: add player.getLongestRoad()
        lblLongestRoadLength.setText("0");
        
        rootPanel.add(longestRoadImage);
        rootPanel.add(lblLongestRoadLength);
    }
    
    //TODO: implement changed longest road
}
