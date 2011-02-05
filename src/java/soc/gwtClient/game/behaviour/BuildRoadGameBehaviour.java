package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.board.pieces.Road;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.visuals.behaviour.gameBoard.BuildRoadBehaviour;

public class BuildRoadGameBehaviour implements GameBehaviour,
        GameBehaviourCallback, TradeFirst
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
        GamePlayer player = gamePanel.getPlayingPlayer();
        Road road = new Road();

        if (gamePanel.getGame().getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            if (road.canPay(player))
            {
                gamePanel.getGameBoardVisual().setBehaviour(
                        buildRoadGameBoardBehaviour);
            }
            else
            {
                gamePanel.getBankTradeUI().setPieceToTradeFor(road, this);
            }
        }
        else
        {
            gamePanel.getGameBoardVisual().setBehaviour(
                    buildRoadGameBoardBehaviour);
        }
    }

    @Override
    public void cancel()
    {
    }

    @Override
    public void done()
    {
        gamePanel.sendAction(buildRoad);
        buildRoadGameBoardBehaviour.setNeutral(gamePanel.getGameBoardVisual());
    }

    @Override
    public void onTraded()
    {
        gamePanel.getGameBoardVisual()
                .setBehaviour(buildRoadGameBoardBehaviour);
    }

    @Override
    public void onCancelTrade()
    {
        finish();
    }

}
