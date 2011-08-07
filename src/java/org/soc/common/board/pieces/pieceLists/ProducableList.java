package org.soc.common.board.pieces.pieceLists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.board.hexes.Hex;
import org.soc.common.board.pieces.abstractPieces.Producable;


public class ProducableList implements Iterable<Producable>, Serializable
{
    private static final long serialVersionUID = 2619202676402607856L;
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
