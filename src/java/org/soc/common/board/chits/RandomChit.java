package org.soc.common.board.chits;

import org.soc.common.internationalization.I18n;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.Resources;

public class RandomChit implements Chit
{
    private static final long serialVersionUID = 4450530964160694813L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().randomChit16(),
                        Resources.icons().randomChit32(), Resources.icons()
                                        .randomChit48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "RandomChit";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().chitRandom();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().chitRandomDescription();
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
        return new RandomChit();
    }

    @Override
    public int getChance()
    {
        return 0;
    }

    @Override
    public int getNumber()
    {
        return 0;
    }

    @Override
    public boolean isRed()
    {
        return false;
    }

}
