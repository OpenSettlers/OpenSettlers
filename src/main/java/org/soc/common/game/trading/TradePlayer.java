package org.soc.common.game.trading;

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
import org.soc.gwt.client.images.R;

public class TradePlayer extends AbstractTurnAction
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
  private TradeOffer originatingOffer;
  private GamePlayer tradeOpponent;
  private TradeResponse tradeResponse;

  public TradeOffer getOriginatingOffer()
  {
    return originatingOffer;
  }
  public TradePlayer setOriginatingOffer(TradeOffer originatingOffer)
  {
    this.originatingOffer = originatingOffer;
    return this;
  }
  public ResourceList getOfferedResources()
  {
    return offeredResources;
  }
  public ResourceList getRequestedResources()
  {
    return requestedResources;
  }
  public TradePlayer setResponse(TradeResponse tradeResponse)
  {
    this.tradeResponse = tradeResponse;
    tradeResponse.setTradeResources(this);
    return this;
  }
  public TradePlayer setTradeOpponent(GamePlayer player)
  {
    tradeOpponent = player;
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
    if (tradeResponse == null)
    {
      invalidMessage = "tradeResponse cannot be null";
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
    return true;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.turnActions.AbstractTurnAction#perform(
   * org.soc.common.game.Game) */
  @Override public void perform(Game game)
  {
    tradeOpponent.resources().moveTo(player.resources(),
            requestedResources);
    player.resources().moveTo(tradeOpponent.resources(),
            offeredResources);
    // TODO: fix message
    super.perform(game);
  }
  @Override public String toDoMessage()
  {
    return I.get().actions().noToDo();
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
}
