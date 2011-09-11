package org.soc.common.game.actions;

import org.soc.common.game.DevelopmentCard;
import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.internationalization.I;
import org.soc.common.server.actions.GameServerActionFactory;
import org.soc.common.server.actions.ServerAction;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.gwt.client.images.R;

public class EndTurn extends AbstractTurnAction
{
  @Override public Icon icon()
  {
    return new IconImpl(null, null, R.icons()
            .endTurn32());
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
  @Override public void perform(Game game)
  {
    for (DevelopmentCard devCard : player.developmentCards())
      devCard.setPlayable(true);
    game.advanceTurn();
    game.advanceTurnPhase();
    super.perform(game);
  }
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return turnPhase.isBuilding();
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return gamePhase.isPlayTurns();
  }
  @Override public String toDoMessage()
  {
    return I.get().actions().noToDo();
  }
  @Override public ActionWidget createActionWidget(
          ActionWidgetFactory actionWidgetFactory)
  {
    return actionWidgetFactory.createEndTurnWidget();
  }
  @Override public ActionDetailWidget createDetailWidget(
          ActionDetailWidgetFactory factory)
  {
    return factory.getEndTurnDetailWidget(this);
  }
  @Override public ServerAction createServerAction(GameServerActionFactory factory)
  {
    return factory.createEndTurnServerAction(this);
  }
}
