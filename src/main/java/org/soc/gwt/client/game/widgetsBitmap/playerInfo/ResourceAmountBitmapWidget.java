package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractResourceAmountWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class ResourceAmountBitmapWidget extends AbstractResourceAmountWidget
{
  Image cardImage = new Image(R.icons().blankCard16());
  Label lblAmountResources = new Label();

  public ResourceAmountBitmapWidget(GamePlayer player)
  {
    super(player);
    cardImage.setSize("16px", "16px");
    lblAmountResources.setText(Integer.toString(player.resources()
            .size()));
    rootPanel.add(cardImage);
    rootPanel.add(lblAmountResources);
  }
  /* (non-Javadoc)
   * 
   * @seesoc.gwtClient.game.abstractWidgets.AbstractResourceAmountWidget#
   * onResourcesChanged(org.soc.common.board.resources.ResourcesChangedEvent) */
  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
  {
    lblAmountResources.setText(Integer.toString(player.resources()
            .size()));
  }
}
