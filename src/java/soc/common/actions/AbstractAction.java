package soc.common.actions;

import java.util.Date;

import soc.common.game.player.GamePlayer;

public abstract class AbstractAction implements Action
{
    private static final long serialVersionUID = -6754147108114150267L;
    private String message;
    private Date dateTimeExecuted;

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
        // TODO implement in all actions using i18n
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.actions.Action#getPlayer()
     */
    @Override
    public GamePlayer getPlayer()
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
     * @see soc.common.actions.Action#setPlayer(soc.common.game.Player)
     */
    @Override
    public Action setPlayer(GamePlayer player)
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
