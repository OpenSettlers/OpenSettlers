package org.soc.common.board.rules;

import org.soc.common.board.Board;

/*
 * When a user finished designing a board, he must check it against a
 * set of rules. Such rules must implement this interface. A server saving a board
 * can set constraints for boards to be submittable.
 * 
 * Because of the many custom rules in the variants, it's necessary to be able to check
 * if a board meets certain criteria.
 */
public interface DesignRule
{
    public boolean invoke(Board b);
    public String getProblem();
    public RuleSeverity getSeverity();
}
