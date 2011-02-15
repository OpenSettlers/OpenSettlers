package soc.gwtClient.game.widgets;

import java.util.HashMap;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.PlayerDetailContainerWidget;
import soc.gwtClient.game.abstractWidgets.PlayerDetailWidget;
import soc.gwtClient.game.widgets.standard.bitmap.playerDetail.DetailContainerWidget;

public class DetailContainerManager
{
    protected HashMap<GamePlayer, PlayerDetailContainerWidget> playersDetails = new HashMap<GamePlayer, PlayerDetailContainerWidget>();
    protected GamePanel gamePanel;

    public DetailContainerManager(GamePanel gamePanel)
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
