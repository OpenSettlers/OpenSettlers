package soc.common.bots;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuildRoad;
import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.actions.gameAction.standard.LooseCards;
import soc.common.actions.gameAction.standard.PlaceRobber;
import soc.common.actions.gameAction.standard.RobPlayer;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.actions.gameAction.turns.QueuedTradeResponse;
import soc.common.game.player.GamePlayer;
import soc.common.server.GameServer;

/*
 * Thread unsafe implementation of a bot principal. Can be used in a hotseat game.
 */
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
        for (GamePlayer player : gameServer.getGame().getPlayers())
            if (player.getBot() != null)
                threadingBots.put(player.getBot(), player.getBot());
    }

    @Override
    public void handleActionsQueue()
    {
        List<GameAction> botActions = gameServer.getGame().getActionsQueue()
                .getPendingBotActions();

        // Call each bot's handler for the action
        for (GameAction botAction : botActions)
            callBotActionHandler(botAction);
    }

    private void callBotActionHandler(GameAction queuedAction)
    {
        // The action compiled by the bot to execute
        GameAction answerAction = null;

        // Reference to the wrapped bot
        Bot bot = threadingBots.get(queuedAction.getPlayer().getBot());

        if (queuedAction instanceof LooseCards)
            answerAction = bot.looseCards(((LooseCards) queuedAction)
                    .getAmountCardsToLoose());

        if (queuedAction instanceof PlaceRobber)
            answerAction = bot.placeRobber();

        if (queuedAction instanceof RobPlayer)
            answerAction = bot.robPlayer();

        if (queuedAction instanceof RollDice)
            answerAction = new RollDice().setPlayer(queuedAction.getPlayer());

        if (queuedAction instanceof BuildTown)
        {
            if (queuedAction.getPlayer().getTowns().size() == 0)
                answerAction = bot.pickFirstTown();
            else
                answerAction = bot.pickSecondTown();
        }

        if (queuedAction instanceof BuildRoad)
        {
            if (queuedAction.getPlayer().getRoads().size() == 0)
                answerAction = bot.pickFirstRoad();
            else
                answerAction = bot.pickSecondRoad();
        }

        if (queuedAction instanceof QueuedTradeResponse)
            answerAction = bot.respondToTrade(gameServer.getGame()
                    .getCurrentTurn().getTradeOffers().getLatestOffer());

        // Execute the action
        performAction(answerAction);
    }

    @Override
    public void performAction(GameAction action)
    {
        gameServer.sendAction(action);
    }

    @Override
    public void handleActions()
    {
        GamePlayer playerOnTurn = gameServer.getGame().getCurrentTurn()
                .getPlayer();
        if (playerOnTurn.getBot() != null)
            playerOnTurn.getBot().handTurn(this);
    }

}
