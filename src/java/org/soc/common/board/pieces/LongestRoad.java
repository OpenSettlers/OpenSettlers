package org.soc.common.board.pieces;

import org.soc.common.board.pieces.abstractPieces.AbstractPlayerPiece;
import org.soc.common.board.routing.Route;
import org.soc.common.game.LongestRoadChangedEvent;
import org.soc.common.game.LongestRoadChangedEventHandler;
import org.soc.common.game.VictoryPointItem;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

public class LongestRoad extends AbstractPlayerPiece implements
                VictoryPointItem
{
    private static final long serialVersionUID = -1851490325447009277L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().longestRoad16(),
                        Resources.icons().longestRoad32(), null, Resources
                                        .icons().longestRoad48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "LongestRoad";
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
    private Route route;
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    public Route getRoute()
    {
        return route;
    }

    /** @param route
     *            the route to set */
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
