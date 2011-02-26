package soc.common.views.meta;

import soc.common.views.widgetsInterface.generic.ToolTip;

public interface Meta
{
    public String getName();

    public String getLocalizedName();

    public String getDescription();

    public Icon icon();

    public Graphics graphics();

    public ToolTip createToolTip();
}
