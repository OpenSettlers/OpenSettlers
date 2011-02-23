package soc.common.board.hexes;

import soc.common.board.resources.Clay;
import soc.common.board.resources.Resource;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class SheepHex extends ResourceHex
{
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().sheepHex(), null,
                null, null);
        private Graphics graphics = new Graphics()
        {
            @Override
            public ImageResource graphics()
            {
                return Resources.images().sheepHex();
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
}