package org.soc.gwt.client.game.widgetsBitmap.actions;

import java.util.HashMap;

import org.soc.common.game.DevelopmentCard;
import org.soc.common.game.DevelopmentCardsChangedEvent;
import org.soc.common.game.DevelopmentCardsChangedEvent.DevelopmentCardsChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget.DevelopmentCardWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.PlayDevelopmentCardWidget;
import org.soc.gwt.client.game.widgetsBitmap.developmentCards.DevelopmentCardBitmapWidgetFactory;
import org.soc.gwt.client.images.R;

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
        PlayDevelopmentCardWidget, DevelopmentCardsChangedHandler,
        ActionWidget
{
  protected AbsolutePanel rootPanel = new AbsolutePanel();
  protected PopupPanel menuBar = new PopupPanel();
  protected VerticalPanel verticalPanel = new VerticalPanel();
  protected GamePlayer player;
  protected GameWidget gameWidget;
  protected PushButton btnPlayDevelopmentCard = new PushButton(new Image(
          R.icons().developmentCardBack48()));
  protected Label lblAmountDvelopmentCards = new Label();
  protected boolean isMenubarShown = false;
  protected DevelopmentCardWidgetFactory devCardWidgetFactory;
  protected HashMap<DevelopmentCard, DevelopmentCardWidget> devCardsWidgets = new HashMap<DevelopmentCard, DevelopmentCardWidget>();

  public PlayDevelopmentCardBitmapWidget(GamePlayer player,
          GameWidget gameWidget)
  {
    this.player = player;
    this.gameWidget = gameWidget;
    devCardWidgetFactory = new DevelopmentCardBitmapWidgetFactory(
            gameWidget);
    rootPanel.add(btnPlayDevelopmentCard);
    rootPanel.add(lblAmountDvelopmentCards);
    menuBar.add(verticalPanel);
    for (DevelopmentCard devCard : player.developmentCards())
    {
      DevelopmentCardWidget devCardWidget = devCard
              .createPlayCardWidget(devCardWidgetFactory);
      verticalPanel.add(devCardWidget);
      devCardsWidgets.put(devCard, devCardWidget);
    }
    player.developmentCards().addDevelopmentCardsChangedHandler(
            this);
    btnPlayDevelopmentCard.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        if (isMenubarShown)
        {
          menuBar.hide();
        } else
        {
          menuBar.setPopupPosition(
                  btnPlayDevelopmentCard.getOffsetWidth(),
                  btnPlayDevelopmentCard.getOffsetHeight()
                          - menuBar.getAbsoluteTop());
          menuBar.show();
        }
        isMenubarShown = !isMenubarShown;
      }
    });
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
  {
    // Update label showing amount of cards player currently has
    lblAmountDvelopmentCards.setText(Integer.toString(player
            .developmentCards().size()));
    if (event.getAddedCard() != null)
    {
      DevelopmentCardWidget devCardWidget = event.getAddedCard()
              .createPlayCardWidget(devCardWidgetFactory);
      verticalPanel.add(devCardWidget);
      devCardsWidgets.put(event.getAddedCard(), devCardWidget);
    }
    if (event.getRemovedCard() != null)
    {
      DevelopmentCardWidget widget = devCardsWidgets.get(event
              .getRemovedCard());
      verticalPanel.remove(widget);
    }
  }
  @Override public GamePlayer getPlayer()
  {
    return player;
  }
  @Override public ActionWidget setEnabled(boolean enabled)
  {
    btnPlayDevelopmentCard.setEnabled(enabled);
    return this;
  }
}
