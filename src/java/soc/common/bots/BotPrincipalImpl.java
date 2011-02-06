package soc.common.bots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.LooseCards;
import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
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
            threadingBots.put(player.getBot(), player.getBot());
    }

    @Override
    public void handleActionsQueue()
    {
        // Grab the blocking game actions in the queue
        List<GameAction> blockingActions = gameServer.getGame()
                .getActionsQueue().getBlockingActions();

        // Compile a list of actions to be performed by bots
        List<GameAction> botActions = new ArrayList<GameAction>();
        for (GameAction action : blockingActions)
            if (action.getPlayer().getBot() != null)
                botActions.add(action);

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

        // Execute the action
        performAction(answerAction);
    }

    @Override
    public void performAction(GameAction action)
    {
        gameServer.sendAction(action);
    }

}