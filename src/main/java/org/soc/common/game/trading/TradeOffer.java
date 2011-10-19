package org.soc.common.game.trading;

import org.soc.common.game.*;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.server.actions.*;

public class TradeOffer extends AbstractGameAction
{
  private MutableResourceList offeredResources = new MutableResourceListImpl();
  private MutableResourceList requestedResources = new MutableResourceListImpl();
  private TradeResponseList responses = new TradeResponseList();
  private boolean responsesCompleted = false;
  private int offerID;

  public TradeResponseList responses()
  {
    return responses;
  }
  public MutableResourceList getOfferedResources()
  {
    return offeredResources;
  }
  public MutableResourceList getRequestedResources()
  {
    return requestedResources;
  }
  public int getOfferID()
  {
    return offerID;
  }
  public void setOfferID(int id)
  {
    offerID = id;
  }
  /** @return the responsesCompleted */
  public boolean isResponsesCompleted()
  {
    return responsesCompleted;
  }
  /** @param responsesCompleted the responsesCompleted to set */
  public TradeOffer setResponsesCompleted(boolean responsesCompleted)
  {
    this.responsesCompleted = responsesCompleted;
    return this;
  }
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
    if (!player.resources().hasAtLeast(offeredResources))
    {
      invalidMessage = "Player does not have offered resources";
      return false;
    }
    if (game.turn().getTradeOffers().size() >= game
            .gameSettings().getMaximumTradesPerTurn()
            .getMaxTradesPerTurn())
    {
      invalidMessage = "Already used all possible trade offers";
      return false;
    }
    return true;
  }
  @Override public void perform(Game game)
  {
    game.turn().getTradeOffers().addOffer(this);
    for (GamePlayer player : game.players())
      game.actionsQueue().enqueue(
              new QueuedTradeResponse().setPlayer(player), false);
    super.perform(game);
  }
  @Override public String toDoMessage()
  {
    // TODO Auto-generated method stub
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
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return actionWidgetFactory.createTradePlayerWidget();
  }
  @Override public ServerAction createServerAction(GameServerActionFactory factory)
  {
    return factory.createTrade(this);
  }
}