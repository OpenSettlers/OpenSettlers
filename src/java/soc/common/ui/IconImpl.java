package soc.common.ui;

import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

public class IconImpl implements Icon
{
    private ImageResource iconDefault;
    private ImageResource iconLarge;
    private ImageResource iconMedium;
    private ImageResource iconSmall;

    public IconImpl(ImageResource iconDefault, ImageResource iconLarge,
            ImageResource iconMedium, ImageResource iconSmall)
    {
        super();
        this.iconDefault = iconDefault;
        this.iconLarge = iconLarge == null ? Resources.icons()
                .unavailableLarge() : iconLarge;
        this.iconMedium = iconMedium == null ? Resources.icons()
                .unavailableMedium() : iconMedium;
        this.iconSmall = iconSmall == null ? Resources.icons()
                .unavailableSmall() : iconSmall;
    }

    @Override
    public ImageResource iconDefault()
    {
        return iconDefault;
    }

    @Override
    public ImageResource iconLarge()
    {
        return iconLarge;
    }

    @Override
    public ImageResource iconMedium()
    {
        return iconMedium;
    }

    @Override
    public ImageResource iconSmall()
    {
        return iconSmall;
    }

}
