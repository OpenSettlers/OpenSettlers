package org.soc.common.game.trading;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.gwt.client.images.R;

public class RejectTradeOffer extends AbstractGameAction implements
        TradeResponse
{
  @Override public Icon icon()
  {
    return new IconImpl(R.icons().tradeRejected16(),
            R.icons().tradeRejected32(), R.icons()
                    .tradeRejected48());
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
  @Override public void perform(Game game)
  {
    game.addTradeResponse(this);
    // TODO: fix message
    super.perform(game);
  }
  @Override public String toDoMessage()
  {
    // TODO Auto-generated method stub
    return "";
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.AbstractGameAction#isExpectedQueueType(
   * org.soc.common.actions.gameAction.GameAction) */
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
  @Override public ActionWidget createActionWidget(
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
