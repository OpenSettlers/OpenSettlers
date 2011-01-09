package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.IsWidget;

public interface StealPlayerCardWidget extends IsWidget
{
    public void update(GamePlayer player);
}
