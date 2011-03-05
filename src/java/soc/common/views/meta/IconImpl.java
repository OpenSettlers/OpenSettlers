package soc.common.views.meta;

import soc.gwtClient.images.Resources;

import com.google.gwt.resources.client.ImageResource;

/*
 * Default icon implementation supporting icons 16,32,48,64,96,128,192,256px square
 * When icons sizes are omitted, Unavailable.png is returned.
 * Don't let the autoformat horror make you depressed
 */
public class IconImpl implements Icon
{
    private final ImageResource icon16;
    private final ImageResource icon24 = Resources.icons().unavailable24();
    private final ImageResource icon32;
    private final ImageResource icon48;
    private final ImageResource icon64;
    private final ImageResource icon96;
    private final ImageResource icon128;
    private final ImageResource icon192;
    private final ImageResource icon256;

    public IconImpl(ImageResource icon16)
    {
        this.icon16 = icon16 == null ? Resources.icons().unavailable16()
                        : icon16;
        this.icon32 = Resources.icons().unavailable32();
        this.icon48 = Resources.icons().unavailable48();
        this.icon64 = Resources.icons().unavailable64();
        this.icon96 = Resources.icons().unavailable96();
        this.icon128 = Resources.icons().unavailable128();
        this.icon192 = Resources.icons().unavailable192();
        this.icon256 = Resources.icons().unavailable256();
    }

    public IconImpl(ImageResource icon16, ImageResource icon32)
    {
        this.icon16 = icon16 == null ? Resources.icons().unavailable16()
                        : icon16;
        this.icon32 = icon32 == null ? Resources.icons().unavailable32()
                        : icon32;
        this.icon48 = Resources.icons().unavailable48();
        this.icon64 = Resources.icons().unavailable64();
        this.icon96 = Resources.icons().unavailable96();
        this.icon128 = Resources.icons().unavailable128();
        this.icon192 = Resources.icons().unavailable192();
        this.icon256 = Resources.icons().unavailable256();
    }

    public IconImpl(ImageResource icon16, ImageResource icon32,
                    ImageResource icon48)
    {
        this.icon16 = icon16 == null ? Resources.icons().unavailable16()
                        : icon16;
        this.icon32 = icon32 == null ? Resources.icons().unavailable32()
                        : icon32;
        this.icon48 = icon48 == null ? Resources.icons().unavailable48()
                        : icon48;
        this.icon64 = Resources.icons().unavailable64();
        this.icon96 = Resources.icons().unavailable96();
        this.icon128 = Resources.icons().unavailable128();
        this.icon192 = Resources.icons().unavailable192();
        this.icon256 = Resources.icons().unavailable256();
    }

    public IconImpl(ImageResource icon16, ImageResource icon32,
                    ImageResource icon48, ImageResource icon64)
    {
        this.icon16 = icon16 == null ? Resources.icons().unavailable16()
                        : icon16;
        this.icon32 = icon32 == null ? Resources.icons().unavailable32()
                        : icon32;
        this.icon48 = icon48 == null ? Resources.icons().unavailable48()
                        : icon48;
        this.icon64 = icon64 == null ? Resources.icons().unavailable64()
                        : icon64;
        this.icon96 = Resources.icons().unavailable96();
        this.icon128 = Resources.icons().unavailable128();
        this.icon192 = Resources.icons().unavailable192();
        this.icon256 = Resources.icons().unavailable256();
    }

    public IconImpl(ImageResource icon16, ImageResource icon32,
                    ImageResource icon48, ImageResource icon64,
                    ImageResource icon96)
    {
        this.icon16 = icon16 == null ? Resources.icons().unavailable16()
                        : icon16;
        this.icon32 = icon32 == null ? Resources.icons().unavailable32()
                        : icon32;
        this.icon48 = icon48 == null ? Resources.icons().unavailable48()
                        : icon48;
        this.icon64 = icon64 == null ? Resources.icons().unavailable64()
                        : icon64;
        this.icon96 = icon96 == null ? Resources.icons().unavailable96()
                        : icon96;
        this.icon128 = Resources.icons().unavailable128();
        this.icon192 = Resources.icons().unavailable192();
        this.icon256 = Resources.icons().unavailable256();
    }

