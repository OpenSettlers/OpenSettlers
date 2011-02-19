package soc.gwtClient.game.widgetsInterface.dialogs;

import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.IsWidget;

public interface LooseCardsWidget extends IsWidget
{
    public void setVisible(boolean visible);

    public void update();

    public GamePlayer getPlayer();

    public ResourceList getLostCards();
}
