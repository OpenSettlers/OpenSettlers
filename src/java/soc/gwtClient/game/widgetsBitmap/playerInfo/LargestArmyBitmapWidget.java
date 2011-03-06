package soc.gwtClient.game.widgetsBitmap.playerInfo;

import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.generic.ToolTip;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsAbstract.playerInfo.AbstractLargestArmyWidget;
import soc.gwtClient.game.widgetsBitmap.tooltips.SoldiersToolTip;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class LargestArmyBitmapWidget extends AbstractLargestArmyWidget
                implements MouseOutHandler, MouseOverHandler
{
    Image largestAmryImage = new Image(Resources.icons().robber16());
    Label amountSoldiers = new Label();
    Soldier soldier = new Soldier();
    ToolTip toolTip;

    public LargestArmyBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);

        toolTip = new SoldiersToolTip(gameWidget, player);

        amountSoldiers.setText(Integer.toString(player
                        .getPlayedDevelopmentCards().ofType(new Soldier())
                        .size()));
        largestAmryImage.setSize("16px", "16px");

        rootPanel.add(largestAmryImage);
        rootPanel.add(amountSoldiers);

        rootPanel.addDomHandler(this, MouseOutEvent.getType());
        rootPanel.addDomHandler(this, MouseOverEvent.getType());
    }

    @Override
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
    {
        amountSoldiers.setText(Integer.toString(player
                        .getPlayedDevelopmentCards().ofType(soldier).size()));
    }

    @Override
    public void onMouseOut(MouseOutEvent event)
    {
        gameWidget.getToolTipManager().hideToolTip(toolTip);
    }

    @Override
    public void onMouseOver(MouseOverEvent event)
    {
        gameWidget.getToolTipManager().showToolTip(toolTip);
    }

}
