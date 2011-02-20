package soc.common.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.server.entities.User;

public class UserList implements Iterable<User>
{
    private List<User> users = new ArrayList<User>();

    @Override
    public Iterator<User> iterator()
    {
        return users.iterator();
    }

}
