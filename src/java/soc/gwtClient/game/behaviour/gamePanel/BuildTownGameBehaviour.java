package soc.gwtClient.game.behaviour.gamePanel;

import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.board.pieces.Town;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.behaviour.gameBoard.BuildTownBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class BuildTownGameBehaviour implements GameBehaviour,
        GameBehaviourCallback, TradeFirst
{
    private BuildTown buildTown;
    private BuildTownBehaviour buildTownGameBoardBehaviour;
    GameWidget gamePanel;
    private Town town = new Town();

    public BuildTownGameBehaviour(BuildTown buildTown, GameWidget gamePanel)
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
    public void start(GameWidget gamePanel)
    {
        GamePlayer player = gamePanel.getPlayingPlayer();
        if (gamePanel.getGame().getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            if (player.getResources().hasAtLeast(town.getCost()))
            {
                gamePanel.getBoardVisualWidget().getBoardVisual().setBehaviour(
                        buildTownGameBoardBehaviour);
            }
            else
            {
                gamePanel.getBankTradeUI().setPieceToTradeFor(town, this);
            }
        }
        else
        {
            gamePanel.getBoardVisualWidget().getBoardVisual().setBehaviour(
                    buildTownGameBoardBehaviour);
        }
    }

    @Override
    public void cancel()
    {

    }

    @Override
    public void done()
    {
        gamePanel.sendAction(buildTown);
        buildTownGameBoardBehaviour.setNeutral(gamePanel.getBoardVisualWidget()
                .getBoardVisual());
    }

    @Override
    public void onCancelTrade()
    {
        buildTownGameBoardBehaviour.setNeutral(gamePanel.getBoardVisualWidget()
                .getBoardVisual());
    }

    @Override
    public void onTraded()
    {
        gamePanel.getBoardVisualWidget().getBoardVisual().setBehaviour(
                buildTownGameBoardBehaviour);
    }
}
