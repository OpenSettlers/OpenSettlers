package soc.gwtClient.game.behaviour.gameBoard.factories;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.gwtClient.game.behaviour.gamePanel.BuildRoadGameBehaviour;
import soc.gwtClient.game.behaviour.gamePanel.BuildTownGameBehaviour;
import soc.gwtClient.game.behaviour.gamePanel.GameBehaviour;
import soc.gwtClient.game.behaviour.gamePanel.MoveRobberGameBehaviour;
import soc.gwtClient.game.behaviour.gamePanel.RobPlayerGameBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class NextActionGameBehaviourFactory implements GameBehaviourFactory
{
    private GameWidget gamePanel;

    public NextActionGameBehaviourFactory(GameWidget gamePanel)
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
