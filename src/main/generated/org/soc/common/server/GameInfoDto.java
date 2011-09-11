package org.soc.common.server;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GameInfoDto implements IsSerializable { 

  int id;
  java.lang.String name;
  org.soc.common.server.entities.User host;
  java.util.List<org.soc.common.server.entities.User> players;
  java.lang.String boardId;
  org.soc.common.game.GameSettings settings;

  public GameInfoDto(int id, java.lang.String name, org.soc.common.server.entities.User host, java.util.List<org.soc.common.server.entities.User> players, java.lang.String boardId, org.soc.common.game.GameSettings settings) {
    this.id = id;
    this.name = name;
    this.host = host;
    this.players = players;
    this.boardId = boardId;
    this.settings = settings;
  }

  protected GameInfoDto() {
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

  public java.util.List<org.soc.common.server.entities.User> getPlayers() {
    return players;
  }

  public java.lang.String getBoardId() {
    return boardId;
  }

  public org.soc.common.game.GameSettings getSettings() {
    return settings;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GameInfoDto other = (GameInfoDto) obj;
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
    if (players == null) {
      if (other.players != null)
        return false;
    } else if (!players.equals(other.players))
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
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + new Integer(id).hashCode();
    hashCode = (hashCode * 37) + (name == null ? 1 : name.hashCode());
    hashCode = (hashCode * 37) + (host == null ? 1 : host.hashCode());
    hashCode = (hashCode * 37) + (players == null ? 1 : players.hashCode());
    hashCode = (hashCode * 37) + (boardId == null ? 1 : boardId.hashCode());
    hashCode = (hashCode * 37) + (settings == null ? 1 : settings.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GameInfoDto["
                 + id
                 + ","
                 + name
                 + ","
                 + host
                 + ","
                 + players
                 + ","
                 + boardId
                 + ","
                 + settings
    + "]";
  }
}
