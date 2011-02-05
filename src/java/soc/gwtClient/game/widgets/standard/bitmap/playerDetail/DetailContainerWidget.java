package soc.gwtClient.game.widgets.standard.bitmap.playerDetail;

import java.util.HashMap;

import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.PlayerDetailContainerWidget;
import soc.gwtClient.game.abstractWidgets.PlayerDetailWidget;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;

/*
 * Popup shown at the right side from the player details widget. Manages PlayerDetailWidgets 
 * who give clues in what is currently happening in the game. Supports a mouseover widget to 
 * give details about a player's possessions when moused over a player widget.
 */
public class DetailContainerWidget extends PopupPanel implements
        PlayerDetailContainerWidget
{
    // Player of the container
    private GamePlayer player;

    // When mousing over, the moused over widget is overlayed on the current
    // widget if present
    private AbsolutePanel rootPanel = new AbsolutePanel();

    private ResourcesGainedDetailWidget resourcesGained;

    // Panel containing all the widgets for permanent viewing
    private DeckPanel deckPanel = new DeckPanel();
    private HashMap<IsWidget, Integer> widgetIndices = new HashMap<IsWidget, Integer>();
    private GamePanel gamePanel;
    private HorizontalPanel mouseOverPanel = new HorizontalPanel();
    private PlayerDetailWidget playerDetailWidget;

    // True when currently showing a permanent widget
    public boolean isShowing = false;

    public DetailContainerWidget(GamePlayer player, GamePanel gamePanel)
    {
        super();
        this.player = player;
        this.gamePanel = gamePanel;

        resourcesGained = new ResourcesGainedDetailWidget(player);
        deckPanel.add(resourcesGained);
        widgetIndices.put(resourcesGained, 0);

        rootPanel.add(deckPanel);
        rootPanel.add(mouseOverPanel);
        add(rootPanel);
    }

    /*
     * Shows a list of resources gained by a player. Only displays when there
     * are one or more resources contained in given ResourceList.
     */
    @Override
    public void showResourcesGained(ResourceList resources)
    {
        deckPanel.setVisible(true);
        if (resources.size() > 0)
        {
            resourcesGained.update(resources);
            deckPanel.showWidget(widgetIndices.get(resourcesGained));
            setPositionAndShow();
        }
        isShowing = true;
    }

    public void setPositionAndShow()
    {
        Point2D location = gamePanel.getTopRightPlayerInfoBoxPosition(player);
        setPopupPosition(location.getX(), location.getY());
        this.show();
    }

    public void hideMouseOverWidget()
    {
        mouseOverPanel.setVisible(false);
        mouseOverPanel.remove(playerDetailWidget);
        if (!isShowing)
            hide();
    }

    public void showMouseOverWidget(PlayerDetailWidget playerDetailWidget)
    {
        this.playerDetailWidget = playerDetailWidget;
        mouseOverPanel.setVisible(true);
        mouseOverPanel.add(playerDetailWidget);
        setPositionAndShow();
    }

    @Override
    public void hide()
    {
        super.hide();
        isShowing = false;
    }

    @Override
    public void hideCurrentWidget()
    {
        isShowing = false;
        deckPanel.setVisible(false);
        hide();
    }
}
