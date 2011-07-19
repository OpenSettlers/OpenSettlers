package org.soc.common.board.chits;

import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

public class Chit9 extends AbstractChit
{
    private static final long serialVersionUID = -2565610929859827018L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().chit916(), Resources
                        .icons().chit932(), Resources.icons().chit948());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Chit9";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().chit9();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().chit9Description();
        }

    };

    @Override
    public Meta getMeta()
    {
        return meta;
    }
    @Override
    public Chit copy()
    {
        return new Chit9();
    }

    @Override
    public int getNumber()
    {
        return 9;
    }

    @Override
    public int getChance()
    {
        return 4;
    }

}
