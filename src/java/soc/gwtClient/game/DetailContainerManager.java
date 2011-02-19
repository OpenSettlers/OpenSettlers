package soc.gwtClient.game;

import java.util.HashMap;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsBitmap.tooltips.DetailContainerWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayerDetailContainerWidget;
import soc.gwtClient.game.widgetsInterface.playerDetail.PlayerDetailWidget;

public class DetailContainerManager
{
    protected HashMap<GamePlayer, PlayerDetailContainerWidget> playersDetails = new HashMap<GamePlayer, PlayerDetailContainerWidget>();
    protected GameWidget gamePanel;

    public DetailContainerManager(GameWidget gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

        for (GamePlayer playerr : gamePanel.getGame().getPlayers())
        {
            playersDetails.put(playerr, new DetailContainerWidget(playerr,
                    gamePanel));
        }
    }

    public void showActionWidget(GameAction action)
    {
        playersDetails.get(action.getPlayer()).showActionWidget(action);
    }

    public void hideCurrentWidget()
    {
        for (PlayerDetailContainerWidget detailContainer : playersDetails
                .values())
        {
            detailContainer.hideCurrentWidget();
        }
    }

    public void showMouseOverDetail(GamePlayer player, PlayerDetailWidget widget)
    {
        playersDetails.get(player).showMouseOverWidget(widget);
    }

    public void hideMouseOverDetail(GamePlayer player)
    {
        playersDetails.get(player).hideMouseOverWidget();
    }
}
