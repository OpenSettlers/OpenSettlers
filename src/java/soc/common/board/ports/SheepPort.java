package soc.common.board.ports;

import soc.common.board.resources.Resource;
import soc.common.board.resources.Sheep;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class SheepPort extends TwoToOneResourcePort
{
    private static final long serialVersionUID = -8884377093715212493L;
    private static Sheep sheep = new Sheep();
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().sheepPort(), null,
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

    @Override
    public Port copy()
    {
        return new SheepPort();
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
        return sheep;
    }
}
