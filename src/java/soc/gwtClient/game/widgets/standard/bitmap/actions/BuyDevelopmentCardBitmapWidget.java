package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.TurnChangedEvent;
import soc.common.game.TurnChangedEventHandler;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BuyDevelopmentCardBitmapWidget extends AbstractActionWidget
        implements ResourcesChangedEventHandler, TurnChangedEventHandler
{
    AbsolutePanel rootPanel = new AbsolutePanel();
    VerticalPanel tradePanel = new VerticalPanel();
    Image trade1 = new Image(Resources.icons().trade());
    Image trade2 = new Image(Resources.icons().trade());
    Image trade3 = new Image(Resources.icons().trade());
    PushButton btnbuyDvelopmentcard = new PushButton(new Image(Resources
            .icons().buyDvelopmentCard()));
    BuyDevelopmentCard buyDevelopmentCard = new BuyDevelopmentCard();

    public BuyDevelopmentCardBitmapWidget(IGamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);

        player.getResources().addResourcesChangedEventHandler(this);
        player.addOnTurnChangedEventHandler(this);

        tradePanel.add(trade1);
        tradePanel.add(trade2);
        tradePanel.add(trade3);
    }

    @Override
    protected void updateEnabled()
    {
        btnbuyDvelopmentcard.setEnabled(enabled);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        checkEnabled();
    }

    @Override
    public void onTurnChanged(TurnChangedEvent event)
    {
        checkEnabled();
    }

    /*
     * Update state of the panel
     */
    private void checkEnabled()
    {
        // TODO: make logic accurate for diamonds & trades
        if (player.isOnTurn())
        {
            if (DevelopmentCard.canPay(player))
            {
                setEnabled(true);
            }
        }
        setEnabled(false);
    }
}
