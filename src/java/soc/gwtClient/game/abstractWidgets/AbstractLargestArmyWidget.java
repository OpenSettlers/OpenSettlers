package soc.gwtClient.game.abstractWidgets;

import soc.common.game.developmentCards.DevelopmentCardsChangedEventHandler;
import soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractLargestArmyWidget implements LargestArmyWidget,
        DevelopmentCardsChangedEventHandler
{
    protected ComplexPanel rootPanel;
    protected GamePlayer player;
    protected GamePanel gamePanel;

    public AbstractLargestArmyWidget(GamePanel gamePanel, GamePlayer player)
    {
        this.gamePanel = gamePanel;
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
