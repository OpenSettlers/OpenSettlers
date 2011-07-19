package org.soc.gwt.server.database;

import java.util.List;

import org.soc.common.server.entities.Player;
import org.soc.common.server.entities.User;
import org.soc.common.server.entities.UserProvider;


import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/*
 * Provides access to users stored in db4o object database
 */
public class OdbUserProvider implements UserProvider
{
    private ObjectContainer database;

    public OdbUserProvider(ObjectContainer database)
    {
        this.database = database;
    }

    @Override
    public List<User> getAllUsers()
    {
        return database.query(User.class);
    }

    @Override
    public User getUserByID(int ID)
    {
        User user = new Player().setId(ID);
        return (User) database.queryByExample(user).get(0);
    }

    @Override
    public User getUserByName(String name)
    {
        User user = new Player().setName(name);
        return (User) database.queryByExample(user).get(0);
    }

    @Override
    public User registerUser(User user)
    {
        database.store(user);
        return user;
    }

    @Override
    public int getTotalUsers()
    {
        return getAllUsers().size();
    }

    @Override
    public User getUser(String nickname, String password)
    {
        // Create prototype
        User user = new Player().setName(nickname).setPassword(password);

        // Grab the user from database
        ObjectSet result = database.queryByExample(user);

        // Return single user when present, otherwise null
        if (result.size() == 1)
            return (User) result.get(0);
        else
            return null;
    }

}
