package org.soc.common.presenters;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.SaidEvent;
import org.soc.common.game.SaidEvent.SaidHandler;
import org.soc.common.game.actions.GameChat;
import org.soc.common.presenters.SayEvent.HasSayHandlers;
import org.soc.common.presenters.SayEvent.SayHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public class GameChatPresenter {
  public interface GameChatView extends HasSayHandlers {
    public void said(String message, GamePlayer player);

    @GenEvent public class Say {
      @Order(0) String message;
    }
  }

  private GameChatView view;
  private GameWidget gameWidget;

  public GameChatPresenter(final GameWidget gameWidget) {
    this.gameWidget = gameWidget;
    gameWidget.game().chatLog().addSaidHandler(new SaidHandler() {
      @Override public void onSaid(SaidEvent event) {
        view.said(event.getSaid().getChatMessage(), event.getSaid().player());
      }
    });
    view.addSayHandler(new SayHandler() {
      @Override public void onSay(SayEvent event) {
        gameWidget.startAction(new GameChat()
                .setChatMessage(event.getMessage())
                .setPlayer(gameWidget.playingPlayer()));
      }
    });
  }
}
