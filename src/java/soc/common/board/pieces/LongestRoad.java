package soc.common.board.pieces;

import soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import soc.common.board.routing.Route;
import soc.common.game.LongestRoadChangedEvent;
import soc.common.game.LongestRoadChangedEventHandler;
import soc.common.game.VictoryPointItem;
import soc.common.game.player.GamePlayer;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
import soc.gwtClient.game.widgetsInterface.visuals.VisualFactory;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class LongestRoad extends AbstractPlayerPiece implements
        VictoryPointItem
{
    private static final long serialVersionUID = -1851490325447009277L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().longestRoad(), null,
                null, Resources.icons().longestRoadSmall());

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
    private Route route;
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    public Route getRoute()
    {
        return route;
    }

    /**
     * @param route
     *            the route to set
     */
    public LongestRoad setRoute(Route route)
    {
        Route oldRoute = route;
        this.route = route;
        this.player = this.route.getPlayer();
        eventBus.fireEvent(new LongestRoadChangedEvent(oldRoute, route));
        return this;
    }

    @Override
    public int getVictoryPoints()
    {
        return 2;
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

    public HandlerRegistration addLongestRoadChangedEventHandler(
            LongestRoadChangedEventHandler handler)
    {
        return eventBus.addHandler(LongestRoadChangedEvent.TYPE, handler);
    }

    @Override
    public boolean affectsRoad()
    {
        return false;
    }

    @Override
    public PieceVisual createPiece(VisualFactory visualFactory)
    {
        return null;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
