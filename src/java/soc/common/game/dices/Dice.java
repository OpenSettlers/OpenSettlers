package soc.common.game.dices;

import java.io.Serializable;

import soc.common.server.randomization.Random;

public interface Dice extends Serializable
{
    public void roll(Random random);
}
