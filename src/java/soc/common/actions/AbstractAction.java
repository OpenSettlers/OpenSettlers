package soc.common.actions;

import java.util.Date;

import soc.common.utils.ClassUtils;

public abstract class AbstractAction implements Action
{
    protected static final long serialVersionUID = -6754147108114150267L;
    protected String message = "No message implemented yet for " + getName();
    protected Date dateTimeExecuted;
    protected int sender;

    /**
     * @return the dateTimeExecuted
     */
    public Date getDateTimeExecuted()
    {
        return dateTimeExecuted;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.Action#getMessage()
     */
    @Override
    public String getMessage()
    {
        return message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.Action#getName()
     */
    @Override
    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().toString());
    }

    /**
     * @return ID of the player initiating this action ID of 0 means the server
     *         initiated this action
     */
    public int getSender()
    {
        return sender;
    }

    /**
     * @param sender
     *            the sender to set
     */
    public Action setSender(int sender)
    {
        this.sender = sender;

        return this;
    }
}
