package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.common.game.developmentCards.DevelopmentCardsChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.IActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.abstractWidgets.IPlayDevelopmentCardWidget;
import soc.gwtClient.game.abstractWidgets.factories.DevelopmentCardWidgetFactory;
import soc.gwtClient.game.widgets.standard.bitmap.developmentCards.DevelopmentCardBitmapWidgetFactory;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PlayDevelopmentCardBitmapWidget implements
        IPlayDevelopmentCardWidget, DevelopmentCardsChangedEventHandler,
        IActionWidget
{
    protected AbsolutePanel rootPanel = new AbsolutePanel();
    protected PopupPanel menuBar = new PopupPanel();
    protected VerticalPanel verticalPanel = new VerticalPanel();
    protected GamePlayer player;
    protected IGamePanel gamePanel;
    protected PushButton btnPlayDevelopmentCard = new PushButton(new Image(
            Resources.icons().developmentCardBack()));
    protected Label lblAmountDvelopmentCards = new Label();
    protected boolean isMenubarShown = false;
    protected DevelopmentCardWidgetFactory devCardWidgetFactory = new DevelopmentCardBitmapWidgetFactory();

    public PlayDevelopmentCardBitmapWidget(GamePlayer player, IGamePanel gamePanel)
    {
        this.player = player;
        this.gamePanel = gamePanel;

        rootPanel.add(btnPlayDevelopmentCard);
        rootPanel.add(lblAmountDvelopmentCards);

        menuBar.add(verticalPanel);

        for (DevelopmentCard devCard : player.getDevelopmentCards())
        {
            verticalPanel.add(devCardWidgetFactory.createWidget(devCard,
                    gamePanel));
        }
        player.getDevelopmentCards().addDevelopmentCardsChangedEventHandler(
                this);

        btnPlayDevelopmentCard.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                if (isMenubarShown)
                {
                    menuBar.hide();
                }
                else
                {
                    menuBar.setPopupPosition(btnPlayDevelopmentCard
                            .getOffsetWidth(), btnPlayDevelopmentCard
                            .getOffsetHeight()
                            - menuBar.getAbsoluteTop());
                    menuBar.show();
                }
                isMenubarShown = !isMenubarShown;
            }
        });
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
    {
        // Update label showing amount of cards player currently has
        lblAmountDvelopmentCards.setText(Integer.toString(player
                .getDevelopmentCardsCount()));
        if (event.getAddedCard() != null)
        {
            verticalPanel.add(devCardWidgetFactory.createWidget(event
                    .getAddedCard(), gamePanel));
        }
        // TODO:implement remove
    }

    @Override
    public GamePlayer getPlayer()
    {
        return player;
    }

    @Override
    public IActionWidget setEnabled(boolean enabled)
    {
        return null;
    }

}
