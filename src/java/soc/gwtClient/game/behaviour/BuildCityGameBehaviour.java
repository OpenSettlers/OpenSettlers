package soc.gwtClient.game.behaviour;

import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.board.pieces.City;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.visuals.behaviour.gameBoard.BuildCityBehaviour;

public class BuildCityGameBehaviour implements GameBehaviour,
        GameBehaviourCallback, TradeFirst
{
    private BuildCityBehaviour buildCityBehaviour;
    private BuildCity buildCity;
    private GamePanel gamePanel;
    private City city = new City();

    public BuildCityGameBehaviour(BuildCity buildCity, GamePanel gamePanel)
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
    public void start(GamePanel gamePanel)
    {
        GamePlayer player = gamePanel.getPlayingPlayer();
        if (player.getResources().hasAtLeast(city.getCost()))
        {
            gamePanel.getGameBoardVisual().setBehaviour(buildCityBehaviour);
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
        buildCityBehaviour.setNeutral(gamePanel.getGameBoardVisual());
    }

    @Override
    public void onCancelTrade()
    {
    }

    @Override
    public void onTraded()
    {
        gamePanel.getGameBoardVisual().setBehaviour(buildCityBehaviour);
    }
}
