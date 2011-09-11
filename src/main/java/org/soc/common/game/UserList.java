package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.game.UserListChangedEvent.HasUserListChangedHandlers;
import org.soc.common.game.UserListChangedEvent.UserListChangedHandler;
import org.soc.common.server.entities.User;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public class UserList implements Iterable<User>, Serializable, HasUserListChangedHandlers
{
  private static final long serialVersionUID = 6637524879571899631L;
  private List<User> users = new ArrayList<User>();
  private transient EventBus eventBus = new SimpleEventBus();

  @Override public Iterator<User> iterator()
  {
    return users.iterator();
  }
  public void addUser(User user)
  {
    users.add(user);
    eventBus.fireEvent(new UserListChangedEvent(user, null, null));
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
      if (user.id() == id)
        return user;
    return null;
  }
  public boolean contains(User user) {
    return users.contains(user);
  }
  public void removeUser(User user) {
    users.remove(user);
    eventBus.fireEvent(new UserListChangedEvent(null, user, null));
  }
  public int size() {
    return users.size();
  }

  @GenEvent public class UserListChanged {
    @Order(0) User addedUser;
    @Order(1) User removedUser;
    @Order(2) UserList addedUsers;
  }

  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
  @Override public HandlerRegistration addUserListChangedHandler(UserListChangedHandler handler) {
    return eventBus.addHandler(UserListChangedEvent.getType(), handler);
  }
}
