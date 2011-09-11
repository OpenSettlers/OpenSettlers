package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.VictoryPointsChangedEvent;
import org.soc.common.game.VictoryPointsChangedEvent.VictoryPointsChangedHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actions.AbstractActionWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class ClaimVictoryBitmapWidget extends AbstractActionWidget implements
        VictoryPointsChangedHandler
{
  PushButton btnClaimVictory = new PushButton(new Image(R.icons()
          .claimVictory48()));

  public ClaimVictoryBitmapWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    player.victoryPoints().addVictoryPointsChangedListener(this);
  }
  @Override public Widget asWidget()
  {
    return btnClaimVictory;
  }
  @Override protected void updateEnabled()
  {
    checkEnabled();
  }
  private void enableUI()
  {
    btnClaimVictory.setEnabled(true);
  }
  private void disableUI()
  {
    btnClaimVictory.setEnabled(false);
  }
  private void checkEnabled()
  {
    if (enabled
            && player.isOnTurn()
            && gameWidget.game().gameSettings()
                    .getBoardSettings().getVpToWin()
                    .vpToWin() <= player
                    .victoryPoints().total())
    {
      enableUI();
      return;
    }
    disableUI();
  }
  @Override public void onVictoryPointsChanged(VictoryPointsChangedEvent event)
  {
    checkEnabled();
  }
}