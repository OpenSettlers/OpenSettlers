package soc.gwtClient.game.widgetsAbstract.playerInfo;

import soc.common.game.developmentCards.DevelopmentCardsChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.payerInfo.LargestArmyDetailWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractLargestArmyWidget implements LargestArmyDetailWidget,
        DevelopmentCardsChangedEventHandler
{
    protected ComplexPanel rootPanel;
    protected GamePlayer player;
    protected GameWidget gameWidget;

    public AbstractLargestArmyWidget(GameWidget gameWidget, GamePlayer player)
    {
        this.gameWidget = gameWidget;
        this.player = player;

        rootPanel = createRootPanel();

        player.getPlayedDevelopmentCards()
                .addDevelopmentCardsChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.client.game.ILargestArmyWidget#createRootPanel()
     */
    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }
}
