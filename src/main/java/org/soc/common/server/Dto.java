package org.soc.common.server;

import java.util.List;

import org.soc.common.game.GameSettings;
import org.soc.common.game.GameStatus;
import org.soc.common.lobby.LobbyLog;
import org.soc.common.server.entities.User;

import com.gwtplatform.dispatch.annotation.GenDto;
import com.gwtplatform.dispatch.annotation.Order;

public interface Dto {
  @GenDto public class Game {
    @Order(0) int id;
    @Order(1) String name;
    @Order(2) User host;
    @Order(3) List<User> users;
    @Order(4) String boardId;
    @Order(5) GameSettings settings;
    @Order(6) GameStatus status;
    @Order(7) LobbyLog lobbyLog;
  }

  @GenDto public class Player {
    @Order(0) int gamesPlayed;
    @Order(1) int gamesWon;
    @Order(2) int gamesLost;
    @Order(3) double gameWonPercentage;
    @Order(4) String name;
    @Order(5) String passwordHash;
    @Order(6) String primaryColor;
    @Order(7) String secondaryColor;
  }

  @GenDto public class UserCredentials {
    @Order(0) String name;
    @Order(1) String password;
  }

  @GenDto public class LoginResponse {
    @Order(0) List<User> loggedInUsers;
    @Order(1) List<GameDto> lobbyGames;
  }

  @GenDto public class Server {
    @Order(0) String getName;
    @Order(1) int connectedPlayers;
    @Order(2) String url;
    @Order(3) int port;
  }

  @GenDto public class PlayedGame {
    @Order(0) int ratingDifference;
    @Order(1) boolean hasGainedRating;
    @Order(2) boolean hasLostRating;
    @Order(3) boolean hasWonGame;
    @Order(4) boolean hasLostGame;
    @Order(5) PlayerDto player;
    @Order(6) int victoryPoints;
  }
}
