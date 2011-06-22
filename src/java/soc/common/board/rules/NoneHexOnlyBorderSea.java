package soc.common.board.rules;

import soc.common.board.Board;
import soc.common.board.hexes.AbstractHex;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.NoneHex;
import soc.common.board.layout.HexLocation;

/*
 * NoneHexes should always be bordered with SeaHexes, or other Hexes where the isPartOfGame
 * is set to false.
 */
public class NoneHexOnlyBorderSea implements DesignRule
{

    @Override
    public String getProblem()
    {
        // TODO: fix message
        return null;
    }

    @Override
    public RuleSeverity getSeverity()
    {
        return RuleSeverity.ERROR;
    }

    /*
     * Returns false when a NoneHex is found with a non-seahex as neighbour
     * 
     * @see soc.common.board.rules.DesignRule#invoke(soc.common.board.Board)
     */
    @Override
    public boolean invoke(Board b)
    {
        // Check all Hexes
        for (Hex hex : b.getHexes())
        {
            // Only check when we encounter a NoneHex
            if (hex instanceof NoneHex)
            {
                // Check each neighbour inside the board bounds if it's a
                // SeaHex.
                for (HexLocation neighbour : hex.getLocation().getNeighbours())
                {
                    if (neighbour.fallsInsideBoardBounds(b.getWidth(),
                                    b.getHeight())
                                    && !(b.getHexes().get(neighbour) instanceof AbstractHex))
                    {
                        // Non-SeaHex found, this rule is not met.
                        return false;
                    }
                }
            }
        }

        // All NoneHexes have only SeaHexes as neighbours, evaulate to true
        return true;
    }

}
