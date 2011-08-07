package org.soc.common.board.pieces;

import org.soc.common.board.Board;
import org.soc.common.board.hexes.Hex;
import org.soc.common.board.layout.HasPoint;
import org.soc.common.board.layout.HexPoint;
import org.soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import org.soc.common.board.pieces.abstractPieces.Producable;
import org.soc.common.board.pieces.abstractPieces.StockPiece;
import org.soc.common.board.resources.Clay;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.board.resources.Sheep;
import org.soc.common.board.resources.Timber;
import org.soc.common.board.resources.Wheat;
import org.soc.common.game.VictoryPointItem;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.game.variants.GameRules;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.payerInfo.StockItemWidget;
import org.soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;
import org.soc.gwt.client.images.Resources;

public class Town extends AbstractPlayerPiece implements VictoryPointItem,
                HasPoint, Producable, StockPiece
{
    private static final long serialVersionUID = -2696233711789990786L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().town16(), Resources
                        .icons().town32(), Resources.icons().town48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Town";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().town();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().townDescription();
        }

    };
    private HexPoint pointLocation;

    @Override
    public String toString()
    {
        return "Town";
    }

    @Override
    public ResourceList getCost()
    {
        ResourceList result = new ResourceList();

        result.add(new Timber());
        result.add(new Wheat());
        result.add(new Clay());
        result.add(new Sheep());

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.pieces.PlayerPiece#canBuild(org.soc.common.board.Board,
     * org.soc.common.game.Player)
     */
    @Override
    public boolean canBuild(Board board, GamePlayer player)
    {
        // We need a town in stock...
        if (player.getStock().getTowns().size() == 0)
            return false;

        return true;
    }

    @Override
    public int getVictoryPoints()
    {
        return 1;
    }

    @Override
    public HexPoint getPoint()
    {
        return pointLocation;
    }

    @Override
    public HasPoint setPoint(HexPoint point)
    {
        this.pointLocation = point;

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
        player.getTowns().moveFrom(player.getStock().getTowns(), this);
        player.getPointPieces().add(this);
        player.getProducables().add(this);
        player.getVictoryPoints().add(this);
    }

    @Override
    public void removeFromPlayer(GamePlayer player)
    {
        player.getStock().getTowns().moveFrom(player.getTowns(), this);
        player.getPointPieces().remove(this);
        player.getProducables().remove(this);
        player.getVictoryPoints().remove(this);
    }

    @Override
    public ResourceList produce(Hex hex, GameRules rules)
    {
        ResourceList production = new ResourceList();
        production.add(hex.getResource().copy());
        return production;
    }

    @Override
    public boolean affectsRoad()
    {
        return true;
    }

    @Override
    public void addToBoard(Board board)
    {
        board.getGraph().addTown(this);
    }

    @Override
    public void removeFromBoard(Board board)
    {
    }

    @Override
    public PieceVisual createPiece(VisualFactory visualFactory)
    {
        return visualFactory.createTownVisual(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public StockItemWidget createStockItemWidget(StockItemWidgetFactory factory)
    {
        return factory.createTownStockWidget();
    }
}
