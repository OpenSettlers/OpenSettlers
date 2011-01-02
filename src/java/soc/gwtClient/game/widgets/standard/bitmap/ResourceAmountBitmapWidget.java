package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.game.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractResourceAmountWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class ResourceAmountBitmapWidget extends AbstractResourceAmountWidget
{
    Image cardImage = new Image(Resources.icons().blankCardSmall());
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
     * onResourcesChanged(soc.common.board.resources.ResourcesChangedEvent)
     */
    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        lblAmountResources.setText(Integer.toString(player.getResources()
                .size()));
    }

}
