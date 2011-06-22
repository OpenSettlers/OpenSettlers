package soc.common.game.phases.setupStrategies;

import java.io.Serializable;

import soc.common.game.Game;

public interface InitialPlacementStrategy extends Serializable
{
    public void executeStrategy(Game game);
}
