package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.HexPoint;
import soc.common.board.hexes.Hex;
import soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import soc.common.board.pieces.abstractPieces.PointPiece;
import soc.common.board.pieces.abstractPieces.Producable;
import soc.common.board.resources.Ore;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Wheat;
import soc.common.game.VictoryPointItem;
import soc.common.game.player.GamePlayer;
import soc.common.game.variants.GameRules;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsInterface.visuals.VisualFactory;
import soc.gwtClient.images.Resources;

public class City extends AbstractPlayerPiece implements VictoryPointItem,
        PointPiece, Producable
{
    private static final long serialVersionUID = 6682481845539642397L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().city(), null, null,
                Resources.icons().citySmall());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public Graphics graphics()
        {
            return null;
        }

        @Override
        public String getName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getDescription()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public ToolTip createToolTip()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };
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
    public ResourceList produce(Hex hex, GameRules rules)
    {
        ResourceList production = new ResourceList();

        production.add(hex.getResource().copy());
        production.add(hex.getResource().copy());

        return production;
    }

    @Override
    public boolean affectsRoad()
    {
        return false;
    }

    @Override
    public void addToBoard(Board board)
    {
        board.getGraph().addCity(this);
    }

    @Override
    public void removeFromBoard(Board board)
    {
    }

    @Override
    public PieceVisual createPiece(VisualFactory visualFactory)
    {
        return visualFactory.createCityVisual(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}