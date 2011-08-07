package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.board.resources.ResourcesChangedEvent;
import org.soc.common.game.player.GamePlayer;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractResourceAmountWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class ResourceAmountBitmapWidget extends AbstractResourceAmountWidget
{
    Image cardImage = new Image(Resources.icons().blankCard16());
    Label lblAmountResources = new Label();

    public ResourceAmountBitmapWidget(GamePlayer player)
    {
        super(player);

        cardImage.setSize("16px", "16px");
        lblAmountResources.setText(Integer.toString(player.getResources()
                        .size()));

        rootPanel.add(cardImage);
        rootPanel.add(lblAmountResources);
    }

    /*
     * (non-Javadoc)
     * 
     * @seesoc.gwtClient.game.abstractWidgets.AbstractResourceAmountWidget#
     * onResourcesChanged(org.soc.common.board.resources.ResourcesChangedEvent)
     */
    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        lblAmountResources.setText(Integer.toString(player.getResources()
                        .size()));
    }

}
