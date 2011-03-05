package soc.common.board.hexes;

import soc.common.board.resources.Clay;
import soc.common.board.resources.Resource;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class ClayHex extends ResourceHex
{
    private static final long serialVersionUID = -4833059372095423603L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().clayHex(), null,
                        null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

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
        return new Clay();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        ClayHex clayHex = new ClayHex();
        clayHex.setChit(chit.copy());
        clayHex.setLocation(hexLocation);
        clayHex.setTerritory(territory);
        return clayHex;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ImageResource getTexture()
    {
        return Resources.images().clayHex();
    }
}
