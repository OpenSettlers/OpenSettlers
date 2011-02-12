package soc.common.board.hexes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.server.random.Random;

public class HexList implements Iterable<Hex>, Serializable
{
    private static final long serialVersionUID = 1628486402283426134L;
    private List<Hex> hexes = new ArrayList<Hex>();

    public void add(Hex hex)
    {
        hexes.add(hex);
    }

    public Hex grabRandom(Random random)
    {
        int index = random.nextInt(hexes.size() - 1, false);
        Hex result = hexes.get(index);
        hexes.remove(result);
        return result;
    }

    @Override
    public Iterator<Hex> iterator()
    {
        return hexes.iterator();
    }

}
