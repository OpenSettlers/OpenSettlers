package soc.common.actions;

/*
 * Represents invalid state of a LobbyAction
 */
public class Invalid implements ValidateResult
{
    private String invalidReason = "No reason given";

    public Invalid(String invalidReason)
    {
        this.invalidReason = invalidReason;
    }

    @Override
    public String getInvalidReason()
    {
        return invalidReason;
    }

    @Override
    public boolean isValid()
    {
        return false;
    }
}