package soc.common.views.behaviour.gameWidget.beforeSend;

import soc.common.actions.gameAction.standard.BuildRoad;
import soc.common.board.pieces.Road;
import soc.common.game.player.GamePlayer;
import soc.common.views.behaviour.gameBoard.BuildRoadBehaviour;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.GameBehaviourCallback;
import soc.common.views.widgetsInterface.main.GameWidget;

public class BuildRoadGameBehaviour implements GameBehaviour,
        GameBehaviourCallback, TradeFirst
{
    private BuildRoad buildRoad;
    private GameWidget gameWidget;
    private BuildRoadBehaviour buildRoadGameBoardBehaviour;

    public BuildRoadGameBehaviour(GameWidget gameWidget, BuildRoad buildRoad)
    {
        super();
        this.buildRoad = buildRoad;
        buildRoadGameBoardBehaviour = new BuildRoadBehaviour(buildRoad, this);
        this.gameWidget = gameWidget;
    }

    @Override
    public void finish()
    {
        buildRoadGameBoardBehaviour.setNeutral(gameWidget
                .getBoardVisualWidget().getBoardVisual());
    }

    @Override
    public void start(GameWidget gameWidget)
    {
        GamePlayer player = gameWidget.getPlayingPlayer();
        Road road = new Road();

        if (gameWidget.getGame().getCurrentPhase().isPlayTurns())
        {
            if (road.canPay(player))
            {
                gameWidget.getBoardVisualWidget().getBoardVisual()
                        .setBehaviour(buildRoadGameBoardBehaviour);
            }
            else
            {
                gameWidget.getBankTradeUI().setPieceToTradeFor(road, this);
            }
        }
        else
        {
            gameWidget.getBoardVisualWidget().getBoardVisual().setBehaviour(
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
        gameWidget.sendAction(buildRoad);
        buildRoadGameBoardBehaviour.setNeutral(gameWidget
                .getBoardVisualWidget().getBoardVisual());
    }

    @Override
    public void onTraded()
    {
        gameWidget.getBoardVisualWidget().getBoardVisual().setBehaviour(
                buildRoadGameBoardBehaviour);
    }

    @Override
    public void onCancelTrade()
    {
        finish();
    }

}
