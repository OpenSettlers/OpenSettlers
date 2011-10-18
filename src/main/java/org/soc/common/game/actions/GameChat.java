package org.soc.common.game.actions;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;

/*
 * A player is saying something in this game using a text chat interface
 */
public class GameChat extends AbstractGameAction
{
  @Override public Icon icon()
  {
    return new IconImpl(null, null, null, null);
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