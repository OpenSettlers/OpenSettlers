package soc.gwtClient.game.abstractWidgets;

import soc.common.board.resources.ResourceList;

import com.google.gwt.user.client.ui.IsWidget;

public interface PlayerDetailContainerWidget extends IsWidget
{
    public void showResourcesGained(ResourceList resources);

    public void hide();

    public void showMouseOverWidget(PlayerDetailWidget playerDetailWidget);

    public void hideMouseOverWidget();

    public void hideCurrentWidget();
}
