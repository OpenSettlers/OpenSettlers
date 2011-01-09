package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.visuals.behaviour.gameBoard.BuildTownBehaviour;

public class BuildTownGameBehaviour implements GameBehaviour,
        GameBehaviourCallback
{
    private BuildTown buildTown;
    private BuildTownBehaviour buildTownGameBoardBehaviour;
    GamePanel gamePanel;

    public BuildTownGameBehaviour(BuildTown buildTown, GamePanel gamePanel)
    {
        super();
        this.buildTown = buildTown;
        this.gamePanel = gamePanel;
        buildTownGameBoardBehaviour = new BuildTownBehaviour(buildTown, this);
    }

    @Override
    public void finish()
    {

    }

    @Override
    public void start(GamePanel gamePanel)
    {
        gamePanel.getGameBoardVisual()
                .setBehaviour(buildTownGameBoardBehaviour);
    }

    @Override
    public void cancel()
    {

    }

    @Override
    public void done()
    {
        gamePanel.sendAction(buildTown);
    }
}
