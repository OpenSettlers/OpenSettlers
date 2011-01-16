package soc.gwtClient.game.behaviour.received;

import soc.common.actions.gameAction.turnActions.standard.ClaimVictory;
import soc.gwtClient.game.abstractWidgets.GamePanel;

public class GameOverGameBehaviour implements ReceiveGameBehaviour
{
    ClaimVictory claimVictory;
    GamePanel gamePanel;

    public GameOverGameBehaviour(ClaimVictory claimVictory, GamePanel gamePanel)
    {
        super();
        this.claimVictory = claimVictory;
        this.gamePanel = gamePanel;
    }

    public ClaimVictory getClaimVictory()
    {
        return claimVictory;
    }

    @Override
    public void finish()
    {

    }

    @Override
    public void start(GamePanel gamePanel)
    {
        gamePanel.getGameOverDialog().update(this);
    }

    @Override
    public boolean endsManually()
    {
        return true;
    }

}
