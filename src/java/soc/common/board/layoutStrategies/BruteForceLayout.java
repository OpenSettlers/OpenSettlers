package soc.common.board.layoutStrategies;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.Board;
import soc.common.board.Chit;
import soc.common.board.ChitList;
import soc.common.board.HexLocation;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.RandomHex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.ports.Port;
import soc.common.board.ports.PortList;
import soc.common.board.ports.RandomPort;
import soc.common.board.territories.Territory;
import soc.common.game.Game;
import soc.common.server.random.Random;

/*
 * Randomly lays out the chits. When equal neighbours or 6/8 neighbours 
 * are detected, it lays out the chit again until a validated layout is 
 * found.
 * 
 */
public class BruteForceLayout implements LayoutStrategy
{
    private int maxTries = 1;

    public BruteForceLayout()
    {
    }

    public BruteForceLayout(int maxTries)
    {
        super();
        this.maxTries = maxTries;
    }

    @Override
    public void layoutBoard(Game game)
    {
        Board board = game.getBoard();
        Territory territory = board.getTerritories().get(0);
        List<ResourceHex> hexesToPutChitOn = new ArrayList<ResourceHex>();
        PortList supportedPorts = game.getRules().getSupportedPorts();
        Random random = game.getRandom();
        List<HexLocation> randomHexes = new ArrayList<HexLocation>();
        int actualTries = 0;

        // Replace random hexes by actual random hex from list of hexes provided
        // by the territory
        for (Hex hex : board.getHexes())
        {
            if (hex instanceof RandomHex)
            {
                randomHexes.add(hex.getLocation());
                // Replace randomhex by a hex grabbed from territories' list of
                // hexes
                Hex newHex = territory.getHexes().grabRandom(random)
                        .setTerritory(hex.getTerritory()).setLocation(
                                hex.getLocation());
                board.getHexes().set(hex.getLocation(), newHex);
                if (newHex instanceof ResourceHex)
                    hexesToPutChitOn.add((ResourceHex) newHex);
            }
        }

        do
        {
            layoutChits(copyResourceHexList(hexesToPutChitOn), territory
                    .getChits().copy(), random);
            actualTries++;
        } while (maxTries > actualTries
                && !validateChitsOnBoard(hexesToPutChitOn, board));

        System.out.println("info: Chits layout took " + actualTries
                + " iterations");

        // Replace all random port placeholders by actual ports
        for (Hex hex : board.getHexes())
        {
            if (hex instanceof SeaHex)
            {
                SeaHex seaHex = (SeaHex) hex;
                if (seaHex.getPort() != null)
                {
                    if (seaHex.getPort() instanceof RandomPort)
                    {
                        HexLocation land = seaHex.getPort().getLandLocation();
                        Hex landHex = board.getHexes().get(land);
                        Territory t = landHex.getTerritory();
                        Port newPort = t.grabPort(random, supportedPorts);
                        newPort.setRotationPosition(seaHex.getPort()
                                .getRotationPosition());
                        newPort.setHexLocation(seaHex.getLocation());
                        seaHex.setPort(newPort);
                    }
                }
            }
        }
    }

    private List<ResourceHex> copyResourceHexList(List<ResourceHex> original)
    {
        List<ResourceHex> copy = new ArrayList<ResourceHex>();

        for (ResourceHex hex : original)
            copy.add(hex);

        return copy;
    }

    private void layoutChits(List<ResourceHex> hexesToPutChitOn,
            ChitList chits, Random random)
    {
        while (hexesToPutChitOn.size() > 0)
        {
            Chit chit = chits.grabRandom(random);
            ResourceHex resourceHex = hexesToPutChitOn.get(0);
            resourceHex.setChit(chit);
            hexesToPutChitOn.remove(0);
        }
    }

    /*
     * Returns false when a neighbouring hex equals the chitnumber of the source
     * hex, or the source hex number equals 6/8 and the neighbouring hex also
     * equals 6/8.
     */
    private boolean validateChitsOnBoard(List<ResourceHex> hexesWithChit,
            Board board)
    {
        for (ResourceHex resourceHex : hexesWithChit)
        {
            List<HexLocation> neighbours = resourceHex.getLocation()
                    .getNeighbours();
            for (HexLocation neighbourLocation : neighbours)
            {
                Hex hex = board.getHexes().get(neighbourLocation);
                if (hex instanceof ResourceHex)
                {
                    ResourceHex neighbour = (ResourceHex) hex;
                    if (resourceHex.getChit().getNumber() == neighbour
                            .getChit().getNumber())
                    {
                        return false;
                    }
                    if (resourceHex.getChit().getNumber() == 6
                            || resourceHex.getChit().getNumber() == 8
                            && neighbour.getChit().getNumber() == 6
                            || neighbour.getChit().getNumber() == 8)
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
