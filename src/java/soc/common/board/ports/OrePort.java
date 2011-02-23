package soc.common.board.ports;

import soc.common.board.resources.Ore;
import soc.common.board.resources.Resource;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.images.Resources;

public class OrePort extends TwoToOneResourcePort
{
    private static final long serialVersionUID = 1142423242419232130L;
    private static Ore ore = new Ore();
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().orePort(), null,
                null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public Graphics graphics()
        {
            // TODO Auto-generated method stub
            return null;
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

    @Override
    public Port copy()
    {
        return new OrePort();
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.ports.AbstractPort#getResource()
     */
    @Override
    public Resource getResource()
    {
        return ore;
    }

    @Override
    public boolean canTrade(Resource resource)
    {
        return resource.isSameType(ore);
    }

}
