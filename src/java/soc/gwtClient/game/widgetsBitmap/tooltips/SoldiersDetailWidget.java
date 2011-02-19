package soc.gwtClient.game.widgetsBitmap.tooltips;

import java.util.HashMap;
import java.util.Map;

import soc.common.board.pieces.ArmyChangedEvent;
import soc.common.board.pieces.ArmyChangedEventHandler;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.AbstractPlayerDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class SoldiersDetailWidget extends AbstractPlayerDetailWidget implements
        ArmyChangedEventHandler
{
    private Map<Soldier, Image> soldierImages = new HashMap<Soldier, Image>();

    public SoldiersDetailWidget(GameWidget gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

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
