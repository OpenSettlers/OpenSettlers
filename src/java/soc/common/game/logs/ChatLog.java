package soc.common.game.logs;

import java.io.Serializable;

import soc.common.actions.gameAction.GameChat;

public interface ChatLog extends Serializable
{
    // A player said something
    public void say(GameChat gamChat);

    // Returns a copy of the chatlog
    public ChatLog copy();

    // Ability for listeners to register themselves
    public void addSaidEventHandler(SaidEventHandler handler);
}
