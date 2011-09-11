package org.soc.common.server;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LoginResponseDto implements IsSerializable { 

  java.util.List<org.soc.common.server.entities.User> loggedInUsers;
  java.util.List<org.soc.common.server.GameDto> lobbyGames;

  public LoginResponseDto(java.util.List<org.soc.common.server.entities.User> loggedInUsers, java.util.List<org.soc.common.server.GameDto> lobbyGames) {
    this.loggedInUsers = loggedInUsers;
    this.lobbyGames = lobbyGames;
  }

  protected LoginResponseDto() {
    // Possibly for serialization.
  }

  public java.util.List<org.soc.common.server.entities.User> getLoggedInUsers() {
    return loggedInUsers;
  }

  public java.util.List<org.soc.common.server.GameDto> getLobbyGames() {
    return lobbyGames;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    LoginResponseDto other = (LoginResponseDto) obj;
    if (loggedInUsers == null) {
      if (other.loggedInUsers != null)
        return false;
    } else if (!loggedInUsers.equals(other.loggedInUsers))
      return false;
    if (lobbyGames == null) {
      if (other.lobbyGames != null)
        return false;
    } else if (!lobbyGames.equals(other.lobbyGames))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (loggedInUsers == null ? 1 : loggedInUsers.hashCode());
    hashCode = (hashCode * 37) + (lobbyGames == null ? 1 : lobbyGames.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "LoginResponseDto["
                 + loggedInUsers
                 + ","
                 + lobbyGames
    + "]";
  }
}
