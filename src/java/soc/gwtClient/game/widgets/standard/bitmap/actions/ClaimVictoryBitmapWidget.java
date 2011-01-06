package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.game.VictoryPointsChangedEvent;
import soc.common.game.VictoryPointsChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class ClaimVictoryBitmapWidget extends AbstractActionWidget implements
        VictoryPointsChangedEventHandler
{
    PushButton btnClaimVictory = new PushButton(new Image(Resources.icons()
            .claimVictory()));

    public ClaimVictoryBitmapWidget(GamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

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
        btnClaimVictory.setEnabled(enabled);
    }

    @Override
    public void onVictoryPointsChanged(VictoryPointsChangedEvent event)
    {
        if (gamePanel.getGame().getGameSettings().getBoardSettings()
                .getVpToWin() >= player.getVictoryPoints().getTotalPoints())
        {
            setEnabled(true);
            return;
        }

        setEnabled(false);
    }
}