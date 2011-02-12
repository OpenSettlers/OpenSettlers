package soc.common.game.dices;

import java.io.Serializable;

import soc.common.server.random.Random;

public interface Dice extends Serializable
{
    public void roll(Random random);
}
