package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.SaidEvent.HasSaidHandlers;
import org.soc.common.game.SaidEvent.SaidHandler;
import org.soc.common.game.actions.GameChat;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public interface ChatLog extends Serializable, HasSaidHandlers {
  // A player said something
  public void say(GameChat gamChat);
  // Returns a copy of the chatlog
  public ChatLog copy();

  @GenEvent public class Said {
    @Order(0) GameChat said;
  }

  public class ChatLogImpl implements ChatLog {
    private static final long serialVersionUID = 6288111035333874654L;
    private List<GameChat> chats = new ArrayList<GameChat>();
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    @Override public ChatLog copy() {
      throw new RuntimeException("ChatLog.copy not yet implemented");
    }
    @Override public void say(GameChat gameChat) {
      chats.add(gameChat);
      eventBus.fireEvent(new SaidEvent(gameChat));
    }
    @Override public HandlerRegistration addSaidHandler(SaidHandler handler) {
      return eventBus.addHandler(SaidEvent.getType(), handler);
    }
    @Override public void fireEvent(GwtEvent<?> event) {
      eventBus.fireEvent(event);
    }
  }
}
