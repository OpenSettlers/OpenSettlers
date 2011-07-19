package org.soc.common.board.hexes;

import org.soc.common.board.resources.Resource;
import org.soc.common.board.resources.Timber;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class TimberHex extends ResourceHex
{
    private static final long serialVersionUID = 34740915606830388L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().timberHex16(),
                        Resources.icons().timberHex32(), Resources.icons()
                                        .timberHex48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "TimberHex";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().timberHex();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().timberHexDescription();
        }

    };

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.AbstractHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return new Timber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        TimberHex timberHex = new TimberHex();
        timberHex.setChit(chit.copy());
        timberHex.setLocation(hexLocation);
        timberHex.setTerritory(territory);
        return timberHex;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ImageResource getTexture()
    {
        return Resources.images().timberHex();
    }

}
