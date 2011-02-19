package soc.gwtClient.game.behaviour.gamePanel;

import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.board.pieces.City;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.behaviour.gameBoard.BuildCityBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class BuildCityGameBehaviour implements GameBehaviour,
        GameBehaviourCallback, TradeFirst
{
    private BuildCityBehaviour buildCityBehaviour;
    private BuildCity buildCity;
    private GameWidget gamePanel;
    private City city = new City();

    public BuildCityGameBehaviour(BuildCity buildCity, GameWidget gamePanel)
    {
        super();
        this.buildCity = buildCity;
        this.gamePanel = gamePanel;

        buildCityBehaviour = new BuildCityBehaviour(buildCity, gamePanel
                .getPlayingPlayer(), this);
    }

    @Override
    public void finish()
    {
    }

    @Override
    public void start(GameWidget gamePanel)
    {
        GamePlayer player = gamePanel.getPlayingPlayer();
        if (player.getResources().hasAtLeast(city.getCost()))
        {
            gamePanel.getBoardVisualWidget().getBoardVisual().setBehaviour(
                    buildCityBehaviour);
        }
        else
        {
            gamePanel.getBankTradeUI().setPieceToTradeFor(city, this);
        }
    }

    @Override
    public void cancel()
    {
    }

    @Override
    public void done()
    {
        gamePanel.sendAction(buildCity);
        buildCityBehaviour.setNeutral(gamePanel.getBoardVisualWidget()
                .getBoardVisual());
    }

    @Override
    public void onCancelTrade()
    {
    }

    @Override
    public void onTraded()
    {
        gamePanel.getBoardVisualWidget().getBoardVisual().setBehaviour(
                buildCityBehaviour);
    }
}
