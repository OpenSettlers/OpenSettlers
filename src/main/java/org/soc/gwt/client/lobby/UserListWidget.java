package org.soc.gwt.client.lobby;

import org.soc.common.game.UserList;

import com.google.gwt.user.client.ui.IsWidget;

public interface UserListWidget extends IsWidget
{
  public void setUsers(UserList users);
}
