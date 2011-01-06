package soc.gwtClient.game.widgets.bitmap;

import java.util.HashMap;

import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.board.ports.PortList;
import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceListWidget;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResourcesGainedBitmapWidget implements IsWidget
{
    PopupPanel popup = new PopupPanel();
    private VerticalPanel rootPanel = new VerticalPanel();
    private HashMap<GamePlayer, ResourceListWidget> playerResources = new HashMap<GamePlayer, ResourceListWidget>();
    private GamePanel gamePanel;

    public ResourcesGainedBitmapWidget(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        for (GamePlayer player : gamePanel.getGame().getPlayers())
        {
            ResourceListWidget resourceList = createResourceListWidget(player
                    .getResources(), null, null);
            playerResources.put(player, resourceList);
            rootPanel.add(resourceList);
        }
        popup.add(rootPanel);
    }

    private ResourceListWidget createResourceListWidget(
            ResourceList resources, ResourceList bankResources, PortList ports)
    {
        return new ResourceListBitmapWidget(resources, bankResources, ports);
    }

    public void update(RollDice rollDice)
    {
        for (GamePlayer player : rollDice.getPlayersResources().keySet())
        {
            playerResources.get(player).setResources(
                    rollDice.getPlayersResources().get(player));
        }
    }

    @Override
    public Widget asWidget()
    {
        return popup;
    }

}
