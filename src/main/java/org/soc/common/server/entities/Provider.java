package org.soc.common.server.entities;

import java.util.List;

public interface Provider<O extends Object> {
  public List<O> byName(String name);
  public O byId(int id);
}
