package org.soc.common.board.layoutStrategies;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.board.Board;
import org.soc.common.board.hexes.Hex;
import org.soc.common.board.territories.Territory;
import org.soc.common.game.Game;


public class AbstractLayoutStrategy implements LayoutStrategy
{

    @Override
    public void layoutBoard(Game game)
    {
        // TODO Auto-generated method stub

    }

    /*
     * Returns amount of chits with neighbour having equal number.
     */
    private int equalNumberNeighbours(Board board)
    {
        int amountSameNumbers = 0;
        for (Territory territory : board.getTerritories())
        {
            // Assemble a list of hexes from this territory having
            List<Hex> hexesWithChit = new ArrayList<Hex>();
            for (Hex hex : board.getHexes())
                if (hex.getChit() != null)
                    hexesWithChit.add(hex);

            // Brute force: check neighbours of each hex (18*6=98ops)
            for (Hex hex : hexesWithChit)
                for (Hex neighbour : board.getHexes().getValidNeighbours(hex))
                    // Neighbour must support a chit...
                    if (neighbour.hasChit()
                    // ...currently have one...
                            && neighbour.getChit() != null
                            // ..and have same number on it
                            && neighbour.getChit().getNumber() == hex.getChit()
                                    .getNumber())
                        amountSameNumbers++;
        }
        System.out.println("Same neighbours in board: " + amountSameNumbers);
        return amountSameNumbers;
    }
}
