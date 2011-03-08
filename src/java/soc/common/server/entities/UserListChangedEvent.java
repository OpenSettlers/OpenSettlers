package soc.common.server.entities;


import com.google.gwt.event.shared.GwtEvent;

public class UserListChangedEvent extends GwtEvent<UserListChangedEventHandler>
{
    public static Type<UserListChangedEventHandler> TYPE = new Type<UserListChangedEventHandler>();
    public User addedUser;
    public User removedUser;
    public UserList addedUsers;

    public UserListChangedEvent(User addedUser, User removedUser,
            UserList addedUsers)
    {
        super();
        this.addedUser = addedUser;
        this.removedUser = removedUser;
        this.addedUsers = addedUsers;
    }

    /**
     * @return the addedUser
     */
    public User getAddedUser()
    {
        return addedUser;
    }

    /**
     * @return the removedUser
     */
    public User getRemovedUser()
    {
        return removedUser;
    }

    /**
     * @return the addedUsers
     */
    public UserList getAddedUsers()
    {
        return addedUsers;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<UserListChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(UserListChangedEventHandler handler)
    {
        handler.onUserListChanged(this);
    }

}
