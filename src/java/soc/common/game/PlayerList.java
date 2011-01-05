package soc.common.game;

import soc.common.game.player.GamePlayer;
import soc.common.game.player.GamePlayerImpl;

public interface PlayerList extends Iterable<GamePlayerImpl>
{
    public void remove(PlayerList players);
    public void remove(GamePlayer player);
    public void add(GamePlayer player);
}
