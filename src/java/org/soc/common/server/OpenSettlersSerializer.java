package org.soc.common.server;

import org.soc.common.actions.lobby.LobbyChat;
import org.soc.common.actions.lobby.UserJoined;
import org.soc.common.actions.lobby.game.CreateGame;
import org.soc.common.game.statuses.Playing;
import org.soc.common.game.statuses.WaitingForPlayers;
import org.soc.common.lobby.GameInfoImpl;
import org.soc.common.server.entities.Player;

import net.zschech.gwt.comet.client.CometSerializer;
import net.zschech.gwt.comet.client.SerialTypes;

@SerialTypes(
{ Player.class, LobbyChat.class, GameInfoImpl.class, LoginResponseImpl.class,
                UserJoined.class, CreateGame.class, GameInfoImpl.class,
                WaitingForPlayers.class, Playing.class })
public abstract class OpenSettlersSerializer extends CometSerializer
{

}
