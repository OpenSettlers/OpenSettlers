package org.soc.common.game.trading;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourceList;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.server.actions.GameServerActionFactory;
import org.soc.common.server.actions.ServerAction;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.gwt.client.images.R;

public class TradeOffer extends AbstractGameAction
{
  @Override public Icon icon()
  {
    return new IconImpl(R.icons().trade16(), R
            .icons().trade32(), R.icons().trade48());
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

  private ResourceList offeredResources = new ResourceList();
  private ResourceList requestedResources = new ResourceList();
  private TradeResponseList responses = new TradeResponseList();
  private boolean responsesCompleted = false;
  private int offerID;

  public TradeResponseList getResponses()
  {
    return responses;
  }
  public ResourceList getOfferedResources()
  {
    return offeredResources;
  }
  public ResourceList getRequestedResources()
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
  @Override public ActionWidget createActionWidget(
          ActionWidgetFactory actionWidgetFactory)
  {
    return actionWidgetFactory.createTradePlayerWidget();
  }
  @Override public ServerAction createServerAction(GameServerActionFactory factory)
  {
    return factory.createTradeOfferAction(this);
  }
}