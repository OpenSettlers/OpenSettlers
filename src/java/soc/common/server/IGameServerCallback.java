package soc.common.server;

import soc.common.actions.gameAction.AbstractGameAction;

public interface IGameServerCallback
{
    public void receive(AbstractGameAction action);
}
