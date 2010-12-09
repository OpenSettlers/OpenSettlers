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
}
