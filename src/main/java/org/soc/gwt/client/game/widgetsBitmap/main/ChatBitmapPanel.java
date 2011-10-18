package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.SaidEvent;
import org.soc.common.game.SaidEvent.SaidHandler;
import org.soc.common.game.actions.GameChat;
import org.soc.common.views.widgetsInterface.main.ChatWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ChatBitmapPanel implements ChatWidget, SaidHandler
{
  TextArea chats = new TextArea();
  TextBox saySomething = new TextBox();
  LayoutPanel rootPanel = new LayoutPanel();
  GameWidget gameWidget;

  public ChatBitmapPanel()
  {
    rootPanel.add(chats);
    rootPanel.add(saySomething);
    // rootPanel.setWidgetTopBottom(saySomething, 0, Unit.PX, 0, Unit.PX);
    rootPanel.setWidgetVerticalPosition(saySomething, Alignment.END);
    rootPanel.setWidgetVerticalPosition(chats, Alignment.STRETCH);
    rootPanel.setWidgetRightWidth(chats, 0, Unit.PX, 100, Unit.PCT);
    rootPanel.setWidgetHorizontalPosition(chats, Alignment.STRETCH);
    rootPanel.setWidgetHorizontalPosition(saySomething, Alignment.STRETCH);
    saySomething.addKeyUpHandler(new KeyUpHandler()
    {
      @Override public void onKeyUp(KeyUpEvent event)
      {
        if (event.getNativeKeyCode() == 13)
        {
          sendChat();
        }
      }
    });
  }
  public ChatBitmapPanel(GameWidget gameWidget)
  {
    this();
    this.gameWidget = gameWidget;
    gameWidget.game().chatLog().addSaidHandler(this);
  }
  private void sendChat()
  {
    GameChat chat = new GameChat();
    chat.setPlayer(gameWidget.playingPlayer());
    chat.setChatMessage(saySomething.getText());
    gameWidget.startAction(chat);
    // clear chat textbox
    saySomething.setText("");
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override public void onSaid(SaidEvent event)
  {
    String text = event.getSaid().player().user().name() + ": "
            + event.getSaid().getChatMessage();
    chats.setText(chats.getText() + "\r\n" + text);
  }
}
