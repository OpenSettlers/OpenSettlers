package soc.common.actions;

import soc.common.internationalization.I18n;

/*
 * Tells everyone a game has been finished
 */
public class GameOver extends AbstractAction
{
    private static final long serialVersionUID = 1790885266071071233L;

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

}
