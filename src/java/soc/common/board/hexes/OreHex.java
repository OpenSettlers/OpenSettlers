package soc.common.board.hexes;

import soc.common.board.resources.Ore;
import soc.common.board.resources.Resource;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class OreHex extends ResourceHex
{
    private static final long serialVersionUID = -2594315235125762897L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().oreHex16(),
                        Resources.icons().oreHex32(), Resources.icons()
                                        .oreHex48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "OreHex";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().oreHex();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().oreHexDescription();
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
        return new Ore();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.AbstractHex#copy()
     */
    @Override
    public Hex copy()
    {
        OreHex oreHex = new OreHex();
        oreHex.setChit(chit.copy());
        oreHex.setLocation(hexLocation);
        oreHex.setTerritory(territory);
        return oreHex;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ImageResource getTexture()
    {
        return Resources.images().oreHex();
    }
}
