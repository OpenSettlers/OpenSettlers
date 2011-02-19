package soc.gwtClient.game.behaviour.gameBoard.factories;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.gwtClient.game.behaviour.gameWidget.BuildCityGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.BuildRoadGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.BuildTownGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.BuyDevelopmentCardGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class SendGameBehaviourFactory implements GameBehaviourFactory
{
    private GameWidget gamePanel;

    public SendGameBehaviourFactory(GameWidget gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    @Override
    public GameBehaviour createBehaviour(GameAction gameAction)
    {
        if (gameAction instanceof BuildTown)
        {
            return new BuildTownGameBehaviour((BuildTown) gameAction, gamePanel);
        }
        if (gameAction instanceof BuildRoad)
        {
            return new BuildRoadGameBehaviour((BuildRoad) gameAction, gamePanel);
        }
        if (gameAction instanceof BuildCity)
        {
            return new BuildCityGameBehaviour((BuildCity) gameAction, gamePanel);
        }
        if (gameAction instanceof BuyDevelopmentCard)
        {
            return new BuyDevelopmentCardGameBehaviour(
                    (BuyDevelopmentCard) gameAction, gamePanel);
        }

        return null;
    }
}
