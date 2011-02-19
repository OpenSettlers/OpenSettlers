package soc.gwtClient.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import soc.common.game.VictoryPointItem;
import soc.common.game.VictoryPointsChangedEvent;
import soc.common.game.VictoryPointsChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class VictoryPointsDetailWidget extends AbstractPlayerDetailWidget
        implements VictoryPointsChangedEventHandler
{
    private Map<VictoryPointItem, Image> vpImages = new HashMap<VictoryPointItem, Image>();

    public VictoryPointsDetailWidget(GameWidget gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

        for (VictoryPointItem vp : player.getVictoryPoints())
        {
            Image vpImage = new Image(Resources.victoryPointItem(vp));
            vpImages.put(vp, vpImage);
            rootPanel.add(vpImage);
        }

        player.getVictoryPoints().addVictoryPointsChangedListener(this);
    }

    @Override
    public void onVictoryPointsChanged(VictoryPointsChangedEvent event)
    {
        if (event.getAddedPoint() != null)
        {
            Image vpImage = new Image(Resources.victoryPointItem(event
                    .getAddedPoint()));
            vpImages.put(event.getAddedPoint(), vpImage);
            rootPanel.add(vpImage);
        }
        if (event.getRemovedPoint() != null)
        {
            Image vpImage = vpImages.get(event.getRemovedPoint());
            rootPanel.remove(vpImage);
        }
    }

}
