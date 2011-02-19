package soc.gwtClient.game.widgetsBitmap.playerDetail;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractLongestRoadWidget;
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

        // TODO: visualize if player has longest road
        lblLongestRoadLength.setText("0");

        rootPanel.add(longestRoadImage);
        rootPanel.add(lblLongestRoadLength);
    }

    // TODO: implement changed longest road
}
