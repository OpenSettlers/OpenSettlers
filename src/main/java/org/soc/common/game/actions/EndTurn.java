package org.soc.common.game.actions;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.TurnChangedEvent.TurnChangedHandler;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.actions.WantsEndTurnEvent.HasWantsEndTurnHandlers;
import org.soc.common.game.actions.WantsEndTurnEvent.WantsEndTurnHandler;
import org.soc.common.internationalization.*;
import org.soc.common.server.actions.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.actions.*;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.actions.*;
import org.soc.gwt.client.game.widgetsAbstract.actions.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;
import com.gwtplatform.dispatch.annotation.*;

public class EndTurn extends AbstractTurnAction
{
  @Override public Icon icon()
  {
    return new IconImpl(null, null, R.icons()
            .endTurn32());
  }
  @Override public Name name()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Description description() {
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
  @Override public ActionPresenter createPresenter(
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
    return factory.createEndTurn(this);
  }

  public interface EndTurnView extends HasWantsEndTurnHandlers {
    @GenEvent public class WantsEndTurn {}

    public void enable();
    public void disable();
  }

  public static class EndTurnBitmapWidget extends AbstractActionPresenter implements
          GamePhaseChangedHandler, TurnChangedHandler,
          TurnPhaseChangedHandler
  {
    private EndTurn endTurn = new EndTurn();
    private EndTurnView view = new EndTurnGwt();

    public EndTurnBitmapWidget(final GameWidget gameWidget,
            final GamePlayer player)
    {
      super(gameWidget, player);
      endTurn.setPlayer(player);
      gameWidget.game().addTurnChangedHandler(this);
      gameWidget.game().addGamePhaseChangedHandler(this);
      gameWidget.game().addTurnPhaseChangedHandler(this);
      view.addWantsEndTurnHandler(new WantsEndTurnHandler() {
        @Override public void onWantsEndTurn(WantsEndTurnEvent event) {
          gameWidget.startAction((AbstractTurnAction) new EndTurn()
                  .setPlayer(player));
        }
      });
    }
    @Override public Widget asWidget() {
      return (Widget) view;
    }
    @Override protected void updateEnabled() {
      checkEnabled();
    }
    private void checkEnabled() {
      if (enabled && player.isOnTurn()) {
        if (gameWidget.game().isAllowed(endTurn)) {
          view.enable();
          return;
        }
      }
      view.disable();
    }
    @Override public void onGamePhaseChanged(GamePhaseChangedEvent event) {
      checkEnabled();
    }
    @Override public void onTurnChanged(TurnChangedEvent event) {
      checkEnabled();
    }
    @Override public void onTurnPhaseChanged(TurnPhaseChangedEvent event) {
      checkEnabled();
    }
  }
}
