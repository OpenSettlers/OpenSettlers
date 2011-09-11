package org.soc.common.game.trading;

import org.soc.common.game.GamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.ActionInGame;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.images.R;

public class QueuedTradeResponse extends AbstractGameAction implements
        TradeResponse
{
  @Override public Icon icon() {
    return new IconImpl(R.icons().tradeDisabled16(),
            R.icons().tradeDisabled32(), R.icons()
                    .tradeDisabled48());
  }
  @Override public String name() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getLocalizedName() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getDescription() {
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
  @Override public ActionWidget createActionWidget(ActionWidgetFactory actionWidgetFactory) {
    return null;
  }
  @Override public ActionInGame received(GameWidget gameWidget) {
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
