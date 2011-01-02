package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.game.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractLongestRoadWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class LongestRoadBitmapWidget extends AbstractLongestRoadWidget
{
    private Image longestRoadImage = new Image(Resources.icons()
            .longestRoadSmall());
    private Label lblLongestRoadLength = new Label();

    public LongestRoadBitmapWidget(GamePlayer player)
    {
        super(player);

        longestRoadImage.setSize("16px", "16px");

        // TODO: add player.getLongestRoad()
        lblLongestRoadLength.setText("0");

        rootPanel.add(longestRoadImage);
        rootPanel.add(lblLongestRoadLength);
    }

    // TODO: implement changed longest road
}
