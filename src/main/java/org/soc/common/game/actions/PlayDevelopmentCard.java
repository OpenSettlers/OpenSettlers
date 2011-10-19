package org.soc.common.game.actions;

import org.soc.common.game.*;
import org.soc.common.game.DevelopmentCard.AbstractDevelopmentCard;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent.HasWantsPlayDevelopmentCardHandlers;
import org.soc.common.internationalization.*;

import com.gwtplatform.dispatch.annotation.*;

public class PlayDevelopmentCard extends AbstractTurnAction
{
  private AbstractDevelopmentCard developmentCard;

  /** @return the development card */
  public DevelopmentCard getDevelopmentcard()
  {
    return developmentCard;
  }
  /** @param developmentcard the development card to set */
  public PlayDevelopmentCard setDevelopmentcard(
          AbstractDevelopmentCard developmentcard)
  {
    this.developmentCard = developmentcard;
    return this;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.GameAction#isValid(org.soc.common.game.Game) */
  @Override public boolean isValid(Game game)
  {
    if (!super.isValid(game))
      return false;
    if (developmentCard == null)
    {
      invalidMessage = "Devcard cannot be null";
      return false;
    }
    GamePlayer player = game.playerById(sender);
    if (!developmentCard.isPlayable())
    {
      invalidMessage = "You already played a non-victorypoint devcard this turn, or your devcards are bought this turn";
      return false;
    }
    if (developmentCard.hasSummoningSickness()
            && developmentCard.turnBought() == game
                    .turn().getID())
    {
      invalidMessage = "Development card is not playable. Wait one turn";
    }
    if (!developmentCard.isValid(game))
    {
      invalidMessage = "Development card is not valid. Reason: "
              + developmentCard.invalidMessage();
      return false;
    }
    if (!player.developmentCards().contains(developmentCard))
    {
      invalidMessage = "Player does not have development card in possesion";
      return false;
    }
    return true;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.GameAction#perform(org.soc.common.game.Game) */
  @Override public void perform(Game game)
  {
    // remove devcard from players hand
    player.developmentCards().remove(developmentCard);
    player.playedDevelopmentCards().add(developmentCard);
    // Execute devcard
    developmentCard.play(game, player);
    // Mark all other cards needing to wait one turn as unplayable, if we
    // play a non-unique dvcard
    if (developmentCard.hasLimitOnePerTurn())
      for (DevelopmentCard dc : player.developmentCards())
        if (dc.hasLimitOnePerTurn())
          dc.setPlayable(false);
    message = developmentCard.message();
    super.perform(game);
  }
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return turnPhase.isBeforeDiceRoll() || turnPhase.isBuilding();
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return gamePhase.isPlayTurns();
  }
  @Override public String toDoMessage()
  {
    return I.get().actions().noToDo();
  }
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return actionWidgetFactory.createPlayDevelopmentCardWidget();
  }

  public interface PlayDevelopmentCardView extends HasWantsPlayDevelopmentCardHandlers {
    @GenEvent public class WantsPlayDevelopmentCard {}

    public void remove(DevelopmentCard developmentCard);
    public void add(DevelopmentCard developmentCard);
    public void enable();
    public void disable();
    public void setAmount(int amountDevelopmentCards);
  }
}
