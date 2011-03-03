package soc.common.server;

import net.zschech.gwt.comet.client.CometSerializer;
import net.zschech.gwt.comet.client.SerialTypes;
import soc.common.actions.lobby.LobbyChat;
import soc.common.server.entities.Player;

@SerialTypes(
    { Player.class, LobbyChat.class, LobbyGameImpl.class,
            LoginResponseImpl.class })
public abstract class OpenSettlersSerializer extends CometSerializer
{

}
