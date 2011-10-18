package org.soc.common.game.trading;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.*;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

public class RejectTradeOffer extends AbstractGameAction implements
        TradeResponse
{
  @Override public Icon icon()
  {
    return new IconImpl(R.icons().tradeRejected16(),
            R.icons().tradeRejected32(), R.icons()
                    .tradeRejected48());
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
  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.AbstractGameAction#perform(org.soc.common.game .Game) */
  @Override public void perform(Game game) {
    game.addTradeResponse(this);
    // TODO: fix message
    super.perform(game);
  }
  @Override public String toDoMessage()
  {
    // TODO Auto-generated method stub
    return "";
  }
  @Override public boolean isExpectedQueueType(GameAction actualAction)
  {
    return actualAction instanceof TradeResponse;
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
    throw new AssertionError("Can't trade with a rejected offer");
  }
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
  @Override public boolean isAccepted()
  {
    return false;
  }
  @Override public boolean isCounterOffer()
  {
    return false;
  }
  @Override public boolean isRejection()
  {
    return true;
  }
}
