package org.soc.common.game.actions;

import org.soc.common.game.*;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.internationalization.*;

public class LooseCards
        extends
        AbstractTurnAction {
  private ResourceList lostCards;
  private int amountCardsToLoose = 4;

  @Override public GameAction setPlayer(GamePlayer player) {
    this.player = player;
    this.sender = player.user().id();
    amountCardsToLoose = player.resources().halfCount();
    return this;
  }
  public ResourceList getLostCards() {
    return lostCards;
  }
  public LooseCards setLostCards(ResourceList lostCards) {
    this.lostCards = lostCards;
    return this;
  }
  public int getAmountCardsToLoose() {
    return amountCardsToLoose;
  }
  public LooseCards setAmountCardsToLoose(int amountCardsToLoose) {
    this.amountCardsToLoose = amountCardsToLoose;
    return this;
  }
  @Override public boolean isValid(Game game) {
    if (!super.isValid(game)) {
      return false;
    }
    if (amountCardsToLoose < 4) {
      invalidMessage = "Amount of cards to loose should be 4 or more";
      return false;
    }
    if (lostCards == null)
    {
      invalidMessage = "LostCards cannot be null";
      return false;
    }
    if (lostCards.size() != amountCardsToLoose)
    {
      invalidMessage = "Amount of resources to loose should equal amount of lost cards";
      return false;
    }
    return true;
  }
  @Override public void perform(Game game) {
    // Move resources from player to bank
    game.bank().moveListFrom(player.resources(), lostCards);
    super.perform(game);
  }
  @Override public String toDoMessage() {
    return I.get()
            .actions()
            .looseCardsToDo(player.user().name(),
                    amountCardsToLoose);
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return turnPhase.isDiceRoll();
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isPlayTurns();
  }
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
}
