package org.soc.common.core;

import org.soc.common.core.GenericList.Model;
import org.soc.common.core.Props.FakeProperty;

/** Hah! a superinterface for all models! */
public class OpenZettlers {
  public interface OsModel<K>
          extends
          Model<K>,
          FakeProperty {}
}
