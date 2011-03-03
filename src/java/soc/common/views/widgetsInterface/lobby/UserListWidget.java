package soc.common.views.widgetsInterface.lobby;

import java.util.List;

import soc.common.server.entities.User;

import com.google.gwt.user.client.ui.IsWidget;

public interface UserListWidget extends IsWidget
{
    public void addUser(User userToAdd);

    public void addUsers(List<User> users);
}
