package soc.common.board.rules;

import soc.common.board.Board;

/*
 * When a user finished designing a board, he must check it against a 
 * set of rules. Such rules must implement this interface.
 */
public interface IDesignRule
{
    public boolean invoke(Board b);
    public String getProblem();
    public RuleSeverity getSeverity();
}
