package org.soc.common.board.layoutStrategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soc.common.board.Board;
import org.soc.common.board.chits.Chit;
import org.soc.common.board.hexes.Hex;
import org.soc.common.board.hexes.RandomHex;
import org.soc.common.board.layout.HexLocation;
import org.soc.common.board.territories.Territory;
import org.soc.common.core.Core;
import org.soc.common.game.Game;
import org.soc.common.server.randomization.Random;


/*
 * Lays out the board by placing the chits grouped by their chance. Highest chance chits are
 * laid out first (6 and 8), then all the way down to 1 (12 and 2).
 */
public class RedsFirstLayout implements LayoutStrategy
{
    Random random = Core.get().getRandom();
    private List<Hex> hexesToPutChitOn = new ArrayList<Hex>();

    /*
     * Walks each territory, then replaces RandomHexes by hexes from the territory, then lays out
     * chits of the territory
     * 
     * @see org.soc.common.board.layoutStrategies.LayoutStrategy#layoutBoard(org.soc.common.game.Game)
     */
    @Override
    public void layoutBoard(Game game)
    {
        Board board = game.getBoard();
        for (Territory territory : game.getBoard().getTerritories())
            // Only mainland is supported for now
            if (territory.isMainland())
            {
                // Create a list of random Hexes to replace
                List<Hex> randomHexes = new ArrayList<Hex>();
                for (Hex hex : board.getHexes())
                    if (territory.equals(hex.getTerritory())
                                    && hex instanceof RandomHex)
                        randomHexes.add(hex);

                // Replace the randomhexes of this territory
                replaceRandomHexes(board, randomHexes, territory);

                // Place chits on hexes of this territory
                layoutChits(board, territory);
            }
    }

    /*
     * Place a chit on every hex by placing high-probability chits first
     */
    private void layoutChits(Board board, Territory territory)
    {
        Map<Integer, List<Chit>> chitsByChance = chitsByProbability(territory
                        .getChits());

        // Iterate over each hexes grouped by their chances to be
        // rolled, starting
        // by the highest probability all the way to the lowest
        for (int i = 5; i > 0; i--)
        {
            List<Chit> chits = chitsByChance.get(i);
            if (chits != null)
            {
                // Lay each chit of current group on the board, trying
                // to prevent neighbours with chits equal to their chance
                List<Hex> allowedHexes = copyResourceHexList(hexesToPutChitOn);
                for (Chit chit : chits)
                {
                    Hex hex = null;

                    // Grab a randomized location to put the chit onto
                    if (allowedHexes.size() == 0)
                        // If no unique spots are found, pick known bad
                        // spot
                        hex = hexesToPutChitOn.get(random.nextInt(
                                        hexesToPutChitOn.size(), false));
                    else
                        // There are spots left where chit does not
                        // neighbour another chit with the same probablity of
                        // being rolled
                        hex = allowedHexes.get(random.nextInt(
                                        allowedHexes.size(), false));

                    // Put the chit on the hex
                    hex.setChit(chit);

                    // Remove the hex from both lists
                    allowedHexes.remove(hex);
                    hexesToPutChitOn.remove(hex);

                    // Remove neighbours from the allowed hexes list
                    allowedHexes.removeAll(getNeighbours(board, hex));
                }

            }
        }
    }

    /*
     * Replace the random hexes by hexes from the territory
     */
    private void replaceRandomHexes(Board board, List<Hex> randomHexes,
                    Territory territory)
    {
        for (Hex hex : randomHexes)
        {
            // Grab another hex
            Hex newHex = territory.getHexes().grabRandom(random)
                            .setTerritory(hex.getTerritory())
                            .setLocation(hex.getLocation());

            // Put it on the board
            board.getHexes().set(hex.getLocation(), newHex);

            // Add to list of hexes to put a chit on, if needing a chit
            if (newHex.canHaveChit())
                hexesToPutChitOn.add(newHex);
        }
    }

    /*
     * Returns a map of chits sorted by their probability, given a list of chits
     */
    private Map<Integer, List<Chit>> chitsByProbability(List<Chit> chits)
    {
        Map<Integer, List<Chit>> result = new HashMap<Integer, List<Chit>>();

        // Put every chit in the map
        for (Chit chit : chits)
        {
            // When we haven't yet encountered a chit with given probability, add
            // a list to the mapfor that probability
            if (result.get(chit.getChance()) == null)
                result.put(chit.getChance(), new ArrayList<Chit>());

            // Add the chit
            result.get(chit.getChance()).add(chit);
        }

        return result;
    }

    /*
     * Returns a list of neighbours form given hex
     */
    private List<Hex> getNeighbours(Board board, Hex hex)
    {
        List<Hex> neighbours = new ArrayList<Hex>();

        // Grab neighbour locations form given hex
        List<HexLocation> locations = hex.getLocation().getNeighbours();
        for (HexLocation location : locations)
        {
            // Add the neighbour when territories of source and neighbour are equal
            Hex neighbour = board.getHexes().get(location);
            if (hex.getTerritory().equals(neighbour.getTerritory()))
                neighbours.add(neighbour);
        }

        return neighbours;
    }

    /*
     * Returns a copy of given list of Hexes
     */
    private List<Hex> copyResourceHexList(List<Hex> original)
    {
        List<Hex> copy = new ArrayList<Hex>();

        for (Hex hex : original)
            copy.add(hex);

        return copy;
    }
}
