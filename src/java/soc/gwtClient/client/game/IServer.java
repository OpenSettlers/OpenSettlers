package soc.gwtClient.client.game;

import soc.common.actions.gameAction.GameAction;

public interface IServer
{
    public void sendAction(GameAction gameAction); 
}
