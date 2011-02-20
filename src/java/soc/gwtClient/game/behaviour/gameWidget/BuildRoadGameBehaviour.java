package soc.gwtClient.game.behaviour.gameWidget;

import soc.common.actions.gameAction.standard.BuildRoad;
import soc.common.board.pieces.Road;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.behaviour.gameBoard.BuildRoadBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class BuildRoadGameBehaviour implements GameBehaviour,
        GameBehaviourCallback, TradeFirst
{
    private BuildRoad buildRoad;
    private GameWidget gamePanel;
    private BuildRoadBehaviour buildRoadGameBoardBehaviour;

    public BuildRoadGameBehaviour(GameWidget gamePanel, BuildRoad buildRoad)
    {
        super();
        this.buildRoad = buildRoad;
        buildRoadGameBoardBehaviour = new BuildRoadBehaviour(buildRoad, this);
        this.gamePanel = gamePanel;
    }

    @Override
    public void finish()
    {
        buildRoadGameBoardBehaviour.setNeutral(gamePanel.getBoardVisualWidget()
                .getBoardVisual());
    }

    @Override
    public void start(GameWidget gamePanel)
    {
        GamePlayer player = gamePanel.getPlayingPlayer();
        Road road = new Road();

        if (gamePanel.getGame().getCurrentPhase() instanceof PlayTurnsGamePhase)
        {
            if (road.canPay(player))
            {
                gamePanel.getBoardVisualWidget().getBoardVisual().setBehaviour(
                        buildRoadGameBoardBehaviour);
            }
            else
            {
                gamePanel.getBankTradeUI().setPieceToTradeFor(road, this);
            }
        }
        else
        {
            gamePanel.getBoardVisualWidget().getBoardVisual().setBehaviour(
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
        buildRoadGameBoardBehaviour.setNeutral(gamePanel.getBoardVisualWidget()
                .getBoardVisual());
    }

    @Override
    public void onTraded()
    {
        gamePanel.getBoardVisualWidget().getBoardVisual().setBehaviour(
                buildRoadGameBoardBehaviour);
    }

    @Override
    public void onCancelTrade()
    {
        finish();
    }

}
