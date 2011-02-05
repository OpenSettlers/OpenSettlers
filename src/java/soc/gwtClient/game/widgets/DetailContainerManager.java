package soc.gwtClient.game.widgets;

import java.util.HashMap;
import java.util.Map.Entry;

import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.board.resources.ResourceList;
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

    public void showResourcesGained(RollDice rollDice)
    {
        for (Entry<GamePlayer, ResourceList> entry : rollDice
                .getPlayersResources().entrySet())
        {
            playersDetails.get(entry.getKey()).showResourcesGained(
                    entry.getValue());
        }
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
