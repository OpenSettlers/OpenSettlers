package soc.common.board.chits;

import soc.common.internationalization.I18n;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.gwtClient.images.Resources;

public class Chit11 extends AbstractChit
{
    private static final long serialVersionUID = -418107127761907817L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().chit1116(),
                        Resources.icons().chit1132(), Resources.icons()
                                        .chit1148());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            return "Chit11";
        }

        @Override
        public String getLocalizedName()
        {
            return I18n.get().constants().chit11();
        }

        @Override
        public String getDescription()
        {
            return I18n.get().constants().chit11Description();
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
        return new Chit11();
    }

    @Override
    public int getChance()
    {
        return 2;
    }

    @Override
    public int getNumber()
    {
        return 11;
    }

}
