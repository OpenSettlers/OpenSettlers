package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.AbstractGameAction;

public interface Server
{
    public void sendAction(AbstractGameAction gameAction); 
}
