package soc.common.board.hexes;

import soc.common.annotations.Sea3D;
import soc.common.board.resources.Gold;
import soc.common.board.resources.Resource;
import soc.common.utils.ClassUtils;
import soc.common.views.meta.Graphics;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.generic.ToolTip;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

@Sea3D
public class VolcanoHex extends ResourceHex
{
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().volcanoHex(), null,
                null, null);
        private Graphics graphics = new Graphics()
        {
            @Override
            public ImageResource graphics()
            {
                return Resources.images().volcanoHex();
            }
        };

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public Graphics graphics()
        {
            return graphics;
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

        @Override
        public ToolTip createToolTip()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };

    private Resource resource = new Gold();

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.ResourceHex#getResource()
     */
    @Override
    public Resource getResource()
    {
        return resource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return "DarkRed";
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.ResourceHex#getName()
     */
    @Override
    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.ResourceHex#Copy()
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
    public boolean hasResource()
    {
        return true;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}