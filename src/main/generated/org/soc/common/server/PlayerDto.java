package org.soc.common.server;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PlayerDto implements IsSerializable { 

  int gamesPlayed;
  int gamesWon;
  int gamesLost;
  double gameWonPercentage;
  java.lang.String name;
  java.lang.String passwordHash;
  java.lang.String primaryColor;
  java.lang.String secondaryColor;

  public PlayerDto(int gamesPlayed, int gamesWon, int gamesLost, double gameWonPercentage, java.lang.String name, java.lang.String passwordHash, java.lang.String primaryColor, java.lang.String secondaryColor) {
    this.gamesPlayed = gamesPlayed;
    this.gamesWon = gamesWon;
    this.gamesLost = gamesLost;
    this.gameWonPercentage = gameWonPercentage;
    this.name = name;
    this.passwordHash = passwordHash;
    this.primaryColor = primaryColor;
    this.secondaryColor = secondaryColor;
  }

  protected PlayerDto() {
    // Possibly for serialization.
  }

  public int getGamesPlayed() {
    return gamesPlayed;
  }

  public int getGamesWon() {
    return gamesWon;
  }

  public int getGamesLost() {
    return gamesLost;
  }

  public double getGameWonPercentage() {
    return gameWonPercentage;
  }

  public java.lang.String getName() {
    return name;
  }

  public java.lang.String getPasswordHash() {
    return passwordHash;
  }

  public java.lang.String getPrimaryColor() {
    return primaryColor;
  }

  public java.lang.String getSecondaryColor() {
    return secondaryColor;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    PlayerDto other = (PlayerDto) obj;
    if (gamesPlayed != other.gamesPlayed)
        return false;
    if (gamesWon != other.gamesWon)
        return false;
    if (gamesLost != other.gamesLost)
        return false;
    if (gameWonPercentage != other.gameWonPercentage)
        return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (passwordHash == null) {
      if (other.passwordHash != null)
        return false;
    } else if (!passwordHash.equals(other.passwordHash))
      return false;
    if (primaryColor == null) {
      if (other.primaryColor != null)
        return false;
    } else if (!primaryColor.equals(other.primaryColor))
      return false;
    if (secondaryColor == null) {
      if (other.secondaryColor != null)
        return false;
    } else if (!secondaryColor.equals(other.secondaryColor))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + new Integer(gamesPlayed).hashCode();
    hashCode = (hashCode * 37) + new Integer(gamesWon).hashCode();
    hashCode = (hashCode * 37) + new Integer(gamesLost).hashCode();
    hashCode = (hashCode * 37) + new Double(gameWonPercentage).hashCode();
    hashCode = (hashCode * 37) + (name == null ? 1 : name.hashCode());
    hashCode = (hashCode * 37) + (passwordHash == null ? 1 : passwordHash.hashCode());
    hashCode = (hashCode * 37) + (primaryColor == null ? 1 : primaryColor.hashCode());
    hashCode = (hashCode * 37) + (secondaryColor == null ? 1 : secondaryColor.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "PlayerDto["
                 + gamesPlayed
                 + ","
                 + gamesWon
                 + ","
                 + gamesLost
                 + ","
                 + gameWonPercentage
                 + ","
                 + name
                 + ","
                 + passwordHash
                 + ","
                 + primaryColor
                 + ","
                 + secondaryColor
    + "]";
  }
}
