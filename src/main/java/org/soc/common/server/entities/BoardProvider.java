package org.soc.common.server.entities;

import java.util.List;

import org.soc.common.game.board.Board;

/*
 * Interface for a source to provide Board instances
 */
public interface BoardProvider<B extends Board>
{
    public Board byName(String name);
    public Board byId(String id);
    public List<Board> byUser(User user);
    public List<Board> all();
}