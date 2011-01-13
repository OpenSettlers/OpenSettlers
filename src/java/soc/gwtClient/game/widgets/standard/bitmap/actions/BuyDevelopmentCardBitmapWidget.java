package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.TurnChangedEvent;
import soc.common.game.TurnChangedEventHandler;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedEvent;
import soc.common.game.gamePhase.turnPhase.TurnPhaseChangedHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BuyDevelopmentCardBitmapWidget extends AbstractActionWidget
        implements ResourcesChangedEventHandler, TurnChangedEventHandler,
        TurnPhaseChangedHandler, GamePhaseChangedEventHandler
{
    AbsolutePanel rootPanel = new AbsolutePanel();
    VerticalPanel tradePanel = new VerticalPanel();
    Image trade1 = new Image(Resources.icons().trade());
    Image trade2 = new Image(Resources.icons().trade());
    Image trade3 = new Image(Resources.icons().trade());
    PushButton btnbuyDvelopmentcard = new PushButton(new Image(Resources
            .icons().buyDvelopmentCard()));
    BuyDevelopmentCard buyDevelopmentCard = new BuyDevelopmentCard();

    public BuyDevelopmentCardBitmapWidget(final GamePanel gamePanel,
            final GamePlayer player)
    {
        super(gamePanel, player);

        player.getResources().addResourcesChangedEventHandler(this);
        gamePanel.getGame().addTurnchangedeventHandler(this);
        gamePanel.getGame().addTurnPhaseChangedHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);

        tradePanel.add(trade1);
        tradePanel.add(trade2);
        tradePanel.add(trade3);

        rootPanel.setSize("4em", "4em");
        rootPanel.add(btnbuyDvelopmentcard);
        rootPanel.add(tradePanel);

        btnbuyDvelopmentcard.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                VictoryPoint vp = new VictoryPoint();
                gamePanel.startAction(new BuyDevelopmentCard().setResources(
                        vp.getCost()).setPlayer(player));
            }
        });
    }

    @Override
    protected void updateEnabled()
    {
        checkEnabled();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    private void enableUI()
    {
        btnbuyDvelopmentcard.setEnabled(true);
    }

    private void disableUI()
    {
        btnbuyDvelopmentcard.setEnabled(false);
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
        if (enabled && player.isOnTurn())
        {
            if (DevelopmentCard.canPay(player))
            {
                enableUI();
                return;
            }
        }
        disableUI();
    }

    @Override
    public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
    {
        checkEnabled();
    }

    @Override
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        checkEnabled();
    }
}
