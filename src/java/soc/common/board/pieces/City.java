package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.hexes.Hex;
import soc.common.board.layout.HasPoint;
import soc.common.board.layout.HexPoint;
import soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import soc.common.board.pieces.abstractPieces.Producable;
import soc.common.board.pieces.abstractPieces.StockPiece;
import soc.common.board.resources.Ore;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.Wheat;
import soc.common.game.VictoryPointItem;
import soc.common.game.player.GamePlayer;
import soc.common.game.variants.GameRules;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.payerInfo.StockItemWidget;
import soc.common.views.widgetsInterface.payerInfo.StockItemWidgetFactory;
import soc.common.views.widgetsInterface.visuals.PieceVisual;
import soc.common.views.widgetsInterface.visuals.VisualFactory;
import soc.gwt.client.images.Resources;

public class City extends AbstractPlayerPiece implements VictoryPointItem,
                HasPoint, Producable, StockPiece
{
    private static final long serialVersionUID = 6682481845539642397L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().city16(), Resources
                        .icons().city32(), null, Resources.icons().city48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "City";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().city();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().cityDescription();
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
    public HasPoint setPoint(HexPoint point)
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
        // TODO: implement for volcano's
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
        // TODO: Implement for volcano's
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

    @Override
    public StockItemWidget createStockItemWidget(StockItemWidgetFactory factory)
    {
        return factory.createCityStockWidget();
    }
}