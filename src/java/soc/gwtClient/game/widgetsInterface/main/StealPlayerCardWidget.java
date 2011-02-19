package soc.gwtClient.game.widgetsInterface.main;

import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.IsWidget;

public interface StealPlayerCardWidget extends IsWidget
{
    public void update(GamePlayer player);

    public void setVisible(boolean visible);
}
