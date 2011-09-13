package org.soc.common.game.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soc.common.core.Core;
import org.soc.common.game.Chit;
import org.soc.common.game.ChitList;
import org.soc.common.game.Game;
import org.soc.common.game.Port;
import org.soc.common.game.Port.RandomPort;
import org.soc.common.game.PortList;
import org.soc.common.game.Random;
import org.soc.common.game.Territory;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.hexes.RandomHex;

/** Prepares a saved board definition into a playable board. 1. Puts hexes from InitialRandomHexes
 * list on RandomHexes, 2. Replaces random ports from those out of RandomPorts bag, 3. Replaces
 * deserts by volcano/jungles if necessary */
public interface LayoutStrategy {
  public void layoutBoard(Game game);

  public class AbstractLayoutStrategy implements LayoutStrategy {
    @Override public void layoutBoard(Game game) {
      // TODO Auto-generated method stub
    }
    /* Returns amount of chits with neighbour having equal number. */
    private int equalNumberNeighbours(Board board) {
      int amountSameNumbers = 0;
      for (Territory territory : board.getTerritories()) {
        // Assemble a list of hexes from this territory having
        List<Hex> hexesWithChit = new ArrayList<Hex>();
        for (Hex hex : board.hexes())
          if (hex.chit() != null)
            hexesWithChit.add(hex);
        // Brute force: check neighbours of each hex (18*6=98ops)
        for (Hex hex : hexesWithChit)
          for (Hex neighbour : board.hexes().validNeighbours(hex))
            // Neighbour must support a chit...
            if (neighbour.hasChit()
                    // ...currently have one...
                    && neighbour.chit() != null
                    // ..and have same number on it
                    && neighbour.chit().number() == hex.chit()
                            .number())
              amountSameNumbers++;
      }
      System.out.println("Same neighbours in board: " + amountSameNumbers);
      return amountSameNumbers;
    }
  }

  /** Randomly lays out the chits. When equal neighbours or 6/8 neighbours are detected, it lays out
   * the chit again until a validated layout is found. */
  public class BruteForceLayout extends AbstractLayoutStrategy {
    private int maxTries = 1;

    public BruteForceLayout() {}
    public BruteForceLayout(int maxTries) {
      super();
      this.maxTries = maxTries;
    }
    @Override public void layoutBoard(Game game) {
      Board board = game.board();
      Territory territory = board.getTerritories().get(0);
      List<Hex> hexesToPutChitOn = new ArrayList<Hex>();
      PortList supportedPorts = game.rules().getSupportedPorts();
      Random random = game.random();
      List<HexLocation> randomHexes = new ArrayList<HexLocation>();
      int actualTries = 0;
      // Replace random hexes by actual random hex from list of hexes provided
      // by the territory
      for (Hex hex : board.hexes()) {
        if (hex instanceof RandomHex) {
          randomHexes.add(hex.location());
          // Replace randomhex by a hex grabbed from territories' list of
          // hexes
          Hex newHex = territory.hexes().grabRandom(random)
                  .setTerritory(hex.territory()).setLocation(
                          hex.location());
          board.hexes().set(hex.location(), newHex);
          if (newHex.producesResource())
            hexesToPutChitOn.add(newHex);
        }
      }
      do {
        layoutChits(copyResourceHexList(hexesToPutChitOn), territory
                .chits().copy(), random);
        actualTries++;
      } while (maxTries > actualTries
              && !validateChitsOnBoard(hexesToPutChitOn, board));
      System.out.println("info: Chits layout took " + actualTries
              + " iterations");
      // Replace all random port placeholders by actual ports
      for (Hex hex : board.hexes()) {
        if (hex.canHavePort() && hex.port() instanceof RandomPort) {
          HexLocation land = hex.port().landLocation();
          Hex landHex = board.hexes().get(land);
          Territory t = landHex.territory();
          Port newPort = t.grabPort(random, supportedPorts);
          newPort.setRotationPosition(hex.port().rotationPosition());
          newPort.setHexLocation(hex.location());
          hex.setPort(newPort);
        }
      }
    }
    private List<Hex> copyResourceHexList(List<Hex> original) {
      List<Hex> copy = new ArrayList<Hex>();
      for (Hex hex : original)
        copy.add(hex);
      return copy;
    }
    private void layoutChits(List<Hex> hexesToPutChitOn, ChitList chits, Random random) {
      while (hexesToPutChitOn.size() > 0) {
        Chit chit = chits.grabRandom(random);
        Hex hex = hexesToPutChitOn.get(0);
        hex.setChit(chit);
        hexesToPutChitOn.remove(0);
      }
    }
    /* Returns false when a neighbouring hex equals the chitnumber of the source hex, or the source
     * hex number equals 6/8 and the neighbouring hex also equals 6/8. */
    private boolean validateChitsOnBoard(List<Hex> hexesWithChit, Board board) {
      for (Hex hex : hexesWithChit) {
        List<HexLocation> neighbours = hex.location().getNeighbours();
        for (HexLocation neighbourLocation : neighbours) {
          Hex neighbour = board.hexes().get(neighbourLocation);
          if (neighbour.producesResource() && neighbour.chit() != null) {
            if (neighbour.chit().number() == neighbour.chit().number())
              return false;
            if (hex.chit().isRed() && neighbour.chit().isRed())
              return false;
          }
        }
      }
      return true;
    }
  }

