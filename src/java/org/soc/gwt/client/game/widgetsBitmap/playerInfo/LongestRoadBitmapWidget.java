package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.player.GamePlayer;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractLongestRoadWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class LongestRoadBitmapWidget extends AbstractLongestRoadWidget
{
    private Image longestRoadImage = new Image(Resources.icons()
                    .longestRoad16());
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
