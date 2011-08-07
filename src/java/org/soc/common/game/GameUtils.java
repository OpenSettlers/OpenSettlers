package org.soc.common.game;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.bots.Bot;
import org.soc.common.bots.IdiotBot;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.server.randomization.Random;


public class GameUtils
{
    private static List<Class<? extends Bot>> bots = new ArrayList<Class<? extends Bot>>();

    static
    {
        bots.add(IdiotBot.class);
    }

    public static List<Class<? extends Bot>> getAllBots()
    {
        return bots;
    }

    public static Bot createBot(Class<? extends Bot> botType, Game game,
            GamePlayer player, Random random)
    {
        if (botType == IdiotBot.class)
            return new IdiotBot(game, player, random);

        throw new AssertionError("Expected known bot type");
    }
}
