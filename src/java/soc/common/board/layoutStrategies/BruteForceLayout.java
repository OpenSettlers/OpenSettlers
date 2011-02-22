package soc.common.board.layoutStrategies;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.Board;
import soc.common.board.Chit;
import soc.common.board.ChitList;
import soc.common.board.HexLocation;
import soc.common.board.hexes.Hex;
import soc.common.board.hexes.RandomHex;
import soc.common.board.ports.Port;
import soc.common.board.ports.PortList;
import soc.common.board.ports.RandomPort;
import soc.common.board.territories.Territory;
import soc.common.game.Game;
import soc.common.server.randomization.Random;

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
        List<Hex> hexesToPutChitOn = new ArrayList<Hex>();
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
                if (newHex.hasResource())
                    hexesToPutChitOn.add(newHex);
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
            if (hex.hasPort() && hex.getPort() instanceof RandomPort)
            {
                HexLocation land = hex.getPort().getLandLocation();
                Hex landHex = board.getHexes().get(land);
                Territory t = landHex.getTerritory();
                Port newPort = t.grabPort(random, supportedPorts);
                newPort
                        .setRotationPosition(hex.getPort()
                                .getRotationPosition());
                newPort.setHexLocation(hex.getLocation());
                hex.setPort(newPort);
            }
        }
    }

    private List<Hex> copyResourceHexList(List<Hex> original)
    {
        List<Hex> copy = new ArrayList<Hex>();

        for (Hex hex : original)
            copy.add(hex);

        return copy;
    }

    private void layoutChits(List<Hex> hexesToPutChitOn, ChitList chits,
            Random random)
    {
        while (hexesToPutChitOn.size() > 0)
        {
            Chit chit = chits.grabRandom(random);
            Hex hex = hexesToPutChitOn.get(0);
            hex.setChit(chit);
            hexesToPutChitOn.remove(0);
        }
    }

    /*
     * Returns false when a neighbouring hex equals the chitnumber of the source
     * hex, or the source hex number equals 6/8 and the neighbouring hex also
     * equals 6/8.
     */
    private boolean validateChitsOnBoard(List<Hex> hexesWithChit, Board board)
    {
        for (Hex hex : hexesWithChit)
        {
            List<HexLocation> neighbours = hex.getLocation().getNeighbours();
            for (HexLocation neighbourLocation : neighbours)
            {
                Hex neighbour = board.getHexes().get(neighbourLocation);
                if (neighbour.hasResource() && neighbour.hasChit())
                {
                    if (neighbour.getChit().getNumber() == neighbour.getChit()
                            .getNumber())
                    {
                        return false;
                    }
                    if (hex.getChit().getNumber() == 6
                            || hex.getChit().getNumber() == 8
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
