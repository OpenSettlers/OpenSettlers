package soc.common.board.hexes;

import soc.common.board.chits.Chit;
import soc.common.board.ports.Port;
import soc.common.board.ports.PortChangedEvent;
import soc.common.board.territories.Territory;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class SeaHex extends AbstractHex
{
    private static final long serialVersionUID = -8018622446925340186L;

    // A SeaHex may have a port associated with it
    private Port port;

    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().seaHex16(),
                        Resources.icons().seaHex32(), Resources.icons()
                                        .seaHex48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "SeaHex";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().seaHex();
        }
        @Override
        public String getDescription()
        {
            return I18n.get().constants().seaHexDescription();
        }
    };

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

    /** @return the port */
    @Override
    public Port getPort()
    {
        return port;
    }

    /** @param port
     *            the port to set */
    @Override
    public AbstractHex setPort(Port p)
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
    public boolean canHaveResource()
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
    public boolean canHavePort()
    {
        return port != null;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ImageResource getTexture()
    {
        return Resources.images().seaHex();
    }
}