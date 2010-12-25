package soc.common.board.rules;

import soc.common.board.Board;

/*
 * Reports an error if the board does not have a name
 */
public class NoNameDesignRule implements DesignRule
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
        if (b.getBoardSettings().getName() != null)
        {
            if (b.getBoardSettings().getName().length() > 0)
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
