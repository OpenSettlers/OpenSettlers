package soc.common.server;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.bots.BotPrincipalImpl;
import soc.common.game.Game;
import soc.common.server.actions.ServerActionFactory;
import soc.common.server.randomization.ClientRandom;

import com.google.gwt.user.client.Timer;

public class HotSeatServer extends AbstractGameServer
{
    int botActionDelay = 2000; // ms
    private List<GameAction> botActions = new ArrayList<GameAction>();
    private Timer timer = new Timer()
    {
        @Override
        public void run()
        {
            processNextBotAction();
        }
    };

    public HotSeatServer(GameServerCallback callback)
    {
        super();
        this.callback = callback;

        game = new Game();
        random = new ClientRandom();
        timer.scheduleRepeating(botActionDelay);
    }

    private void processNextBotAction()
    {
        if (botActions.size() > 0)
        {
            GameAction botAction = botActions.get(0);
            botActions.remove(0);
            super.sendAction(botAction);
        }
    }

    @Override
    public JoinResult join(UserCredentials credentials)
    {
        return null;
    }

    @Override
    public void leave()
    {
    }

    @Override
    public soc.common.server.randomization.Random getRandom()
    {
        return random;
    }

    @Override
    public ServerActionFactory createActionFactory()
    {
        return new ServerActionFactory();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.server.AbstractGameServer#sendAction(soc.common.actions.gameAction
     * .GameAction)
     */
    @Override
    public void sendAction(GameAction action)
    {
        // When the action comes from a bot, schedule it to add a delay
        if (action.getPlayer().getBot() != null)
            botActions.add(action);
        else
            super.sendAction(action);
    }

    @Override
    public void startGame(Game game)
    {
        this.game = game;
        botPrincipal = new BotPrincipalImpl(this);
    }

    @Override
    public void setBotTurnHandled(boolean handled)
    {
        botTurnHandled = handled;
    }
}
