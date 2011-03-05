package soc.common.board.hexes;

import soc.common.board.resources.Resource;
import soc.common.board.resources.Wheat;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class WheatHex extends ResourceHex
{
    private static final long serialVersionUID = 2329772028245348633L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().wheatHex(), null,
                        null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "WheatHex";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().wheatHex();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().wheatHexDescription();
        }

    };

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return new Wheat();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        WheatHex wheatHex = new WheatHex();
        wheatHex.setChit(chit.copy());
        wheatHex.setLocation(hexLocation);
        wheatHex.setTerritory(territory);
        return wheatHex;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ImageResource getTexture()
    {
        return Resources.images().wheatHex();
    }
}
