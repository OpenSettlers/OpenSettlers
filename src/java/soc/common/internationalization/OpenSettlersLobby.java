package soc.common.internationalization;

import com.google.gwt.i18n.client.Messages;

public interface OpenSettlersLobby extends Messages
{
    @DefaultMessage("{0} left")
    String left(String playerName);

    @DefaultMessage("{0} joined")
    String joined(String playerName);
}
