package soc.common.game;

public interface PlayerList extends Iterable<Player>
{
    public void remove(PlayerList players);
    public void remove(Player player);
    public void add(Player player);
}
