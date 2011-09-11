package org.soc.common.lobby;

import org.soc.common.game.UserList;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public interface Lobby {
  public UserList getUsers();
  public GameInfoList games();
  public LobbyLog getLog();

  public class LobbyImpl implements Lobby {
    private UserList users;
    private GameInfoList games;
    private LobbyLog log;
    private transient EventBus eventBus = new SimpleEventBus();

    public LobbyImpl() {
      games = new GameInfoList(this, eventBus);
      log = new LobbyLog(this, eventBus);
      users = new UserList();
    }
    @Override public UserList getUsers() {
      return users;
    }
    @Override public GameInfoList games() {
      return games;
    }
    @Override public LobbyLog getLog() {
      return log;
    }
  }
}
