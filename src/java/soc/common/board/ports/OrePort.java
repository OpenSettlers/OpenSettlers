package soc.common.board.ports;

import soc.common.board.resources.Ore;
import soc.common.board.resources.Resource;
import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
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
        public String getName()
        {
            return "OrePort";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().orePort();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().orePortDescription();
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

}
