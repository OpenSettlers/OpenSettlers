package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.core.GenericList.Adds.*;
import org.soc.common.game.*;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;

public class ResourceAmountBitmapWidget extends AbstractResourceAmountWidget
{
  Image cardImage = new Image(R.icons().blankCard16());
  Label lblAmountResources = new Label();

  public ResourceAmountBitmapWidget(final GamePlayer player)
  {
    super(player);
    cardImage.setSize("16px", "16px");
    lblAmountResources.setText(Integer.toString(player.resources()
            .size()));
    rootPanel.add(cardImage);
    rootPanel.add(lblAmountResources);
    player.resources().addAddedHandler(new Added<Resource>() {
      @Override public void added(Resource item) {
        lblAmountResources.setText(Integer.toString(player.resources().size()));
      }
    });
  }
}
