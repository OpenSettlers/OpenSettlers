package soc.gwtClient.game.widgets.bitmap;

import java.util.HashMap;

import soc.common.board.ports.PortList;
import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.behaviour.RollDiceResult;
import soc.gwtClient.game.widgets.abstractWidgets.ResourceListWidget;
import soc.gwtClient.game.widgets.abstractWidgets.ResourcesGainedWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResourcesGainedBitmapWidget implements ResourcesGainedWidget,
        ClickHandler
{
    PopupPanel popup = new PopupPanel();
    private VerticalPanel rootPanel = new VerticalPanel();
    private HashMap<GamePlayer, ResourceListWidget> playerResources = new HashMap<GamePlayer, ResourceListWidget>();
    private GamePanel gamePanel;
    private Button btnClose = new Button("Close");
    private RollDiceResult rollDiceResult;

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
        rootPanel.add(btnClose);
        btnClose.addClickHandler(this);
        popup.add(rootPanel);
    }

    private ResourceListWidget createResourceListWidget(ResourceList resources,
            ResourceList bankResources, PortList ports)
    {
        return new ResourceListBitmapWidget(resources, bankResources, ports);
    }

    public void update(RollDiceResult rollDiceResult)
    {
        this.rollDiceResult = rollDiceResult;
        for (GamePlayer player : rollDiceResult.getRolledDice()
                .getPlayersResources().keySet())
        {
            playerResources.get(player).setResources(
                    rollDiceResult.getRolledDice().getPlayersResources().get(
                            player));
        }
        popup.show();
    }

    @Override
    public Widget asWidget()
    {
        return popup;
    }

    @Override
    public void onClick(ClickEvent event)
    {
        popup.hide();
        rollDiceResult.doneResources();
    }
}
