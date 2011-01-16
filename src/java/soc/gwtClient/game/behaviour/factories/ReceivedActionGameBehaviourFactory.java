package soc.gwtClient.game.behaviour.factories;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.HostStartsGame;
import soc.common.actions.gameAction.turnActions.standard.ClaimVictory;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.behaviour.StartGameGameBehaviour;
import soc.gwtClient.game.behaviour.received.GameOverGameBehaviour;
import soc.gwtClient.game.behaviour.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.received.RollDiceResult;

public class ReceivedActionGameBehaviourFactory implements
        ReceiveGameBehaviourFactory
{
    private GamePanel gamePanel;

    public ReceivedActionGameBehaviourFactory(GamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public ReceiveGameBehaviour createBehaviour(GameAction gameAction)
    {
        if (gameAction instanceof RollDice)
        {
            if (gamePanel.getGame().getCurrentPhase() instanceof DetermineFirstPlayerGamePhase)
            {

            }
            if (gamePanel.getGame().getCurrentPhase() instanceof PlayTurnsGamePhase)
            {
                return new RollDiceResult(gamePanel, (RollDice) gameAction);
            }
        }
        if (gameAction instanceof HostStartsGame)
        {
            return new StartGameGameBehaviour(gamePanel);
        }
        if (gameAction instanceof ClaimVictory)
        {
            return new GameOverGameBehaviour((ClaimVictory) gameAction,
                    gamePanel);
        }

        return null;
    }
}
