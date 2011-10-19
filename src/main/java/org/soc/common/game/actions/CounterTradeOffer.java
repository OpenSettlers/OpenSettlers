package org.soc.common.game.actions;

import org.soc.common.game.*;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.game.actions.GameBehaviour.DefaultReceivedActionInGame;
import org.soc.common.game.trading.*;
import org.soc.common.views.widgetsInterface.main.*;

public class CounterTradeOffer extends AbstractGameAction implements
        TradeResponse
{
  private MutableResourceList offeredResources = new MutableResourceListImpl();
  private MutableResourceList requestedResources = new MutableResourceListImpl();
  private TradeOffer originatingOffer;

  @Override public TradeOffer getOriginatingOffer()
  {
    return originatingOffer;
  }
  public TradeResponse setOriginatingOffer(TradeOffer originatingOffer)
  {
    this.originatingOffer = originatingOffer;
    return this;
  }
  /* Offered resources as seen from the offering players'perspective */
  public MutableResourceList getOfferedResources()
  {
    return offeredResources;
  }
  /* Requested resources as seen from the offering players' perspective */
  public MutableResourceList getRequestedResources()
  {
    return requestedResources;
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
    if (offeredResources == null || requestedResources == null)
    {
      invalidMessage = "OfferedResources and RequestedResources cannot be null";
      return false;
    }
    if (offeredResources.size() == 0 || requestedResources.size() == 0)
    {
      invalidMessage = "OfferedResources and RequestedResources cannot be empty";
      return false;
    }
    if (!player.resources().hasAtLeast(requestedResources))
    {
      invalidMessage = "Player does not have offered resources";
      return false;
    }
    if (originatingOffer.responses().containsResponse(player))
    {
      invalidMessage = "Player already has responded to the offer";
      return false;
    }
    return true;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.AbstractGameAction#perform(org.soc.common.game .Game) */
  @Override public void perform(Game game)
  {
    game.addTradeResponse(this);
    // TODO: fix message
    super.perform(game);
  }
  @Override public String toDoMessage()
  {
    return null;
  }
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return turnPhase.isTrading();
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return gamePhase.isPlayTurns();
  }
  @Override public void setTradeResources(TradePlayer tradePlayer)
  {
    tradePlayer.getRequestedResources().addList(requestedResources);
    tradePlayer.getOfferedResources().addList(offeredResources);
  }
  @Override public boolean isExpectedQueueType(GameAction actualAction)
  {
    return actualAction instanceof TradeResponse;
  }
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
  @Override public GameBehaviour opponentReceived(
          GameWidget gameWidget)
  {
    return new DefaultReceivedActionInGame(gameWidget, this);
  }
  @Override public GameBehaviour received(GameWidget gameWidget) {
    return new DefaultReceivedActionInGame(gameWidget, this);
  }
  @Override public boolean isAccepted()
  {
    return false;
  }
  @Override public boolean isCounterOffer()
  {
    return true;
  }
  @Override public boolean isRejection()
  {
    return false;
  }
}
