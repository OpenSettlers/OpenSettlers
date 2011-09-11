package org.soc.gwt.client.lobby;

import org.soc.common.lobby.actions.LobbyChat;
import org.soc.common.server.ELobbyActionEvent;
import org.soc.common.server.ELobbyActionEvent.ELobbyActionHandler;
import org.soc.common.views.widgetsInterface.lobby.ChatBoxWidget;
import org.soc.common.views.widgetsInterface.lobby.LobbyWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class ChatBoxBitmapWidget extends DockLayoutPanel implements
        ChatBoxWidget, ELobbyActionHandler
{
  private LobbyWidget lobbyWidget;
  private ListBox chats = new ListBox();
  private TextBox textboxSay = new TextBox();

  public ChatBoxBitmapWidget(LobbyWidget lobbyWidget)
  {
    super(Unit.EM);
    this.lobbyWidget = lobbyWidget;
    this.addSouth(textboxSay, 3);
    this.add(chats);
    chats.setVisibleItemCount(40);
    textboxSay.setSize("100%", "2em");
    chats.setSize("100%", "100%");
    this.setSize("100%", "100%");
    textboxSay.addKeyUpHandler(new KeyUpHandler()
    {
      @Override public void onKeyUp(KeyUpEvent event)
      {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
          say();
      }
    });
    lobbyWidget.getLobby().getLog().addLobbyActionEventHandler(this);
  }
  @Override public void addMessage(LobbyChat chat)
  {
    chats.addItem(chat.getChatMessage());
  }
  private void say()
  {
    if (textboxSay.getText() != null && !textboxSay.getText().isEmpty())
    {
      lobbyWidget.sendLobbyAction(new LobbyChat().setChatMessage(
              textboxSay.getText())
              .setUser(lobbyWidget.getUser()));
    }
  }
  @Override public void onELobbyAction(ELobbyActionEvent event)
  {
    if (event.getLobbyAction() instanceof LobbyChat)
    {
      LobbyChat chat = (LobbyChat) event.getLobbyAction();
      String player = chat.getUser() == null ? "" : chat.getUser()
              .name();
      chats.addItem(player + ": " + chat.getChatMessage());
    }
  }
}
