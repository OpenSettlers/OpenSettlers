package soc.common.board.rules;

import soc.common.board.Board;

/*
 * Reports an error if the board does not have a name
 */
public class NoNameDesignRule implements IDesignRule
{

    @Override
    public String getProblem()
    {
        return "Board does not have a name";
    }

    @Override
    public RuleSeverity getSeverity()
    {
        return RuleSeverity.ERROR;
    }

    @Override
    public boolean invoke(Board b)
    {
        if (b.getName() != null)
        {
            if (b.getName().length() > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

}
