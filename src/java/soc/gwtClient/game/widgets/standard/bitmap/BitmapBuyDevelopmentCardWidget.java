package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.Player;
import soc.common.game.TurnChangedEvent;
import soc.common.game.TurnChangedEventHandler;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

public class BitmapBuyDevelopmentCardWidget extends AbstractActionWidget implements
    ResourcesChangedEventHandler, TurnChangedEventHandler
{
    PushButton btnbuyDvelopmentcard = new PushButton(new Image("icons/48/BuyDevcard48.png"));
    BuyDevelopmentCard buyDevelopmentCard = new BuyDevelopmentCard();
    
    public BitmapBuyDevelopmentCardWidget(IGamePanel gamePanel, Player player)
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
