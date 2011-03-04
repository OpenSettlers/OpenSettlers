package soc.gwtClient.lobby;

import java.util.List;

import soc.common.game.UserListChangedEvent;
import soc.common.game.UserListChangedEventHandler;
import soc.common.server.entities.User;
import soc.common.views.widgetsInterface.lobby.LobbyWidget;
import soc.common.views.widgetsInterface.lobby.UserListWidget;

import com.google.gwt.user.client.ui.ListBox;

public class UserListBitmapWidget extends ListBox implements UserListWidget,
        UserListChangedEventHandler
{
    private LobbyWidget lobbyWidget;

    public UserListBitmapWidget(LobbyWidget lobbyWidget)
    {
        this.lobbyWidget = lobbyWidget;
        // lobbyWidget.getLobby().getUsers().a
        this.setSize("100%", "100%");
        this.setVisibleItemCount(40);
        lobbyWidget.getLobby().getUsers().addUserListChangedEventHandler(this);
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

    /*
     * Called when there's a change in the UserList model this class subscribes
     * to
     * 
     * @see
     * soc.common.game.UserListChangedEventHandler#onUserListChanged(soc.common
     * .game.UserListChangedEvent)
     */
    @Override
    public void onUserListChanged(UserListChangedEvent event)
    {
        // List of users added
        if (event.addedUsers != null)
            for (User newUser : event.addedUsers)
                addItem(newUser.getName());

        // Single user added
        if (event.getAddedUser() != null)
            addItem(event.getAddedUser().getName());

        // User removed, search and destroy
        if (event.getRemovedUser() != null)
            for (int i = 0; i < this.getItemCount(); i++)
                if (getItemText(i).equals(event.getRemovedUser().getName()))
                {
                    removeItem(i);
                    break;
                }
    }
}
