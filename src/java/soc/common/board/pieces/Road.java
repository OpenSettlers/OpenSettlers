package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.layout.HasSide;
import soc.common.board.layout.HexSide;
import soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import soc.common.board.pieces.abstractPieces.StockPiece;
import soc.common.board.resources.Clay;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Timber;
import soc.common.board.routing.GraphPoint;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.payerInfo.StockItemWidget;
import soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;
import soc.common.views.widgetsInterface.visuals.PieceVisual;
import soc.common.views.widgetsInterface.visuals.VisualFactory;
import soc.gwtClient.images.Resources;

public class Road extends AbstractPlayerPiece implements HasSide, StockPiece
{
    private static final long serialVersionUID = -6137419255953696891L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().road16(), Resources
                        .icons().road32(), Resources.icons().road48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Road";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().longestRoad();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().longestRoadDescription();
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
    public HasSide setSide(HexSide side)
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
    public boolean canConnect(GraphPoint graphPoint, HasSide otherPiece)
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
