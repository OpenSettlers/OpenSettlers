package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.HashMap;

import org.soc.common.game.VictoryPointItem;
import org.soc.common.game.VictoryPointsChangedEvent;
import org.soc.common.game.VictoryPointsChangedEventHandler;
import org.soc.common.game.developmentCards.standard.VictoryPoint;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.utils.NotImplementedException;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;


import com.google.gwt.user.client.ui.Image;

/*
 * Shows amount of victory points by displaying each victory point as an image
 */
public class VictoryPointDetailWidget extends AbstractPlayerInfoToolTip
        implements VictoryPointsChangedEventHandler
{
    private HashMap<VictoryPoint, Image> pointsIcons = new HashMap<VictoryPoint, Image>();

    public VictoryPointDetailWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        update();
        player.getVictoryPoints().addVictoryPointsChangedListener(this);
    }

    private void update()
    {
        for (VictoryPointItem vp : player.getVictoryPoints())
        {
            rootPanel.add(new Image(vp.getMeta().icon().iconDefault()));
        }
    }

    @Override
    public void onVictoryPointsChanged(VictoryPointsChangedEvent event)
    {
        if (event.getAddedPoint() != null)
        {
            rootPanel.add(new Image(event.getAddedPoint().getMeta().icon()
                    .iconDefault()));
        }
        if (event.getRemovedPoint() != null)
        {
            // TODO: implement
            throw new NotImplementedException();
        }
    }

}
