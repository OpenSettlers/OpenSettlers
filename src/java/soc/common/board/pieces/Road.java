package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.HexSide;
import soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import soc.common.board.pieces.abstractPieces.SidePiece;
import soc.common.board.pieces.abstractPieces.StockPiece;
import soc.common.board.resources.Clay;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Timber;
import soc.common.board.routing.GraphPoint;
import soc.common.game.player.GamePlayer;
import soc.common.views.meta.Graphics;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.generic.ToolTip;
import soc.common.views.widgetsInterface.payerInfo.StockItemWidget;
import soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;
import soc.common.views.widgetsInterface.visuals.PieceVisual;
import soc.common.views.widgetsInterface.visuals.VisualFactory;
import soc.gwtClient.images.Resources;

public class Road extends AbstractPlayerPiece implements SidePiece, StockPiece
{
    private static final long serialVersionUID = -6137419255953696891L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().road(), null, null,
                Resources.icons().roadSmall());

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
    private HexSide sideLocation;

    @Override
    public String toString()
    {
        return "Road";
    }

    @Override
    public ResourceList getCost()
    {
        ResourceList result = new ResourceList();

        result.add(new Timber());
        result.add(new Clay());

        return result;
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
        if (player.getStock().getRoads().size() == 0)
            return false;

        return true;
    }

    @Override
    public HexSide getSide()
    {
        return sideLocation;
    }

    /*
     * Returns true when the player has enough resources to pay for the road or
     * he has one or more road tokens
     * 
     * @see soc.common.board.pieces.PlayerPiece#canPay(soc.common.game.Player)
     */
    @Override
    public boolean canPay(GamePlayer player)
    {
        return super.canPay(player) || player.getRoadBuildingTokens() > 0;
    }

    @Override
    public boolean isStockPiece()
    {
        return true;
    }

    @Override
    public SidePiece setSide(HexSide side)
    {
        sideLocation = side;
        return null;
    }

    @Override
    public void addToPlayer(GamePlayer player)
    {
        player.getRoads().add(this);
        player.getSidePieces().add(this);
        player.getStock().getRoads().remove(this);
    }

    @Override
    public void removeFromPlayer(GamePlayer player)
    {
    }

    @Override
    public boolean affectsRoad()
    {
        return true;
    }

    @Override
    public void addToBoard(Board board)
    {
        board.getGraph().addRoad(this);
    }

    @Override
    public void removeFromBoard(Board board)
    {
    }

    @Override
    public boolean canConnect(GraphPoint graphPoint, SidePiece otherPiece)
    {
        return (player.equals(graphPoint.getPlayer()) || graphPoint.getPlayer() == null)
                && otherPiece.connectsWithRoad();
    }

    @Override
    public boolean connectsWithBridge()
    {
        return true;
    }

    @Override
    public boolean connectsWithRoad()
    {
        return true;
    }

    @Override
    public boolean connectsWithShip()
    {
        return false;
    }

    @Override
    public PieceVisual createPiece(VisualFactory visualFactory)
    {
        return visualFactory.createRoadVisual(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public StockItemWidget createStockItemWidget(StockItemWidgetFactory factory)
    {
        return factory.createRoadStockWidget();
    }
}
