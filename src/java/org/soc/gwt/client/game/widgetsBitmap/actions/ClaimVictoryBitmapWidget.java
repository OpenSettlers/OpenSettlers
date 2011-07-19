package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.game.VictoryPointsChangedEvent;
import org.soc.common.game.VictoryPointsChangedEventHandler;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actions.AbstractActionWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class ClaimVictoryBitmapWidget extends AbstractActionWidget implements
                VictoryPointsChangedEventHandler
{
    PushButton btnClaimVictory = new PushButton(new Image(Resources.icons()
                    .claimVictory48()));

    public ClaimVictoryBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        player.getVictoryPoints().addVictoryPointsChangedListener(this);
    }

    @Override
    public Widget asWidget()
    {
        return btnClaimVictory;
    }

    @Override
    protected void updateEnabled()
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
                        && gameWidget.getGame().getGameSettings()
                                        .getBoardSettings().getVpToWin()
                                        .getVpToWin() <= player
                                        .getVictoryPoints().getTotalPoints())
        {
            enableUI();
            return;
        }

        disableUI();
    }

    @Override
    public void onVictoryPointsChanged(VictoryPointsChangedEvent event)
    {
        checkEnabled();
    }
}