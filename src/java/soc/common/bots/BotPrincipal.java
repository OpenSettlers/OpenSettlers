package soc.common.bots;

import soc.common.actions.gameAction.GameAction;

/*
 * Responsible for managing game-flow logic for bots
 * 
 * Represents logic for a bot to accept commands from. Ensures the bot is acting legal.
 * Acts as a proxy between the game server and the bots to prevent illegal actions from
 * executing.
 * 
 * When illegal actions are executed, those actions are logged such that they can be debugged.
 */
public interface BotPrincipal
{
    public void performAction(GameAction action);

    public void handleActionsQueue();

    public void handleTurn();
}
