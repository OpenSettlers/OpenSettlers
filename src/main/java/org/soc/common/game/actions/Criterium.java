package org.soc.common.game.actions;

import java.util.ArrayList;
import java.util.List;

public interface Criterium {
  public boolean is();

  public final static NotNull nutNull = new NotNull();

  public static class NotNull implements Criterium {
    @Override public boolean is() {
      // TODO Auto-generated method stub
      return false;
    }
  }

  public class Criteria {
    private List<Criterium> constraints = new ArrayList<Criterium>();

    public boolean are() {
      for (Criterium constraint : constraints) {
        if (constraint.is()) {
          return false;
        }
      }
      return true;
    }
  }
}
