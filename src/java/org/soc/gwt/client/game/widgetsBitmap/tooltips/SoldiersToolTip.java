package org.soc.gwt.client.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import org.soc.common.board.pieces.ArmyChangedEvent;
import org.soc.common.board.pieces.ArmyChangedEventHandler;
import org.soc.common.game.developmentCards.standard.Soldier;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;
import org.soc.gwt.client.images.Resources;


import com.google.gwt.user.client.ui.Image;

public class SoldiersToolTip extends AbstractPlayerInfoToolTip implements
                ArmyChangedEventHandler
{
    private Map<Soldier, Image> soldierImages = new HashMap<Soldier, Image>();

    public SoldiersToolTip(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        for (Soldier soldier : player.getArmy().getSoldiers())
        {
            Image soldierImage = new Image(Resources.mediumIcon(soldier));
            soldierImages.put(soldier, soldierImage);
            rootPanel.add(soldierImage);
        }

        player.getArmy().addSoldiersChangedEventHandler(this);
    }

    @Override
    public void onArmyChanged(ArmyChangedEvent event)
    {
        if (event.getAddedSoldier() != null)
        {
            Image soldierImage = new Image(Resources.mediumIcon(event
                            .getAddedSoldier()));
            soldierImages.put(event.getAddedSoldier(), soldierImage);
            rootPanel.add(soldierImage);
        }
    }
}
