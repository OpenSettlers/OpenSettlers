package soc.common.game;

public interface PlayerList extends Iterable<GamePlayerImpl>
{
    public void remove(PlayerList players);
    public void remove(GamePlayer player);
    public void add(GamePlayer player);
}
