package org.soc.gwt.client.game;

import java.util.HashMap;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget;
import org.soc.common.views.widgetsInterface.main.DetailContainerManager;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.playerInfo.PlayerDetailContainerWidget;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.DetailContainerWidget;


public class DetailContainerManagerImpl implements DetailContainerManager
{
    protected HashMap<GamePlayer, PlayerDetailContainerWidget> playersDetails = new HashMap<GamePlayer, PlayerDetailContainerWidget>();
    protected GameWidget gameWidget;

    public DetailContainerManagerImpl(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;

        for (GamePlayer playerr : gameWidget.game().players())
        {
            playersDetails.put(playerr, new DetailContainerWidget(playerr,
                    gameWidget));
        }
    }

    public void showActionWidget(ActionDetailWidget actionDetailWidget)
    {
        PlayerDetailContainerWidget detailContainer = playersDetails
                .get(actionDetailWidget.getGameAction().player());
        detailContainer.showActionWidget(actionDetailWidget);
    }

    public void hideCurrentWidget()
    {
        for (PlayerDetailContainerWidget detailContainer : playersDetails
                .values())
        {
            detailContainer.hideCurrentWidget();
        }
    }
}
