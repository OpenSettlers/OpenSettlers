package soc.common.board.hexes;

import soc.common.board.resources.Gold;
import soc.common.board.resources.Resource;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class GoldHex extends ResourceHex
{
    private static final long serialVersionUID = 415190181441875977L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().goldHex(), null,
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
        return new Gold();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        GoldHex goldHex = new GoldHex();
        goldHex.setChit(chit.copy());
        goldHex.setLocation(hexLocation);
        goldHex.setTerritory(territory);
        return goldHex;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ImageResource getTexture()
    {
        return Resources.images().goldHex();
    }
}