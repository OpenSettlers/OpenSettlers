package soc.common.board.hexes;

import soc.common.board.Chit;
import soc.common.views.meta.Graphics;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.generic.ToolTip;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class DesertHex extends AbstractHex
{
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().desertHex(), null,
                null, null);
        private Graphics graphics = new Graphics()
        {
            @Override
            public ImageResource graphics()
            {
                return Resources.images().desertHex();
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
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex copy()
    {
        DesertHex result = new DesertHex();

        result.setTerritory(territory);

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        return "DarkKhaki";
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.LandHex#isBuildableLand()
     */
    @Override
    public boolean isBuildableLand()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.LandHex#isBuildableSea()
     */
    @Override
    public boolean isBuildableSea()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.LandHex#isPartOfGame()
     */
    @Override
    public boolean isPartOfGame()
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.LandHex#isPiratePlaceable()
     */
    @Override
    public boolean isPiratePlaceable()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.hexes.LandHex#isRobberPlaceable()
     */
    @Override
    public boolean isRobberPlaceable()
    {
        return true;
    }

    @Override
    public boolean canHaveChit()
    {
        return false;
    }

    @Override
    public boolean hasChit()
    {
        return false;
    }

    @Override
    public boolean hasResource()
    {
        return false;
    }

    @Override
    public Chit getChit()
    {
        return null;
    }

    @Override
    public Hex setChit(Chit chit)
    {
        return this;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
