package org.soc.common.game.actions;

import org.soc.common.game.GamePhase;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.main.GameWidget;

/*
 * A generic message from the server. Currently used as error message
 * for debugging purposes. POssible future use is for example servers
 * rebooting.
 */
public class MessageFromServer extends AbstractGameAction
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

  private String serverMessage;

  /** @return the serverMessage */
  public String getServerMessage()
  {
    return serverMessage;
  }
  /** @param serverMessage the serverMessage to set */
  public MessageFromServer setServerMessage(String serverMessage)
  {
    this.serverMessage = serverMessage;
    return this;
  }
  /* Returns true: message from server is always allowed
   * 
   * @see org.soc.common.actions.gameAction.GameAction#isAllowed(org.soc.common.game.gamePhase
   * .turnPhase.TurnPhase) */
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return true;
  }
  /* Returns true: message from server is always allowed
   * 
   * @see org.soc.common.actions.gameAction.GameAction#isAllowed(org.soc.common.game.gamePhase
   * .GamePhase) */
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return true;
  }
  @Override public String toDoMessage()
  {
    return I.get().actions().noToDo();
  }

  public static class ErrorReceivedInGame implements ActionInGame {
    private MessageFromServer messageFromServer;

    public ErrorReceivedInGame(MessageFromServer messageFromServer) {
      super();
      this.messageFromServer = messageFromServer;
    }
    @Override public void finish() {}
    @Override public void start(GameWidget gameWidget) {
      gameWidget.getDebugPanel().addError(messageFromServer);
    }
    @Override public boolean endsManually() {
      return false;
    }
  }
}
