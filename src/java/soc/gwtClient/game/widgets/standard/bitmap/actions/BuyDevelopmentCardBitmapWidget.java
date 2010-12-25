package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.Player;
import soc.common.game.TurnChangedEvent;
import soc.common.game.TurnChangedEventHandler;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class BuyDevelopmentCardBitmapWidget extends AbstractActionWidget
        implements ResourcesChangedEventHandler, TurnChangedEventHandler
{
    PushButton btnbuyDvelopmentcard = new PushButton(new Image(Resources
            .icons().buyDvelopmentCard()));
    BuyDevelopmentCard buyDevelopmentCard = new BuyDevelopmentCard();

    public BuyDevelopmentCardBitmapWidget(IGamePanel gamePanel, Player player)
    {
        super(gamePanel, player);

        player.getResources().addResourcesChangedEventHandler(this);
        player.addOnTurnChangedEventHandler(this);
    }

    @Override
    protected void updateEnabled()
    {
        btnbuyDvelopmentcard.setEnabled(enabled);
    }

    @Override
    public Widget asWidget()
    {
        return btnbuyDvelopmentcard;
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

    private void checkEnabled()
    {
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
