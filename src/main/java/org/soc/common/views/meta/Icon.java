package org.soc.common.views.meta;

import com.google.gwt.resources.client.ImageResource;

public interface Icon
{
    public ImageResource icon16();

    public ImageResource icon24();

    public ImageResource icon32();

    public ImageResource icon48();

    public ImageResource icon64();

    public ImageResource icon96();

    public ImageResource icon128();

    public ImageResource icon192();

    public ImageResource icon256();

    public ImageResource iconDefault();

    // Not yet supported by GWT
    // public ImageResource iconSvg();
}
