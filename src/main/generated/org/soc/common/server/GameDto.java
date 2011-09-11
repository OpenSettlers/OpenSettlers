package org.soc.common.server;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GameDto implements IsSerializable { 

  int id;
  java.lang.String name;
  org.soc.common.server.entities.User host;
  java.util.List<org.soc.common.server.entities.User> users;
  java.lang.String boardId;
  org.soc.common.game.GameSettings settings;
  org.soc.common.game.GameStatus status;
  org.soc.common.lobby.LobbyLog lobbyLog;

  public GameDto(int id, java.lang.String name, org.soc.common.server.entities.User host, java.util.List<org.soc.common.server.entities.User> users, java.lang.String boardId, org.soc.common.game.GameSettings settings, org.soc.common.game.GameStatus status, org.soc.common.lobby.LobbyLog lobbyLog) {
    this.id = id;
    this.name = name;
    this.host = host;
    this.users = users;
    this.boardId = boardId;
    this.settings = settings;
    this.status = status;
    this.lobbyLog = lobbyLog;
  }

  protected GameDto() {
    // Possibly for serialization.
  }

  public int getId() {
    return id;
  }

  public java.lang.String getName() {
    return name;
  }

  public org.soc.common.server.entities.User getHost() {
    return host;
  }

  public java.util.List<org.soc.common.server.entities.User> getUsers() {
    return users;
  }

  public java.lang.String getBoardId() {
    return boardId;
  }

  public org.soc.common.game.GameSettings getSettings() {
    return settings;
  }

  public org.soc.common.game.GameStatus getStatus() {
    return status;
  }

  public org.soc.common.lobby.LobbyLog getLobbyLog() {
    return lobbyLog;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GameDto other = (GameDto) obj;
    if (id != other.id)
        return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (host == null) {
      if (other.host != null)
        return false;
    } else if (!host.equals(other.host))
      return false;
    if (users == null) {
      if (other.users != null)
        return false;
    } else if (!users.equals(other.users))
      return false;
    if (boardId == null) {
      if (other.boardId != null)
        return false;
    } else if (!boardId.equals(other.boardId))
      return false;
    if (settings == null) {
      if (other.settings != null)
        return false;
    } else if (!settings.equals(other.settings))
      return false;
    if (status == null) {
      if (other.status != null)
        return false;
    } else if (!status.equals(other.status))
      return false;
    if (lobbyLog == null) {
      if (other.lobbyLog != null)
        return false;
    } else if (!lobbyLog.equals(other.lobbyLog))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + new Integer(id).hashCode();
    hashCode = (hashCode * 37) + (name == null ? 1 : name.hashCode());
    hashCode = (hashCode * 37) + (host == null ? 1 : host.hashCode());
    hashCode = (hashCode * 37) + (users == null ? 1 : users.hashCode());
    hashCode = (hashCode * 37) + (boardId == null ? 1 : boardId.hashCode());
    hashCode = (hashCode * 37) + (settings == null ? 1 : settings.hashCode());
    hashCode = (hashCode * 37) + (status == null ? 1 : status.hashCode());
    hashCode = (hashCode * 37) + (lobbyLog == null ? 1 : lobbyLog.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GameDto["
                 + id
                 + ","
                 + name
                 + ","
                 + host
                 + ","
                 + users
                 + ","
                 + boardId
                 + ","
                 + settings
                 + ","
                 + status
                 + ","
                 + lobbyLog
    + "]";
  }
}
