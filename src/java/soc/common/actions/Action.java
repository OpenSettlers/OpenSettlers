package soc.common.actions;

import java.io.Serializable;
import java.util.Date;

import soc.common.views.meta.HasMeta;

public interface Action extends Serializable, HasMeta
{
    /**
     * @return When an action is required to perform for a user, this message is
     *         displayed
     */
    public String getToDoMessage();

    /**
     * @return DateTime when this action is performed
     */
    public Date getDateTimeExecuted();

    /**
     * @return ID of the player initiating this action ID of 0 means the server
     *         initiated this action
     */
    public int getSender();

    /**
     * @param sender
     *            the sender to set
     */
    public Action setSender(int sender);

    /**
     * @return Message set after this action is performed
     */
    public String getMessage();

    public String getName();
}
