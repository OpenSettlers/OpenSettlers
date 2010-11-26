package soc.common.client.behaviour.game;

import soc.common.client.behaviour.IInteractionBehaviour;
import soc.common.client.visuals.game.IGameVisual;

public interface IGameBehaviour extends IInteractionBehaviour
{
    public void start(IGameVisual gameVisual);
    public void setNeutral(IGameVisual visual);
}
