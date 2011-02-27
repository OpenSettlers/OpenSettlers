package soc.common.views.behaviour.gameWidget.beforeSend;

import soc.common.actions.gameAction.standard.BuildCity;
import soc.common.board.pieces.City;
import soc.common.game.player.GamePlayer;
import soc.common.views.behaviour.gameBoard.BuildCityBehaviour;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.GameBehaviourCallback;
import soc.common.views.widgetsInterface.main.GameWidget;

public class BuildCityGameBehaviour implements GameBehaviour,
        GameBehaviourCallback, TradeFirst
{
    private BuildCityBehaviour buildCityBehaviour;
    private BuildCity buildCity;
    private GameWidget gameWidget;
    private City city = new City();

    public BuildCityGameBehaviour(GameWidget gameWidget, BuildCity buildCity)
    {
        super();
        this.buildCity = buildCity;
        this.gameWidget = gameWidget;

        buildCityBehaviour = new BuildCityBehaviour(buildCity, gameWidget
                .getPlayingPlayer(), this);
    }

    @Override
    public void finish()
    {
    }

    @Override
    public void start(GameWidget gameWidget)
    {
        GamePlayer player = gameWidget.getPlayingPlayer();
        if (player.getResources().hasAtLeast(city.getCost()))
        {
            gameWidget.getBoardVisualWidget().getBoardVisual().setBehaviour(
                    buildCityBehaviour);
        }
        else
        {
            gameWidget.getBankTradeDialog().setPieceToTradeFor(city, this);
        }
    }

    @Override
    public void cancel()
    {
    }

    @Override
    public void done()
    {
        gameWidget.sendAction(buildCity);
        buildCityBehaviour.setNeutral(gameWidget.getBoardVisualWidget()
                .getBoardVisual());
    }

    @Override
    public void onCancelTrade()
    {
    }

    @Override
    public void onTraded()
    {
        gameWidget.getBoardVisualWidget().getBoardVisual().setBehaviour(
                buildCityBehaviour);
    }
}
