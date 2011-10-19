package org.soc.common.views.meta;

import org.soc.common.core.Props.HasGetters;

import com.google.gwt.resources.client.*;

public interface Icon {
  public interface HasIcon extends HasGetters {
    public Icon icon();
  }

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
