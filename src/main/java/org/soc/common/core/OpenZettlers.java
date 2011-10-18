package org.soc.common.core;

import org.soc.common.core.GenericList.Model;
import org.soc.common.core.property.Properties.Description.HasDescription;
import org.soc.common.core.property.Properties.Name.HasName;
import org.soc.common.views.meta.Icon.HasIcon;

/** Hah! a superinterface for all models! */
public class OpenZettlers {
  public interface OsModel<K>
          extends
          Model<K>,
          HasName,
          HasIcon,
          HasDescription {}
}
