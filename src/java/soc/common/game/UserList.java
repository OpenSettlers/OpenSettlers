package soc.common.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.server.entities.User;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class UserList implements Iterable<User>
{
    private List<User> users = new ArrayList<User>();
    private EventBus eventBus = new SimpleEventBus();

    @Override
    public Iterator<User> iterator()
    {
        return users.iterator();
    }

    public void addUser(User user)
    {
        users.add(user);
    }

    public void addUsers(List<User> users)
    {
        for (User user : users)
            addUser(user);
    }

}
