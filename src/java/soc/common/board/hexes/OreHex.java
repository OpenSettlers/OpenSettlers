package soc.common.board.hexes;

import soc.common.board.resources.Ore;
import soc.common.board.resources.Resource;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class OreHex extends ResourceHex
{
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().oreHex(), null,
                null, null);
        private Graphics graphics = new Graphics()
        {
            @Override
            public ImageResource graphics()
            {
                return Resources.images().oreHex();
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
}