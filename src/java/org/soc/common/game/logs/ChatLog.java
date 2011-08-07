package org.soc.common.game.logs;

import java.io.Serializable;

import org.soc.common.actions.gameAction.meta.GameChat;


import com.google.gwt.event.shared.HandlerRegistration;

public interface ChatLog extends Serializable
{
    // A player said something
    public void say(GameChat gamChat);

    // Returns a copy of the chatlog
    public ChatLog copy();

    // Ability for listeners to register themselves
    public HandlerRegistration addSaidEventHandler(SaidEventHandler handler);
}
