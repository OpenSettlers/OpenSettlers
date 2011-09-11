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
 * A player is saying something in this game using a text chat interface
 */
public class GameChat extends AbstractGameAction
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

  private String chatMessage;

  /** @return the chatMessage */
  public String getChatMessage()
  {
    return chatMessage;
  }
  /** @param chatMessage the chatMessage to set */
  public GameChat setChatMessage(String chatMessage)
  {
    this.chatMessage = chatMessage;
    return this;
  }
  @Override public void perform(Game game)
  {
    // Add the chat message to the chat log of this game
    game.getChatLog().say(this);
    message = ": " + chatMessage;
    super.perform(game);
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return true;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.common.actions.gameAction.GameAction#isAllowed(org.soc.common.game.gamePhase
   * .turnPhase.TurnPhase) */
  @Override public boolean isAllowed(TurnPhase turnPhase)
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