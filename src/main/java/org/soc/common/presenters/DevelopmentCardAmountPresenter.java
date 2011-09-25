package org.soc.common.presenters;

import org.soc.common.game.DevelopmentCardsChangedEvent;
import org.soc.common.game.DevelopmentCardsChangedEvent.DevelopmentCardsChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;

import com.google.inject.Inject;

public class DevelopmentCardAmountPresenter {
  public interface DevelopmentCardAmountView {
    public void setAmountDevelopmentCards(int amount);
  }

  @Inject public DevelopmentCardAmountPresenter(final DevelopmentCardAmountView view,
          GameWidget gameWidget, final GamePlayer player) {
    player.developmentCards()
            .addDevelopmentCardsChangedHandler(new DevelopmentCardsChangedHandler() {
              @Override public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event) {
                view.setAmountDevelopmentCards(player.developmentCards().size());
              }
            });
  }
}
