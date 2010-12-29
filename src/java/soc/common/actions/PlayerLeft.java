package soc.common.actions;

import soc.common.internationalization.I18n;

/*
 * A player has disconnected and left the server
 */
public class PlayerLeft extends AbstractAction
{
    private static final long serialVersionUID = 1885439014105922967L;

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

}
