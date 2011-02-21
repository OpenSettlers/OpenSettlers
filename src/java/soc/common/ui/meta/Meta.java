package soc.common.ui.meta;

import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;

public interface Meta
{
    public String getName();

    public String getLocalizedName();

    public String getDescription();

    public Icon icon();

    public Graphics graphics();

    public ToolTip createToolTip();
}
