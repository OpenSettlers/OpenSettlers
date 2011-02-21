package soc.gwtClient.game;

import java.util.HashMap;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsBitmap.tooltips.DetailContainerWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.ActionDetailWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayerDetailContainerWidget;

public class DetailContainerManager
{
    protected HashMap<GamePlayer, PlayerDetailContainerWidget> playersDetails = new HashMap<GamePlayer, PlayerDetailContainerWidget>();
    protected GameWidget gameWidget;

    public DetailContainerManager(GameWidget gameWidget)
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
