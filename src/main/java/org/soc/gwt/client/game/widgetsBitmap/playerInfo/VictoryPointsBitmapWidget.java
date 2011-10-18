package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.core.GenericList.Adds.*;
import org.soc.common.core.GenericList.Removes.*;
import org.soc.common.game.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;

public class VictoryPointsBitmapWidget extends AbstractVictoryPointsWidget
{
  private Image victoryPointsImage = new Image(R.icons()
          .victoryPoints16());
  private Label lblVictoryPointsAmount = new Label();

  public VictoryPointsBitmapWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    victoryPointsImage.setSize("16px", "16px");
    lblVictoryPointsAmount.setText(Integer.toString(player
            .victoryPoints().total()));
    rootPanel.add(victoryPointsImage);
    rootPanel.add(lblVictoryPointsAmount);
    player.victoryPoints().addAddedHandler(new Added<VictoryPointItem>() {
      @Override public void added(VictoryPointItem item) {
        update();
      }
    });
    player.victoryPoints().addRemovedHandler(new Removed<VictoryPointItem>() {
      @Override public void removed(VictoryPointItem item) {
        update();
      }
    });
  }
  private void update() {
    lblVictoryPointsAmount.setText(Integer.toString(player
            .victoryPoints().total()));
  }
}