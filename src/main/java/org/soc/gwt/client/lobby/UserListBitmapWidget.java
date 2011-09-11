package org.soc.gwt.client.lobby;

import java.util.List;

import org.soc.common.game.UserListChangedEvent;
import org.soc.common.game.UserListChangedEvent.UserListChangedHandler;
import org.soc.common.server.entities.User;
import org.soc.common.views.widgetsInterface.lobby.LobbyWidget;
import org.soc.common.views.widgetsInterface.lobby.UserListWidget;

import com.google.gwt.user.client.ui.ListBox;

public class UserListBitmapWidget extends ListBox implements UserListWidget,
        UserListChangedHandler
{
  private LobbyWidget lobbyWidget;

  public UserListBitmapWidget(LobbyWidget lobbyWidget)
  {
    this.lobbyWidget = lobbyWidget;
    // lobbyWidget.getLobby().getUsers().a
    this.setSize("100%", "100%");
    this.setVisibleItemCount(40);
    lobbyWidget.getLobby().getUsers().addUserListChangedHandler(this);
  }
  @Override public void addUser(User userToAdd)
  {
    insertItem(userToAdd.name(), 0);
  }
  @Override public void addUsers(List<User> users)
  {
    for (User user : users)
      addUser(user);
  }
  /* Called when there's a change in the UserList model this class subscribes to
   * 
   * @see org.soc.common.game.UserListChangedEventHandler#onUserListChanged(org.soc.common
   * .game.UserListChangedEvent) */
  @Override public void onUserListChanged(UserListChangedEvent event) {
    // List of users added
    if (event.getAddedUsers() != null)
      for (User newUser : event.getAddedUsers())
        addItem(newUser.name());
    // Single user added
    if (event.getAddedUser() != null)
      addItem(event.getAddedUser().name());
    // User removed, search and destroy
    if (event.getRemovedUser() != null)
      for (int i = 0; i < this.getItemCount(); i++)
        if (getItemText(i).equals(event.getRemovedUser().name()))
        {
          removeItem(i);
          break;
        }
  }
}
