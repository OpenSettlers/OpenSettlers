package org.soc.common.presenters;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.Resource;
import org.soc.common.game.ResourceList;
import org.soc.common.presenters.CardPickedEvent.CardPickedHandler;
import org.soc.common.presenters.CardPickedEvent.HasCardPickedHandlers;
import org.soc.common.presenters.CardRemovedEvent.CardRemovedHandler;
import org.soc.common.presenters.CardRemovedEvent.HasCardRemovedHandlers;
import org.soc.common.presenters.DiscardCardsEvent.DiscardCardsHandler;
import org.soc.common.presenters.DiscardCardsEvent.HasDiscardCardsHandlers;
import org.soc.common.views.widgetsInterface.main.GameWidget;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public class LooseCardsPresenter {
  public interface LooseCardsView extends HasCardPickedHandlers, HasCardRemovedHandlers,
          HasDiscardCardsHandlers {
    public void removeCard(Resource resource);
    public void pickCard(Resource card);
    public void setHand(ResourceList hand);
    public void setPlayer(GamePlayer player);

    @GenEvent public class CardRemoved {
      @Order(0) Resource card;
    }

    @GenEvent public class CardPicked {
      @Order(0) Resource card;
    }

    @GenEvent public class DiscardCards {
      @Order(0) ResourceList cards;
    }
  }

  private GameWidget gameWidget;
  private LooseCardsView view;

  @Inject public LooseCardsPresenter(final LooseCardsView view, GameWidget gameWidget) {
    this.view = view;
    this.gameWidget = gameWidget;
    view.addCardPickedHandler(new CardPickedHandler() {
      @Override public void onCardPicked(CardPickedEvent event) {
        view.pickCard(event.getCard());
      }
    });
    view.addCardRemovedHandler(new CardRemovedHandler() {
      @Override public void onCardRemoved(CardRemovedEvent event) {
        // TODO Auto-generated method stub
      }
    });
    view.addDiscardCardsHandler(new DiscardCardsHandler() {
      @Override public void onDiscardCards(DiscardCardsEvent event) {
        // TODO Auto-generated method stub
      }
    });
  }
}
