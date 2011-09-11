package org.soc.common.views.meta;

import org.soc.gwt.client.images.R;

import com.google.gwt.resources.client.ImageResource;

/*
 * Default icon implementation supporting icons 16,32,48,64,96,128,192,256px square
 * When icons sizes are omitted, Unavailable.png is returned.
 * Don't let the autoformat horror make you depressed
 */
public class IconImpl implements Icon
{
  private final ImageResource icon16;
  private final ImageResource icon24 = R.icons().unavailable24();
  private final ImageResource icon32;
  private final ImageResource icon48;
  private final ImageResource icon64;
  private final ImageResource icon96;
  private final ImageResource icon128;
  private final ImageResource icon192;
  private final ImageResource icon256;

  public static IconImpl nullIcon() {
    return new IconImpl(null, null, null, null);
  }
  public IconImpl(ImageResource icon16)
  {
    this.icon16 = icon16 == null ? R.icons().unavailable16()
            : icon16;
    this.icon32 = R.icons().unavailable32();
    this.icon48 = R.icons().unavailable48();
    this.icon64 = R.icons().unavailable64();
    this.icon96 = R.icons().unavailable96();
    this.icon128 = R.icons().unavailable128();
    this.icon192 = R.icons().unavailable192();
    this.icon256 = R.icons().unavailable256();
  }
  public IconImpl(ImageResource icon16, ImageResource icon32)
  {
    this.icon16 = icon16 == null ? R.icons().unavailable16()
            : icon16;
    this.icon32 = icon32 == null ? R.icons().unavailable32()
            : icon32;
    this.icon48 = R.icons().unavailable48();
    this.icon64 = R.icons().unavailable64();
    this.icon96 = R.icons().unavailable96();
    this.icon128 = R.icons().unavailable128();
    this.icon192 = R.icons().unavailable192();
    this.icon256 = R.icons().unavailable256();
  }
  public IconImpl(ImageResource icon16, ImageResource icon32,
          ImageResource icon48)
  {
    this.icon16 = icon16 == null ? R.icons().unavailable16()
            : icon16;
    this.icon32 = icon32 == null ? R.icons().unavailable32()
            : icon32;
    this.icon48 = icon48 == null ? R.icons().unavailable48()
            : icon48;
    this.icon64 = R.icons().unavailable64();
    this.icon96 = R.icons().unavailable96();
    this.icon128 = R.icons().unavailable128();
    this.icon192 = R.icons().unavailable192();
    this.icon256 = R.icons().unavailable256();
  }
  public IconImpl(ImageResource icon16, ImageResource icon32,
          ImageResource icon48, ImageResource icon64)
  {
    this.icon16 = icon16 == null ? R.icons().unavailable16()
            : icon16;
    this.icon32 = icon32 == null ? R.icons().unavailable32()
            : icon32;
    this.icon48 = icon48 == null ? R.icons().unavailable48()
            : icon48;
    this.icon64 = icon64 == null ? R.icons().unavailable64()
            : icon64;
    this.icon96 = R.icons().unavailable96();
    this.icon128 = R.icons().unavailable128();
    this.icon192 = R.icons().unavailable192();
    this.icon256 = R.icons().unavailable256();
  }
  public IconImpl(ImageResource icon16, ImageResource icon32,
          ImageResource icon48, ImageResource icon64,
          ImageResource icon96)
  {
    this.icon16 = icon16 == null ? R.icons().unavailable16()
            : icon16;
    this.icon32 = icon32 == null ? R.icons().unavailable32()
            : icon32;
    this.icon48 = icon48 == null ? R.icons().unavailable48()
            : icon48;
    this.icon64 = icon64 == null ? R.icons().unavailable64()
            : icon64;
    this.icon96 = icon96 == null ? R.icons().unavailable96()
            : icon96;
    this.icon128 = R.icons().unavailable128();
    this.icon192 = R.icons().unavailable192();
    this.icon256 = R.icons().unavailable256();
  }
  public IconImpl(ImageResource icon16, ImageResource icon32,
          ImageResource icon48, ImageResource icon64,
          ImageResource icon96, ImageResource icon128)
  {
    this.icon16 = icon16 == null ? R.icons().unavailable16()
            : icon16;
    this.icon32 = icon32 == null ? R.icons().unavailable32()
            : icon32;
    this.icon48 = icon48 == null ? R.icons().unavailable48()
            : icon48;
    this.icon64 = icon64 == null ? R.icons().unavailable64()
            : icon64;
    this.icon96 = icon96 == null ? R.icons().unavailable96()
            : icon96;
    this.icon128 = icon128 == null ? R.icons().unavailable128()
            : icon128;
    this.icon192 = R.icons().unavailable192();
    this.icon256 = R.icons().unavailable256();
  }
  public IconImpl(ImageResource icon16, ImageResource icon32,
          ImageResource icon48, ImageResource icon64,
          ImageResource icon96, ImageResource icon128,
          ImageResource icon192)
  {
    this.icon16 = icon16 == null ? R.icons().unavailable16()
            : icon16;
    this.icon32 = icon32 == null ? R.icons().unavailable32()
            : icon32;
    this.icon48 = icon48 == null ? R.icons().unavailable48()
            : icon48;
    this.icon64 = icon64 == null ? R.icons().unavailable64()
            : icon64;
    this.icon96 = icon96 == null ? R.icons().unavailable96()
            : icon96;
    this.icon128 = icon128 == null ? R.icons().unavailable128()
            : icon128;
    this.icon192 = icon192 == null ? R.icons().unavailable192()
            : icon192;
    this.icon256 = R.icons().unavailable256();
  }
  /* Returns first non-default icon in the following order: 32-48-24-16-64-96-128-192-256 If no
   * non-default is found, returns default (Unavailable.png)
   * 
   * @see org.soc.common.views.meta.Icon#iconDefault() */
  @Override public ImageResource iconDefault()
  {
    return !icon32.equals(R.icons().unavailable32()) ? icon32
            : !icon48.equals(R.icons().unavailable48()) ? icon48
                    : !icon16.equals(R.icons()
                            .unavailable16()) ? icon16
                            : !icon24.equals(R
                                    .icons()
                                    .unavailable24()) ? icon24
                                    : !icon64.equals(R
                                            .icons()
                                            .unavailable64()) ? icon64
                                            : !icon96.equals(R
                                                    .icons()
                                                    .unavailable96()) ? icon96
                                                    : !icon128.equals(R
                                                            .icons()
                                                            .unavailable128()) ? icon128
                                                            : !icon192.equals(R
                                                                    .icons()
                                                                    .unavailable192()) ? icon192
                                                                    : !icon256.equals(R
                                                                            .icons()
                                                                            .unavailable256()) ? icon256
                                                                            : R.icons()
                                                                                    .unavailable32();
    // Woeha! autoformat awesomeness
  }
  @Override public ImageResource icon16()
  {
    return icon16;
  }
  @Override public ImageResource icon24()
  {
    return icon24;
  }
  @Override public ImageResource icon32()
  {
    return icon32;
  }
  @Override public ImageResource icon48()
  {
    return icon48;
  }
  @Override public ImageResource icon64()
  {
    return icon64;
  }
  @Override public ImageResource icon96()
  {
    return icon96;
  }
  @Override public ImageResource icon128()
  {
    return icon128;
  }
  @Override public ImageResource icon192()
  {
    return icon192;
  }
  @Override public ImageResource icon256()
  {
    return icon256;
  }
}