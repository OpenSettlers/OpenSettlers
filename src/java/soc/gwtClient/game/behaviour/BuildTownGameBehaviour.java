package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.board.pieces.Town;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.visuals.behaviour.gameBoard.BuildTownBehaviour;

public class BuildTownGameBehaviour implements GameBehaviour,
        GameBehaviourCallback, TradeFirst
{
    private BuildTown buildTown;
    private BuildTownBehaviour buildTownGameBoardBehaviour;
    GamePanel gamePanel;
    private Town town = new Town();

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
        GamePlayer player = gamePanel.getPlayingPlayer();
        if (gamePanel.getGame().getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            if (player.getResources().hasAtLeast(town.getCost()))
            {
                gamePanel.getGameBoardVisual().setBehaviour(
                        buildTownGameBoardBehaviour);
            }
            else
            {
                gamePanel.getBankTradeUI().setPieceToTradeFor(town, this);
            }
        }
        else
        {
            gamePanel.getGameBoardVisual().setBehaviour(
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
        buildTownGameBoardBehaviour.setNeutral(gamePanel.getGameBoardVisual());
    }

    @Override
    public void onCancelTrade()
    {
        buildTownGameBoardBehaviour.setNeutral(gamePanel.getGameBoardVisual());
    }

    @Override
    public void onTraded()
    {
        gamePanel.getGameBoardVisual()
                .setBehaviour(buildTownGameBoardBehaviour);
    }
}
