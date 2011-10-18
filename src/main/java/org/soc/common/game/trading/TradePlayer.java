package org.soc.common.game.trading;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

public class TradePlayer extends AbstractTurnAction
{
  @Override public Icon icon()
  {
    return new IconImpl(R.icons().trade16(), R
            .icons().trade32(), R.icons().trade48());
  }
  @Override public Name name()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Description description()
  {
    // TODO Auto-generated method stub
    return null;
  }

  private transient MutableResourceList offeredResources = new MutableResourceListImpl();
  private transient MutableResourceList requestedResources = new MutableResourceListImpl();
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
  public MutableResourceList getOfferedResources()
  {
    return offeredResources;
  }
  public MutableResourceList getRequestedResources()
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
  @Override public void perform(Game game) {
    player.resources().moveListFrom(tradeOpponent.resources(), requestedResources);
    tradeOpponent.resources().moveListFrom(player.resources(), offeredResources);
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
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return actionWidgetFactory.createTradePlayerWidget();
  }
}
