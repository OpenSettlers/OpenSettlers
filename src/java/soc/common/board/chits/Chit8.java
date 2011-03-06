package soc.common.board.chits;

import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class Chit8 extends AbstractChit
{
    private static final long serialVersionUID = -2593827639221299571L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().chit816(), Resources
                        .icons().chit832(), Resources.icons().chit848());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Chit8";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().chit8();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().chit8Description();
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
        return new Chit8();
    }

    @Override
    public int getChance()
    {
        return 5;
    }

    @Override
    public int getNumber()
    {
        return 8;
    }

}
