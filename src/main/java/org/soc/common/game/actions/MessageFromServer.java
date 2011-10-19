package org.soc.common.game.actions;

import org.soc.common.game.*;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.*;
import org.soc.common.views.widgetsInterface.main.*;

/*
 * A generic message from the server. Currently used as error message
 * for debugging purposes. POssible future use is for example servers
 * rebooting.
 */
public class MessageFromServer extends AbstractGameAction {
  private String serverMessage;

  public String getServerMessage() {
    return serverMessage;
  }
  public MessageFromServer setServerMessage(String serverMessage) {
    this.serverMessage = serverMessage;
    return this;
  }
  /** Returns true: message from server is always allowed */
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return true;
  }
  /** Returns true: message from server is always allowed */
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return true;
  }
  @Override public String toDoMessage() {
    return I.get().actions().noToDo();
  }

  public static class ErrorReceivedInGame implements GameBehaviour {
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
