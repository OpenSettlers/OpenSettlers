package org.soc.common.game.bots;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.actions.BuildRoad;
import org.soc.common.game.actions.BuildTown;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.LooseCards;
import org.soc.common.game.actions.PlaceRobber;
import org.soc.common.game.actions.RobPlayer;
import org.soc.common.game.actions.RollDice;
import org.soc.common.game.trading.QueuedTradeResponse;
import org.soc.common.server.GameServer;

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
  public void handleActions();

  /* Thread unsafe implementation of a bot principal. Can be used in a hotseat game. */
  public class BotPrincipalImpl implements BotPrincipal
  {
    private GameServer gameServer;
    private Map<Bot, Bot> threadingBots = new HashMap<Bot, Bot>();

    public BotPrincipalImpl(GameServer gameServer)
    {
      super();
      this.gameServer = gameServer;
      // Map bots to their wrapper equivalents for threading
      // Simple start implementation, no threads needed so no wrapped thread
      // bot needed
      for (GamePlayer player : gameServer.game().players())
        if (player.bot() != null)
          threadingBots.put(player.bot(), player.bot());
    }
    @Override public void handleActionsQueue()
    {
      List<GameAction> botActions = gameServer.game().actionsQueue()
              .pendingBotActions();
      // Call each bot's handler for the action
      for (GameAction botAction : botActions)
        callBotActionHandler(botAction);
    }
    private void callBotActionHandler(GameAction queuedAction)
    {
      // The action compiled by the bot to execute
      GameAction answerAction = null;
      // Reference to the wrapped bot
      Bot bot = threadingBots.get(queuedAction.player().bot());
      if (queuedAction instanceof LooseCards)
        answerAction = bot.looseCards(((LooseCards) queuedAction)
                .getAmountCardsToLoose());
      if (queuedAction instanceof PlaceRobber)
        answerAction = bot.placeRobber();
      if (queuedAction instanceof RobPlayer)
        answerAction = bot.robPlayer();
      if (queuedAction instanceof RollDice)
        answerAction = new RollDice().setPlayer(queuedAction.player());
      if (queuedAction instanceof BuildTown)
      {
        if (queuedAction.player().towns().size() == 0)
          answerAction = bot.pickFirstTown();
        else
          answerAction = bot.pickSecondTown();
      }
      if (queuedAction instanceof BuildRoad)
      {
        if (queuedAction.player().roads().size() == 0)
          answerAction = bot.pickFirstRoad();
        else
          answerAction = bot.pickSecondRoad();
      }
      if (queuedAction instanceof QueuedTradeResponse)
        answerAction = bot.respondToTrade(gameServer.game()
                .turn().getTradeOffers().getLatestOffer());
      // Execute the action
      performAction(answerAction);
    }
    @Override public void performAction(GameAction action)
    {
      gameServer.sendAction(action);
    }
    @Override public void handleActions()
    {
      GamePlayer playerOnTurn = gameServer.game().turn()
              .player();
      if (playerOnTurn.bot() != null)
        playerOnTurn.bot().handTurn(this);
    }
  }
}
