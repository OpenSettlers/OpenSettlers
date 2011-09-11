package org.soc.common.server;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PlayedGameDto implements IsSerializable { 

  int ratingDifference;
  boolean hasGainedRating;
  boolean hasLostRating;
  boolean hasWonGame;
  boolean hasLostGame;
  org.soc.common.server.PlayerDto player;
  int victoryPoints;

  public PlayedGameDto(int ratingDifference, boolean hasGainedRating, boolean hasLostRating, boolean hasWonGame, boolean hasLostGame, org.soc.common.server.PlayerDto player, int victoryPoints) {
    this.ratingDifference = ratingDifference;
    this.hasGainedRating = hasGainedRating;
    this.hasLostRating = hasLostRating;
    this.hasWonGame = hasWonGame;
    this.hasLostGame = hasLostGame;
    this.player = player;
    this.victoryPoints = victoryPoints;
  }

  protected PlayedGameDto() {
    // Possibly for serialization.
  }

  public int getRatingDifference() {
    return ratingDifference;
  }

  public boolean isHasGainedRating() {
    return hasGainedRating;
  }

  public boolean isHasLostRating() {
    return hasLostRating;
  }

  public boolean isHasWonGame() {
    return hasWonGame;
  }

  public boolean isHasLostGame() {
    return hasLostGame;
  }

  public org.soc.common.server.PlayerDto getPlayer() {
    return player;
  }

  public int getVictoryPoints() {
    return victoryPoints;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    PlayedGameDto other = (PlayedGameDto) obj;
    if (ratingDifference != other.ratingDifference)
        return false;
    if (hasGainedRating != other.hasGainedRating)
        return false;
    if (hasLostRating != other.hasLostRating)
        return false;
    if (hasWonGame != other.hasWonGame)
        return false;
    if (hasLostGame != other.hasLostGame)
        return false;
    if (player == null) {
      if (other.player != null)
        return false;
    } else if (!player.equals(other.player))
      return false;
    if (victoryPoints != other.victoryPoints)
        return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + new Integer(ratingDifference).hashCode();
    hashCode = (hashCode * 37) + new Boolean(hasGainedRating).hashCode();
    hashCode = (hashCode * 37) + new Boolean(hasLostRating).hashCode();
    hashCode = (hashCode * 37) + new Boolean(hasWonGame).hashCode();
    hashCode = (hashCode * 37) + new Boolean(hasLostGame).hashCode();
    hashCode = (hashCode * 37) + (player == null ? 1 : player.hashCode());
    hashCode = (hashCode * 37) + new Integer(victoryPoints).hashCode();
    return hashCode;
  }

  @Override
  public String toString() {
    return "PlayedGameDto["
                 + ratingDifference
                 + ","
                 + hasGainedRating
                 + ","
                 + hasLostRating
                 + ","
                 + hasWonGame
                 + ","
                 + hasLostGame
                 + ","
                 + player
                 + ","
                 + victoryPoints
    + "]";
  }
}
