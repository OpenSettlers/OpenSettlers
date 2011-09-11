package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.game.DevelopmentCard.AbstractDevelopmentCard;
import org.soc.common.game.GamePhaseChangedEvent;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.common.game.TurnChangedEvent;
import org.soc.common.game.TurnChangedEvent.TurnChangedHandler;
import org.soc.common.game.TurnPhaseChangedEvent;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.game.actions.BuyDevelopmentCard;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actions.AbstractActionWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BuyDevelopmentCardBitmapWidget extends AbstractActionWidget
        implements ResourcesChangedHandler,
        TurnChangedHandler, TurnPhaseChangedHandler,
        GamePhaseChangedHandler
{
  AbsolutePanel rootPanel = new AbsolutePanel();
  VerticalPanel tradePanel = new VerticalPanel();
  Image trade1 = new Image(R.icons().trade16());
  Image trade2 = new Image(R.icons().trade16());
  Image trade3 = new Image(R.icons().trade16());
  PushButton btnbuyDvelopmentcard = new PushButton(new Image(R
          .icons().buyDvelopmentCard48()));
  BuyDevelopmentCard buyDevelopmentCard = new BuyDevelopmentCard();

  public BuyDevelopmentCardBitmapWidget(final GameWidget gameWidget,
          final GamePlayer player)
  {
    super(gameWidget, player);
    player.resources().addResourcesChangedHandler(this);
    gameWidget.game().addTurnChangedHandler(this);
    gameWidget.game().addTurnPhaseChangedHandler(this);
    gameWidget.game().addGamePhaseChangedHandler(this);
    tradePanel.add(trade1);
    tradePanel.add(trade2);
    tradePanel.add(trade3);
    rootPanel.setSize("4em", "4em");
    rootPanel.add(btnbuyDvelopmentcard);
    rootPanel.add(tradePanel, 3, 3);
    btnbuyDvelopmentcard.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        gameWidget.startAction(new BuyDevelopmentCard().setResources(
                AbstractDevelopmentCard.getCost()).setPlayer(
                player));
      }
    });
  }
  @Override protected void updateEnabled()
  {
    checkEnabled();
  }
  @Override public Widget asWidget()
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
  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
  {
    checkEnabled();
  }
  @Override public void onTurnChanged(TurnChangedEvent event)
  {
    checkEnabled();
  }
  /* Update state of the panel */
  private void checkEnabled()
  {
    // TODO: make logic accurate for diamonds & trades
    setTradesNeededToBuild();
    if (enabled && player.isOnTurn())
    {
      if (gameWidget.game().isAllowed(buyDevelopmentCard)
              && AbstractDevelopmentCard.canPay(player))
      {
        enableUI();
        return;
      }
    }
    disableUI();
  }
  @Override public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
  {
    checkEnabled();
  }
  @Override public void onGamePhaseChanged(GamePhaseChangedEvent event)
  {
    checkEnabled();
  }
  private void setTradesNeededToBuild()
  {
    if (AbstractDevelopmentCard.canPay(player))
    {
      int amountTradesNeeded = player
              .resources()
              .getNeededResources(
                      AbstractDevelopmentCard.getCost())
              .size();
      trade1.setVisible(amountTradesNeeded >= 1);
      trade2.setVisible(amountTradesNeeded >= 2);
      trade3.setVisible(amountTradesNeeded >= 3);
    } else
    {
      trade1.setVisible(false);
      trade2.setVisible(false);
      trade3.setVisible(false);
    }
  }
}
