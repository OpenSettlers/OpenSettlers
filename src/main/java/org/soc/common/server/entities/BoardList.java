package org.soc.common.server.entities;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.board.Board;

public class BoardList implements BoardProvider {
  private List<Board> boards = new ArrayList<Board>();

  @Override public List<Board> all() {
    return boards;
  }
  @Override public Board byId(String id) {
    for (Board board : boards)
      if (board.getId().equals(id))
        return board;
    return null;
  }
  @Override public List<Board> byUser(User user) {
    List<Board> result = new ArrayList<Board>();
    for (Board board : boards)
      if (board.name().equals(user.name()))
        result.add(board);
    return result;
  }
  @Override public Board byName(String name)
  {
    // TODO Auto-generated method stub
    return null;
  }
}
