package org.soc.common.actions;

/*
 * A valid result for validating
 */
public class Valid implements ValidateResult
{
    private static final long serialVersionUID = 7406983073499630911L;

    @Override
    public String getInvalidReason()
    {
        return null;
    }

    @Override
    public boolean isValid()
    {
        return true;
    }

}
