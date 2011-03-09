package soc.common.server;

import net.zschech.gwt.comet.client.CometSerializer;
import net.zschech.gwt.comet.client.SerialTypes;
import soc.common.actions.lobby.LobbyChat;
import soc.common.actions.lobby.UserJoined;
import soc.common.actions.lobby.game.CreateGame;
import soc.common.game.statuses.Playing;
import soc.common.game.statuses.WaitingForPlayers;
import soc.common.lobby.GameInfoImpl;
import soc.common.server.entities.Player;

@SerialTypes(
{ Player.class, LobbyChat.class, GameInfoImpl.class, LoginResponseImpl.class,
                UserJoined.class, CreateGame.class, GameInfoImpl.class,
                WaitingForPlayers.class, Playing.class })
public abstract class OpenSettlersSerializer extends CometSerializer
{

}
