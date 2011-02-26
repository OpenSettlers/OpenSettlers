package soc.gwtClient.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import soc.common.board.pieces.ArmyChangedEvent;
import soc.common.board.pieces.ArmyChangedEventHandler;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.toolTips.AbstractPlayerInfoToolTip;
import soc.gwtClient.images.Resources;

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
            Image soldierImage = new Image(Resources.icons().soldier());
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
            Image soldierImage = new Image(Resources.icons().soldier());
            soldierImages.put(event.getAddedSoldier(), soldierImage);
            rootPanel.add(soldierImage);
        }
    }

}
