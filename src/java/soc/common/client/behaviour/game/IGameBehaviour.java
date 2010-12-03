package soc.common.client.behaviour.game;

import soc.common.client.behaviour.IInteractionBehaviour;
import soc.common.client.visuals.game.IGameBoardVisual;

public interface IGameBehaviour extends IInteractionBehaviour
{
    public void start(IGameBoardVisual gameVisual);
    public void setNeutral(IGameBoardVisual visual);
}
