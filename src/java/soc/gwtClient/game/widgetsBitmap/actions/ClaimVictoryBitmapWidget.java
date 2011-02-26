package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.game.VictoryPointsChangedEvent;
import soc.common.game.VictoryPointsChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.actions.AbstractActionWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class ClaimVictoryBitmapWidget extends AbstractActionWidget implements
        VictoryPointsChangedEventHandler
{
    PushButton btnClaimVictory = new PushButton(new Image(Resources.icons()
            .claimVictory()));

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
                && gameWidget.getGame().getGameSettings().getBoardSettings()
                        .getVpToWin() <= player.getVictoryPoints()
                        .getTotalPoints())
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