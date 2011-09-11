package org.soc.common.server;

import net.zschech.gwt.comet.client.CometSerializer;
import net.zschech.gwt.comet.client.SerialTypes;

import org.soc.common.game.GameStatus.Playing;
import org.soc.common.game.GameStatus.WaitingForPlayers;
import org.soc.common.lobby.actions.CreateGame;
import org.soc.common.lobby.actions.LobbyChat;
import org.soc.common.lobby.actions.UserJoined;
import org.soc.common.server.entities.User.Player;

@SerialTypes(
{ Player.class, LobbyChat.class, // GameInfoImpl.class, LoginResponseDto.class,
UserJoined.class, CreateGame.class, // GameInfoImpl.class,
WaitingForPlayers.class, Playing.class }) public abstract class OpenSettlersSerializer
        extends CometSerializer
{}
