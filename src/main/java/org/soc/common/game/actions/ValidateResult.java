package org.soc.common.game.actions;

import java.io.Serializable;

/** A class to make literate validation logic where you can "return valid" or "return invalid" or
 * "return invalid("some invalidReason")". To use, add a static import. */
public interface ValidateResult extends Serializable {
  public String getInvalidReason();
  public boolean isValid();

  public static Valid valid = new Valid();
  public static Invalid invalid = new Invalid();

  public class Valid implements ValidateResult {
    @Override public String getInvalidReason() {
      return null;
    }
    @Override public boolean isValid() {
      return true;
    }
  }

  public class Invalid implements ValidateResult {
    private String invalidReason = "No reason given";

    public Invalid() {}
    public Invalid(String invalidReason) {
      this.invalidReason = invalidReason;
    }
    @Override public String getInvalidReason() {
      return invalidReason;
    }
    @Override public boolean isValid() {
      return false;
    }
    public Invalid cantBeNull() {
      invalidReason += " cannot be null";
      return this;
    }
    public static Invalid invalid(String reason) {
      return new Invalid(reason);
    }
    public static Invalid nullInvalid(String name) {
      return new Invalid(name);
    }
    public static boolean isNull(Object o) {
      return o == null;
    }
  }
}
