package soc.gwtClient.game.widgets.standard.bitmap.playerDetail;

import java.util.HashMap;

import soc.common.game.VictoryPointItem;
import soc.common.game.VictoryPointsChangedEvent;
import soc.common.game.VictoryPointsChangedEventHandler;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.common.game.player.GamePlayer;
import soc.common.utils.NotImplementedException;
import soc.gwtClient.game.abstractWidgets.AbstractPlayerDetailWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

/*
 * Shows amount of victory points by displaying each victory point as an image
 */
public class VictoryPointDetailWidget extends AbstractPlayerDetailWidget
        implements VictoryPointsChangedEventHandler
{
    private HashMap<VictoryPoint, Image> pointsIcons = new HashMap<VictoryPoint, Image>();

    public VictoryPointDetailWidget(GamePlayer player)
    {
        super(player);

        update();
        player.getVictoryPoints().addVictoryPointsChangedListener(this);
    }

    private void update()
    {
        for (VictoryPointItem vp : player.getVictoryPoints())
        {
            rootPanel.add(new Image(Resources.victoryPointItem(vp)));
        }
    }

    @Override
    public void onVictoryPointsChanged(VictoryPointsChangedEvent event)
    {
        if (event.getAddedPoint() != null)
        {
            rootPanel.add(new Image(Resources.victoryPointItem(event
                    .getAddedPoint())));
        }
        if (event.getRemovedPoint() != null)
        {
            // TODO: implement
            throw new NotImplementedException();
        }
    }

}