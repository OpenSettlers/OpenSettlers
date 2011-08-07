package org.soc.common.game.phases.setupStrategies;

import java.io.Serializable;

import org.soc.common.game.Game;


public interface InitialPlacementStrategy extends Serializable
{
    public void executeStrategy(Game game);
}
