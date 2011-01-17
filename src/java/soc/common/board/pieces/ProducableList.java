package soc.common.board.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.board.hexes.Hex;

public class ProducableList implements Iterable<Producable>
{
    private List<Producable> producables = new ArrayList<Producable>();

    public void add(Producable producable)
    {
        producables.add(producable);
    }

    public void remove(Producable producable)
    {
        producables.remove(producable);
    }

    public ProducableList producablesAtHex(Hex hex)
    {
        ProducableList result = new ProducableList();

        for (Producable producable : producables)
            if (producable.getPoint().hasLocation(hex.getLocation()))
                result.add(producable);

        return result;
    }

    @Override
    public Iterator<Producable> iterator()
    {
        return producables.iterator();
    }
}
