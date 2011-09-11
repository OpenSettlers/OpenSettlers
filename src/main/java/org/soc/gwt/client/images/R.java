package org.soc.gwt.client.images;

import org.soc.common.game.Chit;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.defaultTheme.Icons;
import org.soc.gwt.client.images.defaultTheme.Images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;

public class R
{
  private static Icons icons2 = GWT.create(Icons.class);
  private static Images images2 = GWT.create(Images.class);
  public static Icons icons = GWT.create(Icons.class);
  public static Images images = GWT.create(Images.class);

  public static Images images()
  {
    return images2;
  }
  public static Icons icons()
  {
    return icons2;
  }
  public static ImageResource smallIcon(Meta meta)
  {
    return meta.icon().icon16();
  }
  public static ImageResource mediumIcon(Meta meta)
  {
    return meta.icon().icon32();
  }
  public static ImageResource largeIcon(Meta meta)
  {
    return meta.icon().icon48();
  }
  public static ImageResource island(int id)
  {
    switch (id)
    {
      case 1:
        return icons.island132();
      case 2:
        return icons.island232();
      case 3:
        return icons.island332();
      case 4:
        return icons.island432();
      case 5:
        return icons.island532();
      case 6:
        return icons.island632();
    }
    return null;
  }
  public static ImageResource chit(Chit chit, int size)
  {
    switch (chit.number())
    {
      case 0:
        return icons.randomChit32();
      case 2:
        return icons.chit232();
      case 3:
        return icons.chit332();
      case 4:
        return icons.chit432();
      case 5:
        return icons.chit532();
      case 6:
        return icons.chit632();
      case 8:
        return icons.chit832();
      case 9:
        return icons.chit932();
      case 10:
        return icons.chit1032();
      case 11:
        return icons.chit1132();
      case 12:
        return icons.chit1232();
    }
    return null;
  }
}