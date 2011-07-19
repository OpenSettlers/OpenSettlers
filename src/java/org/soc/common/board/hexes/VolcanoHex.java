package org.soc.common.board.hexes;

import org.soc.common.annotations.Sea3D;
import org.soc.common.board.resources.Gold;
import org.soc.common.board.resources.Resource;
import org.soc.common.internationalization.I18n;
import org.soc.common.utils.ClassUtils;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.resources.client.ImageResource;

@Sea3D
public class VolcanoHex extends ResourceHex
{
    private static final long serialVersionUID = 9066964176287866076L;

    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, Resources.icons()
                        .volcanoHex32(), null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "VolcanoHex";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().volcanoHex();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().volcanoHexDescription();
        }

    };

    private Resource resource = new Gold();

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.ResourceHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return resource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return "DarkRed";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.ResourceHex#getName()
     */
    @Override
    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.board.hexes.ResourceHex#Copy()
     */
    @Override
    public Hex copy()
    {
        VolcanoHex result = new VolcanoHex();

        result.setTerritory(territory);

        return result;
    }

    @Override
    public boolean canHaveChit()
    {
        return true;
    }

    @Override
    public boolean hasChit()
    {
        return chit != null;
    }

    @Override
    public boolean canHaveResource()
    {
        return true;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ImageResource getTexture()
    {
        return Resources.images().volcanoHex();
    }
}