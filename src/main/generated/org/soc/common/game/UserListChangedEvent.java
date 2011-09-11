package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class UserListChangedEvent extends GwtEvent<UserListChangedEvent.UserListChangedHandler> { 

  public interface HasUserListChangedHandlers extends HasHandlers {
    HandlerRegistration addUserListChangedHandler(UserListChangedHandler handler);
  }

  public interface UserListChangedHandler extends EventHandler {
    public void onUserListChanged(UserListChangedEvent event);
  }

  private static final Type<UserListChangedHandler> TYPE = new Type<UserListChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.server.entities.User addedUser, org.soc.common.server.entities.User removedUser, org.soc.common.game.UserList addedUsers) {
    source.fireEvent(new UserListChangedEvent(addedUser, removedUser, addedUsers));
  }

  public static Type<UserListChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.server.entities.User addedUser;
  org.soc.common.server.entities.User removedUser;
  org.soc.common.game.UserList addedUsers;

  public UserListChangedEvent(org.soc.common.server.entities.User addedUser, org.soc.common.server.entities.User removedUser, org.soc.common.game.UserList addedUsers) {
    this.addedUser = addedUser;
    this.removedUser = removedUser;
    this.addedUsers = addedUsers;
  }

  protected UserListChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<UserListChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.server.entities.User getAddedUser() {
    return addedUser;
  }

  public org.soc.common.server.entities.User getRemovedUser() {
    return removedUser;
  }

  public org.soc.common.game.UserList getAddedUsers() {
    return addedUsers;
  }

  @Override
  protected void dispatch(UserListChangedHandler handler) {
    handler.onUserListChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    UserListChangedEvent other = (UserListChangedEvent) obj;
    if (addedUser == null) {
      if (other.addedUser != null)
        return false;
    } else if (!addedUser.equals(other.addedUser))
      return false;
    if (removedUser == null) {
      if (other.removedUser != null)
        return false;
    } else if (!removedUser.equals(other.removedUser))
      return false;
    if (addedUsers == null) {
      if (other.addedUsers != null)
        return false;
    } else if (!addedUsers.equals(other.addedUsers))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (addedUser == null ? 1 : addedUser.hashCode());
    hashCode = (hashCode * 37) + (removedUser == null ? 1 : removedUser.hashCode());
    hashCode = (hashCode * 37) + (addedUsers == null ? 1 : addedUsers.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "UserListChangedEvent["
                 + addedUser
                 + ","
                 + removedUser
                 + ","
                 + addedUsers
    + "]";
  }
}