  /** Lays out the board by placing the chits grouped by their chance. Highest chance chits are laid
   * out first (6 and 8), then all the way down to 1 (12 and 2). */
  public class RedsFirstLayout implements LayoutStrategy {
    Random random = Core.get().random();
    private List<Hex> hexesToPutChitOn = new ArrayList<Hex>();

    /** Walks each territory, then replaces RandomHexes by hexes from the territory, then lays out
     * chits of the territory */
    @Override public void layoutBoard(Game game) {
      Board board = game.board();
      for (Territory territory : game.board().getTerritories())
        // Only mainland is supported for now
        if (territory.isMainland()) {
          // Create a list of random Hexes to replace
          List<Hex> randomHexes = new ArrayList<Hex>();
          for (Hex hex : board.hexes())
            if (territory.equals(hex.territory())
                    && hex instanceof RandomHex)
              randomHexes.add(hex);
          // Replace the randomhexes of this territory
          replaceRandomHexes(board, randomHexes, territory);
          // Place chits on hexes of this territory
          layoutChits(board, territory);
        }
    }
    /** Place a chit on every hex by placing high-probability chits first */
    private void layoutChits(Board board, Territory territory) {
      Map<Integer, List<Chit>> chitsByChance = chitsByProbability(territory.chits());
      // Iterate over each hexes grouped by their chances to be
      // rolled, starting
      // by the highest probability all the way to the lowest
      for (int i = 5; i > 0; i--) {
        List<Chit> chits = chitsByChance.get(i);
        if (chits != null) {
          // Lay each chit of current group on the board, trying
          // to prevent neighbours with chits equal to their chance
          List<Hex> allowedHexes = copyResourceHexList(hexesToPutChitOn);
          for (Chit chit : chits) {
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
    /* Replace the random hexes by hexes from the territory */
    private void replaceRandomHexes(Board board, List<Hex> randomHexes, Territory territory) {
      for (Hex hex : randomHexes) {
        // Grab another hex
        Hex newHex = territory.hexes().grabRandom(random)
                .setTerritory(hex.territory())
                .setLocation(hex.location());
        // Put it on the board
        board.hexes().set(hex.location(), newHex);
        // Add to list of hexes to put a chit on, if needing a chit
        if (newHex.canHaveChit())
          hexesToPutChitOn.add(newHex);
      }
    }
    /* Returns a map of chits sorted by their probability, given a list of chits */
    private Map<Integer, List<Chit>> chitsByProbability(ChitList chits) {
      Map<Integer, List<Chit>> result = new HashMap<Integer, List<Chit>>();
      // Put every chit in the map
      for (Chit chit : chits) {
        // When we haven't yet encountered a chit with given probability, add
        // a list to the mapfor that probability
        if (result.get(chit.chance()) == null)
          result.put(chit.chance(), new ArrayList<Chit>());
        // Add the chit
        result.get(chit.chance()).add(chit);
      }
      return result;
    }
    /* Returns a list of neighbours form given hex */
    private List<Hex> getNeighbours(Board board, Hex hex) {
      List<Hex> neighbours = new ArrayList<Hex>();
      // Grab neighbour locations form given hex
      List<HexLocation> locations = hex.location().getNeighbours();
      for (HexLocation location : locations) {
        // Add the neighbour when territories of source and neighbour are equal
        Hex neighbour = board.hexes().get(location);
        if (hex.territory().equals(neighbour.territory()))
          neighbours.add(neighbour);
      }
      return neighbours;
    }
    /* Returns a copy of given list of Hexes */
    private List<Hex> copyResourceHexList(List<Hex> original) {
      List<Hex> copy = new ArrayList<Hex>();
      for (Hex hex : original)
        copy.add(hex);
      return copy;
    }
  }
}
