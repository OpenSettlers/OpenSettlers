package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.HostStartsGame;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.gwtClient.game.abstractWidgets.GamePanel;

public class StandardGameBoardBehaviourFactory implements GameBehaviourFactory
{
    private GamePanel gamePanel;

    public StandardGameBoardBehaviourFactory(GamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public GameBehaviour createBehaviour(TurnAction turnAction)
    {
        if (turnAction instanceof RollDice)
        {
            if (gamePanel.getGame().getCurrentPhase() instanceof DetermineFirstPlayerGamePhase)
            {

            }
            if (gamePanel.getGame().getCurrentPhase() instanceof PlayTurnsGamePhase)
            {
                return new RollDiceResult((RollDice) turnAction);
            }
        }
        if (turnAction instanceof BuildTown)
        {
            return new BuildTownGameBehaviour((BuildTown) turnAction, gamePanel);
        }
        if (turnAction instanceof BuildRoad)
        {
            return new BuildRoadGameBehaviour((BuildRoad) turnAction, gamePanel);
        }
        if (turnAction instanceof BuildCity)
        {
            return new BuildCityGameBehaviour((BuildCity) turnAction, gamePanel);
        }
        if (turnAction instanceof HostStartsGame)
        {
            return new StartGameGameBehaviour(gamePanel);
        }

        return null;
    }
}
