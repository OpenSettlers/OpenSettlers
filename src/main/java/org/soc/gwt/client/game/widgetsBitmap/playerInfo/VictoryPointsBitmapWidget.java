package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.VictoryPointsChangedEvent;
import org.soc.common.game.VictoryPointsChangedEvent.VictoryPointsChangedHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractVictoryPointsWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class VictoryPointsBitmapWidget extends AbstractVictoryPointsWidget
        implements VictoryPointsChangedHandler
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
    player.victoryPoints().addVictoryPointsChangedListener(this);
  }
  @Override public void onVictoryPointsChanged(VictoryPointsChangedEvent event)
  {
    lblVictoryPointsAmount.setText(Integer.toString(player
            .victoryPoints().total()));
  }
}