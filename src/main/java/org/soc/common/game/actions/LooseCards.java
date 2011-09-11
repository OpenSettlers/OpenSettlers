package org.soc.common.game.actions;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourceList;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;

public class LooseCards extends AbstractTurnAction
{
  @Override public Icon icon()
  {
    return new IconImpl(null, null, null, null);
  }
  @Override public String name()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getLocalizedName()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getDescription()
  {
    // TODO Auto-generated method stub
    return null;
  }

  private ResourceList lostCards;
  private int amountCardsToLoose = 4;

  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.AbstractGameAction#setPlayer(org.soc.common
   * .game.player.GamePlayer) */
  @Override public GameAction setPlayer(GamePlayer player)
  {
    this.player = player;
    this.sender = player.user().id();
    amountCardsToLoose = player.resources().halfCount();
    return this;
  }
  /** @return the lostCards */
  public ResourceList getLostCards()
  {
    return lostCards;
  }
  /** @param lostCards the lostCards to set */
  public LooseCards setLostCards(ResourceList lostCards)
  {
    this.lostCards = lostCards;
    return this;
  }
  /** @return the amountCardsToLoose */
  public int getAmountCardsToLoose()
  {
    return amountCardsToLoose;
  }
  /** @param amountCardsToLoose the amountCardsToLoose to set */
  public LooseCards setAmountCardsToLoose(int amountCardsToLoose)
  {
    this.amountCardsToLoose = amountCardsToLoose;
    return this;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.AbstractGameAction#isValid(org.soc.common.game .Game) */
  @Override public boolean isValid(Game game)
  {
    if (!super.isValid(game))
    {
      return false;
    }
    if (amountCardsToLoose < 4)
    {
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
  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.turnActions.AbstractTurnAction#perform(
   * org.soc.common.game.Game) */
  @Override public void perform(Game game)
  {
    // Move resources from player to bank
    game.bank().swapResourcesFrom(lostCards, player.resources());
    super.perform(game);
  }
  @Override public String toDoMessage()
  {
    return I.get()
            .actions()
            .looseCardsToDo(player.user().name(),
                    amountCardsToLoose);
  }
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return turnPhase.isDiceRoll();
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return gamePhase.isPlayTurns();
  }
  @Override public ActionWidget createActionWidget(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
}
