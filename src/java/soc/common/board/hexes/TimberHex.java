package soc.common.board.hexes;

import soc.common.board.resources.Resource;
import soc.common.board.resources.Timber;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class TimberHex extends ResourceHex
{
    private static final long serialVersionUID = 34740915606830388L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().timberHex(), null,
                        null, null);

        @Override
        public Icon icon()
        {
            return icon;
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

    };

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return new Timber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
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
