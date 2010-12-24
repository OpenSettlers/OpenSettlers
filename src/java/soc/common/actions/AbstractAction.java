package soc.common.actions;

import java.util.Date;

import soc.common.game.Player;

public abstract class AbstractAction implements Action
{
    private static final long serialVersionUID = -6754147108114150267L;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.Action#getDateTimeExecuted()
     */
    @Override
    public Date getDateTimeExecuted()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.Action#getMessage()
     */
    @Override
    public String getMessage()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.Action#getName()
     */
    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.Action#getPlayer()
     */
    @Override
    public Player getPlayer()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.Action#getSender()
     */
    @Override
    public int getSender()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.Action#getToDoMessage()
     */
    @Override
    public String getToDoMessage()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.Action#setPlayer(soc.common.game.Player)
     */
    @Override
    public Action setPlayer(Player player)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.Action#setSender(int)
     */
    @Override
    public Action setSender(int sender)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
