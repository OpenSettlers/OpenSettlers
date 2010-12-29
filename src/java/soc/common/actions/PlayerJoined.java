package soc.common.actions;

import soc.common.internationalization.I18n;

/*
 * A player has logged in and joined the server
 */
public class PlayerJoined extends AbstractAction
{
    private static final long serialVersionUID = -379055883044399903L;

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

}
