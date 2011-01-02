package soc.common.client.behaviour.game;

import soc.common.client.behaviour.InteractionBehaviour;
import soc.common.client.visuals.game.GameBoardVisual;

public interface GameBehaviour extends InteractionBehaviour
{
    public void start(GameBoardVisual gameVisual);
    public void setNeutral(GameBoardVisual visual);
}
