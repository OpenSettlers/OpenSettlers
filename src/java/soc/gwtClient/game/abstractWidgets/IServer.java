package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.AbstractGameAction;

public interface IServer
{
    public void sendAction(AbstractGameAction gameAction); 
}
