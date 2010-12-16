package soc.common.server.actions;

import soc.common.actions.gameAction.GameAction;

/*
 * Interface for actions needed to be performed on the server side,
 * like rolling dice, shuffling developmentcards stack
 */
public interface IServerAction
{
    public GameAction getAction();
    public void execute();
    
    // Returns the associated gameaction, but then it's data set so that
    // opponents don't have data they should not have.
    // For example, a BuyDevelopmentCard action's DevelopmentCard
    // should not be known to the opponents.
    public GameAction getOpponentAction();
}
