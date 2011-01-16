package soc.gwtClient.game.behaviour.factories;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.behaviour.BuildRoadGameBehaviour;
import soc.gwtClient.game.behaviour.BuildTownGameBehaviour;
import soc.gwtClient.game.behaviour.GameBehaviour;
import soc.gwtClient.game.behaviour.MoveRobberGameBehaviour;
import soc.gwtClient.game.behaviour.RobPlayerGameBehaviour;

public class NextActionGameBehaviourFactory implements GameBehaviourFactory
{
    private GamePanel gamePanel;

    public NextActionGameBehaviourFactory(GamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public GameBehaviour createBehaviour(GameAction gameAction)
    {
        if (gameAction instanceof BuildTown)
        {
            return new BuildTownGameBehaviour((BuildTown) gameAction, gamePanel);
        }
        if (gameAction instanceof BuildRoad)
        {
            return new BuildRoadGameBehaviour((BuildRoad) gameAction, gamePanel);
        }
        if (gameAction instanceof PlaceRobber)
        {
            return new MoveRobberGameBehaviour((PlaceRobber) gameAction,
                    gamePanel);
        }
        if (gameAction instanceof RobPlayer)
        {
            return new RobPlayerGameBehaviour(gamePanel);
        }
        return null;
    }

}
