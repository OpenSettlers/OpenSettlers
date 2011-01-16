package soc.gwtClient.game.behaviour.factories;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.behaviour.BuildCityGameBehaviour;
import soc.gwtClient.game.behaviour.BuildRoadGameBehaviour;
import soc.gwtClient.game.behaviour.BuildTownGameBehaviour;
import soc.gwtClient.game.behaviour.BuyDevelopmentCardGameBehaviour;
import soc.gwtClient.game.behaviour.GameBehaviour;

public class SendGameBehaviourFactory implements GameBehaviourFactory
{
    private GamePanel gamePanel;

    public SendGameBehaviourFactory(GamePanel gamePanel)
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
