package soc.gwtClient.game.behaviour.gameBoard.factories;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.HostStartsGame;
import soc.common.actions.gameAction.MessageFromServer;
import soc.common.actions.gameAction.turnActions.standard.ClaimVictory;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.gwtClient.game.behaviour.gameBoard.opponent.DefaultOpponentReceivedBehaviour;
import soc.gwtClient.game.behaviour.gameBoard.received.GameOverGameBehaviour;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameBoard.received.RollDiceResult;
import soc.gwtClient.game.behaviour.gameWidget.ErrorReceivedGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.StartGameGameBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class ReceivedActionGameBehaviourFactory implements
        ReceiveGameBehaviourFactory
{
    private GameWidget gamePanel;

    public ReceivedActionGameBehaviourFactory(GameWidget gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
    }

    @Override
    public ReceiveGameBehaviour createBehaviour(GameAction gameAction)
    {
        if (gameAction.getPlayer().getBot() != null)
            return new DefaultOpponentReceivedBehaviour(gamePanel, gameAction);

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
        if (gameAction instanceof MessageFromServer)
        {
            return new ErrorReceivedGameBehaviour(
                    (MessageFromServer) gameAction);
        }

        return null;
    }
}
