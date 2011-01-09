package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.visuals.behaviour.gameBoard.BuildRoadBehaviour;

public class BuildRoadGameBehaviour implements GameBehaviour,
        GameBehaviourCallback
{
    private BuildRoad buildRoad;
    private GamePanel gamePanel;
    private BuildRoadBehaviour buildRoadGameBoardBehaviour;

    public BuildRoadGameBehaviour(BuildRoad buildRoad, GamePanel gamePanel)
    {
        super();
        this.buildRoad = buildRoad;
        buildRoadGameBoardBehaviour = new BuildRoadBehaviour(buildRoad, this);
        this.gamePanel = gamePanel;
    }

    @Override
    public void finish()
    {
        buildRoadGameBoardBehaviour.setNeutral(gamePanel.getGameBoardVisual());
    }

    @Override
    public void start(GamePanel gamePanel)
    {
        gamePanel.getGameBoardVisual()
                .setBehaviour(buildRoadGameBoardBehaviour);
    }

    @Override
    public void cancel()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void done()
    {
        gamePanel.sendAction(buildRoad);
    }

}
