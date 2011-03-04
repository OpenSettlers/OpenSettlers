package soc.common.server;

import net.zschech.gwt.comet.client.CometSerializer;
import net.zschech.gwt.comet.client.SerialTypes;
import soc.common.actions.lobby.LobbyChat;
import soc.common.actions.lobby.PlayerJoined;
import soc.common.lobby.GameInfoImpl;
import soc.common.server.entities.Player;

@SerialTypes(
    { Player.class, LobbyChat.class, GameInfoImpl.class,
            LoginResponseImpl.class, PlayerJoined.class })
public abstract class OpenSettlersSerializer extends CometSerializer
{

}
