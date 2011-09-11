package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.game.GamePhaseChangedEvent;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.TurnChangedEvent;
import org.soc.common.game.TurnChangedEvent.TurnChangedHandler;
import org.soc.common.game.TurnPhaseChangedEvent;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.game.actions.EndTurn;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actions.AbstractActionWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class EndTurnBitmapWidget extends AbstractActionWidget implements
        GamePhaseChangedHandler, TurnChangedHandler,
        TurnPhaseChangedHandler
{
  public PushButton btnEndTurn = new PushButton(new Image(R.icons()
          .endTurn32()));
  private EndTurn endTurn = new EndTurn();

  public EndTurnBitmapWidget(final GameWidget gameWidget,
          final GamePlayer player)
  {
    super(gameWidget, player);
    endTurn.setPlayer(player);
    gameWidget.game().addTurnChangedHandler(this);
    gameWidget.game().addGamePhaseChangedHandler(this);
    gameWidget.game().addTurnPhaseChangedHandler(this);
    btnEndTurn.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        gameWidget.startAction((AbstractTurnAction) new EndTurn()
                .setPlayer(player));
      }
    });
  }
  @Override public Widget asWidget()
  {
    return btnEndTurn;
  }
  @Override protected void updateEnabled()
  {
    checkEnabled();
  }
  private void enableUI()
  {
    btnEndTurn.setEnabled(true);
  }
  private void disableUI()
  {
    btnEndTurn.setEnabled(false);
  }
  private void checkEnabled()
  {
    if (enabled && player.isOnTurn())
    {
      if (gameWidget.game().isAllowed(endTurn))
      {
        enableUI();
        return;
      }
    }
    disableUI();
  }
  @Override public void onGamePhaseChanged(GamePhaseChangedEvent event)
  {
    checkEnabled();
  }
  @Override public void onTurnChanged(TurnChangedEvent event)
  {
    checkEnabled();
  }
  @Override public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
  {
    checkEnabled();
  }
}
