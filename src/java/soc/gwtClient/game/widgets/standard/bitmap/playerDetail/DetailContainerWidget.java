package soc.gwtClient.game.widgets.standard.bitmap.playerDetail;

import java.util.HashMap;

import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.PlayerDetailContainerWidget;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;

public class DetailContainerWidget extends PopupPanel implements
        PlayerDetailContainerWidget
{
    private GamePlayer player;
    private ResourcesGainedDetailWidget resourcesGained;
    private DeckPanel deckPanel = new DeckPanel();
    private HashMap<IsWidget, Integer> widgetIndices = new HashMap<IsWidget, Integer>();
    private GamePanel gamePanel;

    public DetailContainerWidget(GamePlayer player, GamePanel gamePanel)
    {
        super();
        this.player = player;
        this.gamePanel = gamePanel;

        resourcesGained = new ResourcesGainedDetailWidget(player);
        deckPanel.add(resourcesGained);
        widgetIndices.put(resourcesGained, 0);

        add(deckPanel);
    }

    /*
     * Shows a list of resources gained by a player. Only displays when there
     * are one or more resources contained in given ResourceList.
     */
    @Override
    public void showResourcesGained(ResourceList resources)
    {
        if (resources.size() > 0)
        {
            resourcesGained.update(resources);
            deckPanel.showWidget(widgetIndices.get(resourcesGained));
            setPositionAndShow();
        }
    }

    private void setPositionAndShow()
    {
        Point2D location = gamePanel.getTopRightPlayerInfoBoxPosition(player);
        setPopupPosition(location.getX(), location.getY());
        this.show();
    }

}
