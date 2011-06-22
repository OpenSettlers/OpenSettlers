package soc.common.actions;

import java.io.Serializable;

public interface ValidateResult extends Serializable
{
    public String getInvalidReason();
    public boolean isValid();
}
