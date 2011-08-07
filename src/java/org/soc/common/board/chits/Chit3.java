package org.soc.common.board.chits;

import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

public class Chit3 extends AbstractChit
{
    private static final long serialVersionUID = -7935501769616769044L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().chit316(), Resources
                        .icons().chit332(), Resources.icons().chit348());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Chit3";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().chit3();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().chit3Description();
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
        return new Chit3();
    }

    @Override
    public int getChance()
    {
        return 2;
    }

    @Override
    public int getNumber()
    {
        return 3;
    }

}
