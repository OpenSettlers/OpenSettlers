package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.game.DevelopmentCard;
import org.soc.common.game.DevelopmentCardsChangedEvent;
import org.soc.common.game.DevelopmentCardsChangedEvent.DevelopmentCardsChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.actions.Action.ActionPresenter;
import org.soc.common.game.actions.PlayDevelopmentCard.PlayDevelopmentCardView;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent.WantsPlayDevelopmentCardHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.PlayDevelopmentCardWidget;

import com.google.gwt.user.client.ui.Widget;

public class PlayDevelopmentCardBitmapWidget implements
        PlayDevelopmentCardWidget, DevelopmentCardsChangedHandler,
        ActionPresenter
{
  protected GamePlayer player;
  protected GameWidget gameWidget;
  private PlayDevelopmentCardView view;

  public PlayDevelopmentCardBitmapWidget(GamePlayer player, GameWidget gameWidget) {
    this.player = player;
    this.gameWidget = gameWidget;
    view.addWantsPlayDevelopmentCardHandler(new WantsPlayDevelopmentCardHandler() {
      @Override public void onWantsPlayDevelopmentCard(WantsPlayDevelopmentCardEvent event) {
        // TODO Auto-generated method stub
      }
    });
    for (DevelopmentCard devCard : player.developmentCards())
      view.add(devCard);
    player.developmentCards().addDevelopmentCardsChangedHandler(this);
  }
  @Override public Widget asWidget() {
    return (Widget) view;
  }
  @Override public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event) {
    view.setAmount(player.developmentCards().size());
    if (event.getAddedCard() != null) {
      view.add(event.getAddedCard());
    }
    if (event.getRemovedCard() != null) {
      view.remove(event.getRemovedCard());
    }
  }
  @Override public GamePlayer getPlayer() {
    return player;
  }
  @Override public ActionPresenter setEnabled(boolean enabled) {
    if (enabled) {
      view.enable();
    } else {
      view.disable();
    }
    return this;
  }
}
