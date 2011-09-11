package org.soc.common.server.entities;

import java.io.Serializable;

public interface User extends Serializable {
  public String name();
  public User setName(String name);
  public int id();
  public User setId(int id);
  public int amountGamesPlayed();
  public User setGamesPlayed(int gamesPlayed);
  public User setRegistered(boolean isRegistered);
  public boolean isRegistered();
  public String password();
  public User setPassword(String password);
  public boolean isAnonymous();

  public abstract class AbstractUser implements User {
    private static final long serialVersionUID = 2830056305909862121L;
    private String name;
    private int id;
    private int gamesPlayed;
    private boolean isRegistered;

    public String name() {
      return name;
    }
    public User setName(String name) {
      this.name = name;
      return this;
    }
    public int id() {
      return id;
    }
    public User setId(int id) {
      this.id = id;
      return this;
    }
    public int amountGamesPlayed() {
      return gamesPlayed;
    }
    public User setGamesPlayed(int gamesPlayed) {
      this.gamesPlayed = gamesPlayed;
      return this;
    }
    public User setRegistered(boolean isRegistered) {
      this.isRegistered = isRegistered;
      return this;
    }
    public boolean isRegistered() {
      return isRegistered;
    }
    @Override public int hashCode() {
      return id;
    }
    @Override public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      User other = (User) obj;
      if (id != other.id())
        return false;
      return true;
    }
  }

  public class Player implements User {
    private String name;
    private int id;
    private int gamesPlayed;
    private boolean isRegistered;
    private String password;

    public String name() {
      return name;
    }
    public Player setName(String name) {
      this.name = name;
      return this;
    }
    public int id() {
      return id;
    }
    public Player setId(int id) {
      this.id = id;
      return this;
    }
    public int amountGamesPlayed() {
      return gamesPlayed;
    }
    public Player setGamesPlayed(int gamesPlayed) {
      this.gamesPlayed = gamesPlayed;
      return this;
    }
    public Player setRegistered(boolean isRegistered) {
      this.isRegistered = isRegistered;
      return this;
    }
    public boolean isRegistered() {
      return isRegistered;
    }
    @Override public String password() {
      return password;
    }
    @Override public User setPassword(String password) {
      this.password = password;
      return this;
    }
    @Override public boolean isAnonymous() {
      return false;
    }
  }

  public class ServerUser implements User {
    @Override public int amountGamesPlayed() {
      return 0;
    }
    @Override public int id() {
      return 0;
    }
    @Override public String name() {
      return "Server";
    }
    @Override public String password() {
      return null;
    }
    @Override public boolean isRegistered() {
      return false;
    }
    @Override public User setGamesPlayed(int gamesPlayed) {
      return null;
    }
    @Override public User setId(int id) {
      return this;
    }
    @Override public User setName(String name) {
      return this;
    }
    @Override public User setPassword(String password) {
      return this;
    }
    @Override public User setRegistered(boolean isRegistered) {
      return this;
    }
    @Override public boolean isAnonymous() {
      return false;
    }
  }

  public class AnonymousUser implements User {
    private String name;
    private int id;
    private int gamesPlayed;
    private boolean isRegistered;
    private String password;

    @Override public String name() {
      return name;
    }
    @Override public User setName(String name) {
      this.name = name;
      return this;
    }
    @Override public int id() {
      return id;
    }
    @Override public User setId(int id) {
      this.id = id;
      return this;
    }
    @Override public int amountGamesPlayed() {
      return 0;
    }
    @Override public User setGamesPlayed(int gamesPlayed) {
      return this;
    }
    @Override public User setRegistered(boolean isRegistered) {
      return this;
    }
    @Override public boolean isRegistered() {
      return false;
    }
    @Override public String password() {
      return null;
    }
    @Override public User setPassword(String password) {
      return this;
    }
    @Override public boolean isAnonymous() {
      return true;
    }
  }

  public class BotUser extends AbstractUser implements User {
    @Override public String password() {
      return null;
    }
    @Override public User setPassword(String password) {
      return null;
    }
    @Override public boolean isAnonymous() {
      return false;
    }
  }
}
