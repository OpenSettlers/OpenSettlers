package soc.common.board.hexes;

import soc.common.board.resources.Diamond;
import soc.common.board.resources.Resource;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwt.client.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class DiamondHex extends ResourceHex
{
    private static final long serialVersionUID = 22283290007058031L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().diamondHex16(),
                        Resources.icons().diamondHex32(), Resources.icons()
                                        .diamondHex48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "DiamondHex";
        }

        @Override
        public String getLocalizedName()
        {
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
        return new Diamond();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        DiamondHex diamondHex = new DiamondHex();
        diamondHex.setChit(chit.copy());
        diamondHex.setLocation(hexLocation);
        diamondHex.setTerritory(territory);
        return diamondHex;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ImageResource getTexture()
    {
        return Resources.images().diamondHex();
    }
}