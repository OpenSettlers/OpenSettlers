package soc.common.board.pieces;

import soc.common.annotations.SeaFarers;
import soc.common.board.HexPoint;
import soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import soc.common.board.territories.Territory;
import soc.common.game.VictoryPointItem;
import soc.common.game.player.GamePlayer;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsInterface.visuals.VisualFactory;

@SeaFarers
public class IslandBonus extends AbstractPlayerPiece implements
        VictoryPointItem
{
    private static final long serialVersionUID = -4359339700965121307L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, null, null);

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
    private Territory territory;
    private HexPoint location;

    /**
     * @return the location
     */
    public HexPoint getLocation()
    {
        return location;
    }

    /**
     * @return the territory
     */
    public Territory getTerritory()
    {
        return territory;
    }

    /**
     * @param territory
     *            the territory to set
     */
    public IslandBonus setTerritory(Territory territory)
    {
        this.territory = territory;

        return this;
    }

    @Override
    public int getVictoryPoints()
    {
        return 1;
    }

    @Override
    public boolean isStockPiece()
    {
        return false;
    }

    @Override
    public void addToPlayer(GamePlayer player)
    {
        player.getVictoryPoints().add(this);
    }

    @Override
    public void removeFromPlayer(GamePlayer player)
    {
        player.getVictoryPoints().remove(this);
    }

    @Override
    public boolean affectsRoad()
    {
        return false;
    }

    @Override
    public PieceVisual createPiece(VisualFactory visualFactory)
    {
        return visualFactory.createIslandBonus(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
