package soc.common.server.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class UserList implements Iterable<User>, Serializable
{
    private static final long serialVersionUID = 6637524879571899631L;
    private List<User> users = new ArrayList<User>();
    private transient EventBus eventBus = new SimpleEventBus();

    @Override
    public Iterator<User> iterator()
    {
        return users.iterator();
    }

    public void addUser(User user)
    {
        users.add(user);
        eventBus.fireEvent(new UserListChangedEvent(user, null, null));
    }

    public boolean contains(User user)
    {
        return users.contains(user);
    }

    public void addUsers(UserList usersToAdd)
    {
        for (User user : usersToAdd)
            users.add(user);

        eventBus.fireEvent(new UserListChangedEvent(null, null, usersToAdd));
    }

    public User getById(int id)
    {
        for (User user : users)
            if (user.getId() == id)
                return user;

        return null;
    }

    public void removeUser(User user)
    {
        if (users.contains(user))
            users.remove(user);

        eventBus.fireEvent(new UserListChangedEvent(null, user, null));
    }

    public HandlerRegistration addUserListChangedEventHandler(
                    UserListChangedEventHandler handler)
    {
        return eventBus.addHandler(UserListChangedEvent.TYPE, handler);
    }
}
