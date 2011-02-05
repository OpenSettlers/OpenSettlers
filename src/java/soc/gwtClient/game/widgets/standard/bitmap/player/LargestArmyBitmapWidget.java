package soc.gwtClient.game.widgets.standard.bitmap.player;

import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractLargestArmyWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.widgets.standard.bitmap.playerDetail.SoldiersDetailWidget;
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
    Image largestAmryImage = new Image(Resources.icons().robberSmall());
    Label amountSoldiers = new Label();
    Soldier soldier = new Soldier();
    SoldiersDetailWidget soldiersDetailWidget;

    public LargestArmyBitmapWidget(GamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

        soldiersDetailWidget = new SoldiersDetailWidget(player);

        amountSoldiers.setText(Integer.toString(player
                .getPlayedDevelopmentCards().ofType(new Soldier()).size()));
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
        gamePanel.getDetailContainerManager().hideMouseOverDetail(player);
    }

    @Override
    public void onMouseOver(MouseOverEvent event)
    {
        gamePanel.getDetailContainerManager().showMouseOverDetail(player,
                soldiersDetailWidget);
    }

}
