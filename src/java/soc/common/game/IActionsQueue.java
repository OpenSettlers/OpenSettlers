package soc.common.game;

import soc.common.actions.gameAction.GameAction;

public interface IActionsQueue
{
    public void enqueue(GameAction inGameAction);
    public void dequeue();
    public boolean isExpected(GameAction action, Game game);
    public GameAction peek();
    public int size();
}
