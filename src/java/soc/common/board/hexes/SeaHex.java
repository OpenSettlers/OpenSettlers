package soc.common.board.hexes;

import soc.common.board.Chit;
import soc.common.board.ports.Port;
import soc.common.board.ports.PortChangedEvent;
import soc.common.board.ports.PortChangedEventHandler;
import soc.common.board.territories.Territory;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.resources.client.ImageResource;

public class SeaHex extends AbstractHex
{
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().seaHex(), null,
                null, null);
        private Graphics graphics = new Graphics()
        {
            @Override
            public ImageResource graphics()
            {
                return Resources.images().seaHex();
            }
        };

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public Graphics graphics()
        {
            return graphics;
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
    // A SeaHex may have a port associated with it
    private Port port;

    // Event notification instance
    private SimpleEventBus eventBus = new SimpleEventBus();

    /*
     * Has no effect on a SeaHex, sea hexes do not have a territory
     * 
     * @see
     * soc.common.board.hexes.Hex#setTerritory(soc.common.board.territories.
     * Territory)
     */
    @Override
    public Hex setTerritory(Territory t)
    {
        return this;
    }

    /**
     * @return the port
     */
    @Override
    public Port getPort()
    {
        return port;
    }

    /**
     * @param port
     *            the port to set
     */
    @Override
    public SeaHex setPort(Port p)
    {
        this.port = p;

        eventBus.fireEvent(new PortChangedEvent(port));

        return this;
    }

    /*
     * Copies this sea hex without the port
     * 
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex copy()
    {
        return new SeaHex().setLocation(hexLocation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return "DarkBlue";
    }

    public HandlerRegistration addPortChangedEventHandler(
            PortChangedEventHandler handler)
    {
        return eventBus.addHandler(PortChangedEvent.TYPE, handler);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#isBuildableLand()
     */
    @Override
    public boolean isBuildableLand()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#isBuildableSea()
     */
    @Override
    public boolean isBuildableSea()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#isPartOfGame()
     */
    @Override
    public boolean isPartOfGame()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#isPiratePlaceable()
     */
    @Override
    public boolean isPiratePlaceable()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#isRobberPlaceable()
     */
    @Override
    public boolean isRobberPlaceable()
    {
        return false;
    }

    @Override
    public boolean canHaveChit()
    {
        return false;
    }

    @Override
    public boolean hasChit()
    {
        return false;
    }

    @Override
    public boolean hasResource()
    {
        return false;
    }

    /*
     * Returns null, SeaHexes do not support chits
     * 
     * @see soc.common.board.hexes.Hex#getChit()
     */
    @Override
    public Chit getChit()
    {
        return null;
    }

    /*
     * Does nothing, SeaHexes do not support chits
     * 
     * @see soc.common.board.hexes.Hex#setChit(soc.common.board.Chit)
     */
    @Override
    public Hex setChit(Chit chit)
    {
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#hasPort()
     */
    @Override
    public boolean hasPort()
    {
        return port != null;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}