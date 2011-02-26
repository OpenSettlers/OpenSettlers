package soc.gwtClient.game;

import java.util.HashMap;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.DetailContainerManager;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;
import soc.common.views.widgetsInterface.payerInfo.PlayerDetailContainerWidget;
import soc.gwtClient.game.widgetsBitmap.tooltips.DetailContainerWidget;

public class DetailContainerManagerImpl implements DetailContainerManager
{
    protected HashMap<GamePlayer, PlayerDetailContainerWidget> playersDetails = new HashMap<GamePlayer, PlayerDetailContainerWidget>();
    protected GameWidget gameWidget;

    public DetailContainerManagerImpl(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;

        for (GamePlayer playerr : gameWidget.getGame().getPlayers())
        {
            playersDetails.put(playerr, new DetailContainerWidget(playerr,
                    gameWidget));
        }
    }

    public void showActionWidget(ActionDetailWidget actionDetailWidget)
    {
        PlayerDetailContainerWidget detailContainer = playersDetails
                .get(actionDetailWidget.getGameAction().getPlayer());
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
