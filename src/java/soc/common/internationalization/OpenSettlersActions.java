package soc.common.internationalization;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

/*
 * Messages internationalization for all actions
 * TODO: replace all other string literals in the codebase with messages
 */
public interface OpenSettlersActions extends Messages
{
    @DefaultStringValue("{0} has built a town")
    String builtTown(String playerName);

    @DefaultStringValue("{0} ended turn")
    String endedTurn(String playerName);
}
