package soc.common.board.hexes;

import soc.common.board.resources.Clay;
import soc.common.board.resources.Resource;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class SheepHex extends ResourceHex
{
    private static final long serialVersionUID = -4252055009001134451L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().sheepHex16(),
                        Resources.icons().sheepHex32(), Resources.icons()
                                        .sheepHex48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "SheepHex";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().sheepHex();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().sheepHexDescription();
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
        SheepHex sheepHex = new SheepHex();
        sheepHex.setChit(chit.copy());
        sheepHex.setLocation(hexLocation);
        sheepHex.setTerritory(territory);
        return sheepHex;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ImageResource getTexture()
    {
        return Resources.images().sheepHex();
    }
}
