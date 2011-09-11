package org.soc.common.game.actions;

import java.io.Serializable;
import java.util.Date;

import org.soc.common.utils.Util;
import org.soc.common.views.meta.Meta;

public interface Action extends Serializable, Meta {
  public String toDoMessage();
  public Date dateTimeExecuted();
  public int senderId();
  public Action setSender(int sender);
  public String message();

  public abstract class AbstractAction implements Action {
    protected String message = "No message implemented yet for " + name();
    protected Date dateTimeExecuted;
    protected int sender;

    public Date dateTimeExecuted() {
      return dateTimeExecuted;
    }
    @Override public String message() {
      return message;
    }
    @Override public String name() {
      return Util.shortName(this.getClass());
    }
    public int senderId() {
      return sender;
    }
    public Action setSender(int sender) {
      this.sender = sender;
      return this;
    }
  }
}
