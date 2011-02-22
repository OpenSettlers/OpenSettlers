package soc.common.server;

import soc.common.actions.gameAction.GameAction;
import soc.common.bots.BotPrincipalImpl;
import soc.common.game.Game;
import soc.common.server.actions.ServerActionFactory;
import soc.common.server.randomization.ClientRandom;

import com.google.gwt.user.client.Timer;

/*
 * Server running n the browser to provide hotseat and playing versus bots possibility
 */
public class HotSeatServer extends AbstractGameServer
{
    int botActionDelay = 2000; // ms
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
        if (botActionQueue.size() > 0)
        {
            GameAction botAction = botActionQueue.get(0);
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
        return new ServerActionFactory(this);
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