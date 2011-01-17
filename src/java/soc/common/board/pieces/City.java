package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.HexPoint;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.resources.Ore;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Wheat;
import soc.common.game.GameRules;
import soc.common.game.VictoryPointItem;
import soc.common.game.player.GamePlayer;

public class City extends AbstractPlayerPiece implements VictoryPointItem,
        PointPiece, Producable
{
    private static final long serialVersionUID = 6682481845539642397L;
    public static City CITY = new City();
    private HexPoint pointLocation;

    @Override
    public ResourceList getCost()
    {
        ResourceList result = new ResourceList();

        result.add(new Wheat());
        result.add(new Wheat());
        result.add(new Ore());
        result.add(new Ore());
        result.add(new Ore());

        return result;
    }

    @Override
    public String toString()
    {
        return "City";
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.pieces.PlayerPiece#canBuild(soc.common.board.Board,
     * soc.common.game.Player)
     */
    @Override
    public boolean canBuild(Board board, GamePlayer player)
    {
        // We need a city in stock...
        if (player.getStock().getCities().size() == 0)
            return false;

        // And we need a town to replace.
        if (player.getTowns().size() == 0)
            return false;

        return true;
    }

    /*
     * City is worth 2 victory points
     * 
     * @see soc.common.game.IVictoryPointItem#amount()
     */
    @Override
    public int getVictoryPoints()
    {
        return 2;
    }

    public HexPoint getPoint()
    {
        return pointLocation;
    }

    @Override
    public PointPiece setPoint(HexPoint point)
    {
        pointLocation = point;

        return this;
    }

    @Override
    public boolean isStockPiece()
    {
        return true;
    }

    @Override
    public void addToPlayer(GamePlayer player)
    {
        // Put City on board
        player.getCities().moveFrom(player.getStock().getCities(), this);
        player.getPointPieces().add(this);
        player.getProducables().add(this);

        // Add to victory points
        player.getVictoryPoints().add(this);
    }

    @Override
    public void removeFromPlayer(GamePlayer player)
    {

    }

    @Override
    public ResourceList produce(ResourceHex resourceHex, GameRules rules)
    {
        ResourceList production = new ResourceList();

        production.add(resourceHex.getResource().copy());
        production.add(resourceHex.getResource().copy());

        return production;
    }
}
