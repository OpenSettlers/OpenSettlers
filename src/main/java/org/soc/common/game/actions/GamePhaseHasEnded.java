package org.soc.common.game.actions;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;

/*
 * Announces a gamephase which has been ended
 */
public class GamePhaseHasEnded extends AbstractGameAction
{
  @Override public Icon icon()
  {
    return new IconImpl(null, null, null, null);
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

  private GamePhase endedGamePhase;
  private GamePhase newPhase;

  /** @return the newPhase */
  public GamePhase getNewPhase()
  {
    return newPhase;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.GameAction#perform(org.soc.common.game.Game) */
  @Override public void perform(Game game)
  {
    endedGamePhase = game.phase();
    game.advanceGamePhase();
    newPhase = game.phase();
    // TODO: fix message
    message = endedGamePhase.name() + " has ended, entering"
            + newPhase.name();
    super.perform(game);
  }
  /** @return the endedGamePhase */
  public GamePhase getEndedGamePhase()
  {
    return endedGamePhase;
  }
  /** @param endedGamePhase the endedGamePhase to set */
  public GamePhaseHasEnded setEndedGamePhase(GamePhase endedGamePhase)
  {
    this.endedGamePhase = endedGamePhase;
    return this;
  }
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return true;
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return true;
  }
  @Override public String toDoMessage()
  {
    return I.get().actions().noToDo();
  }
  @Override public ActionWidget createActionWidget(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
}