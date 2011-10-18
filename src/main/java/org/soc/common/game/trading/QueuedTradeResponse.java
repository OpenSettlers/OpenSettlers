package org.soc.common.game.trading;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.game.actions.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.images.*;

public class QueuedTradeResponse extends AbstractGameAction implements
        TradeResponse
{
  @Override public Icon icon() {
    return new IconImpl(R.icons().tradeDisabled16(),
            R.icons().tradeDisabled32(), R.icons()
                    .tradeDisabled48());
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
  @Override public TradeOffer getOriginatingOffer() {
    return null;
  }
  @Override public TradeResponse setOriginatingOffer(TradeOffer tradeOffer) {
    return null;
  }
  @Override public void setTradeResources(TradePlayer tradePlayer) {}
  @Override public String toDoMessage() {
    return "Respond on the trade";
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return false;
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return false;
  }
  @Override public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory) {
    return null;
  }
  @Override public GameBehaviour received(GameWidget gameWidget) {
    return null;
  }
  @Override public boolean isAccepted() {
    return false;
  }
  @Override public boolean isCounterOffer() {
    return false;
  }
  @Override public boolean isRejection() {
    return false;
  }
}
