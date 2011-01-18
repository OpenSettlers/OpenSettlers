package soc.gwtClient.game.widgets.abstractWidgets;

import soc.common.board.resources.ResourceList;

import com.google.gwt.user.client.ui.IsWidget;

public interface TradeListWidget extends IsWidget
{
    public ResourceList getWantResources();

    public ResourceList getGiveResources();
}