    public IconImpl(ImageResource icon16, ImageResource icon32,
                    ImageResource icon48, ImageResource icon64,
                    ImageResource icon96, ImageResource icon128)
    {
        this.icon16 = icon16 == null ? Resources.icons().unavailable16()
                        : icon16;
        this.icon32 = icon32 == null ? Resources.icons().unavailable32()
                        : icon32;
        this.icon48 = icon48 == null ? Resources.icons().unavailable48()
                        : icon48;
        this.icon64 = icon64 == null ? Resources.icons().unavailable64()
                        : icon64;
        this.icon96 = icon96 == null ? Resources.icons().unavailable96()
                        : icon96;
        this.icon128 = icon128 == null ? Resources.icons().unavailable128()
                        : icon128;
        this.icon192 = Resources.icons().unavailable192();
        this.icon256 = Resources.icons().unavailable256();
    }

    public IconImpl(ImageResource icon16, ImageResource icon32,
                    ImageResource icon48, ImageResource icon64,
                    ImageResource icon96, ImageResource icon128,
                    ImageResource icon192)
    {
        this.icon16 = icon16 == null ? Resources.icons().unavailable16()
                        : icon16;
        this.icon32 = icon32 == null ? Resources.icons().unavailable32()
                        : icon32;
        this.icon48 = icon48 == null ? Resources.icons().unavailable48()
                        : icon48;
        this.icon64 = icon64 == null ? Resources.icons().unavailable64()
                        : icon64;
        this.icon96 = icon96 == null ? Resources.icons().unavailable96()
                        : icon96;
        this.icon128 = icon128 == null ? Resources.icons().unavailable128()
                        : icon128;
        this.icon192 = icon192 == null ? Resources.icons().unavailable192()
                        : icon192;
        this.icon256 = Resources.icons().unavailable256();
    }

    /*
     * Returns first non-default icon in the following order: 32-48-24-16-64-96-128-192-256
     * If no non-default is found, returns default (Unavailable.png)
     * 
     * @see soc.common.views.meta.Icon#iconDefault()
     */
    @Override
    public ImageResource iconDefault()
    {
        return !icon32.equals(Resources.icons().unavailable32()) ? icon32
                        : !icon48.equals(Resources.icons().unavailable48()) ? icon48
                                        : !icon16.equals(Resources.icons()
                                                        .unavailable16()) ? icon16
                                                        : !icon24.equals(Resources
                                                                        .icons()
                                                                        .unavailable24()) ? icon24
                                                                        : !icon64.equals(Resources
                                                                                        .icons()
                                                                                        .unavailable64()) ? icon64
                                                                                        : !icon96.equals(Resources
                                                                                                        .icons()
                                                                                                        .unavailable96()) ? icon96
                                                                                                        : !icon128.equals(Resources
                                                                                                                        .icons()
                                                                                                                        .unavailable128()) ? icon128
                                                                                                                        : !icon192.equals(Resources
                                                                                                                                        .icons()
                                                                                                                                        .unavailable192()) ? icon192
                                                                                                                                        : !icon256.equals(Resources
                                                                                                                                                        .icons()
                                                                                                                                                        .unavailable256()) ? icon256
                                                                                                                                                        : Resources.icons()
                                                                                                                                                                        .unavailable32();
        // Woeha! autoformat awesomeness

    }
    @Override
    public ImageResource icon16()
    {
        return icon16;
    }

    @Override
    public ImageResource icon24()
    {
        return icon24;
    }

    @Override
    public ImageResource icon32()
    {
        return icon32;
    }

    @Override
    public ImageResource icon48()
    {
        return icon48;
    }

    @Override
    public ImageResource icon64()
    {
        return icon64;
    }

    @Override
    public ImageResource icon96()
    {
        return icon96;
    }

    @Override
    public ImageResource icon128()
    {
        return icon128;
    }

    @Override
    public ImageResource icon192()
    {
        return icon192;
    }

    @Override
    public ImageResource icon256()
    {
        return icon256;
    }
}