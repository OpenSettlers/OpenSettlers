package soc.gwtClient.lobby;

import java.util.List;

import soc.common.server.entities.User;
import soc.common.views.widgetsInterface.lobby.LobbyWidget;
import soc.common.views.widgetsInterface.lobby.UserListWidget;

import com.google.gwt.user.client.ui.ListBox;

public class UserListBitmapWidget extends ListBox implements UserListWidget
{
    private LobbyWidget lobbyWidget;

    public UserListBitmapWidget(LobbyWidget lobbyWidget)
    {
        this.lobbyWidget = lobbyWidget;
        //lobbyWidget.getLobby().getUsers().a
        this.setSize("100%", "100%");
        this.setVisibleItemCount(40);
    }

    @Override
    public void addUser(User userToAdd)
    {
        insertItem(userToAdd.getName(), 0);
    }

    @Override
    public void addUsers(List<User> users)
    {
        for (User user : users)
            addUser(user);
    }
}
