package org.soc.common.game.actions;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.ResourceList;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.ActionInGame.DefaultOpponentReceivedBehaviour;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.game.trading.TradeOffer;
import org.soc.common.game.trading.TradePlayer;
import org.soc.common.game.trading.TradeResponse;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.images.R;

public class CounterTradeOffer extends AbstractGameAction implements
        TradeResponse
{
  @Override public Icon icon()
  {
    return new IconImpl(R.icons().tradeCountered16(),
            R.icons().tradeCountered32(), R.icons()
                    .tradeCountered48());
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
  public ResourceList getOfferedResources()
  {
    return offeredResources;
  }
  /* Requested resources as seen from the offering players' perspective */
  public ResourceList getRequestedResources()
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
    if (originatingOffer.getResponses().containsResponse(player))
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
  @Override public ActionWidget createActionWidget(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
  @Override public ActionInGame opponentReceived(
          GameWidget gameWidget)
  {
    return new DefaultOpponentReceivedBehaviour(gameWidget, this);
  }
  @Override public ActionInGame received(GameWidget gameWidget) {
    return new DefaultOpponentReceivedBehaviour(gameWidget, this);
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
