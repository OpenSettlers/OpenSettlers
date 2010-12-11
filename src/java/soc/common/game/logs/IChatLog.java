package soc.common.game.logs;

import soc.common.actions.gameAction.GameChat;

public interface IChatLog
{
    // A player said something
    public void say(GameChat gamChat);
    
    // Returns a copy of the chatlog
    public IChatLog copy();
    
    // Ability for listeners to register themselves
    public void addSaidEventHandler(SaidEventHandler handler);
}
