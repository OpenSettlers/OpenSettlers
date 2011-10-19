package org.soc.common.game.actions;

import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.*;

/*
 * A player is saying something in this game using a text chat interface
 */
public class GameChat extends AbstractGameAction
{
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
    game.chatLog().say(this);
    message = ": " + chatMessage;
    super.perform(game);
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return true;
  }
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return true;
  }
  @Override public String toDoMessage()
  {
    return I.get().actions().noToDo();
  }
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    return null;
  }
}