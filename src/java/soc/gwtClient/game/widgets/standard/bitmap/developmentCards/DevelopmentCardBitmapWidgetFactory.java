package soc.gwtClient.game.widgets.standard.bitmap.developmentCards;

import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.standard.Monopoly;
import soc.common.game.developmentCards.standard.RoadBuilding;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.common.game.developmentCards.standard.YearOfPlenty;
import soc.gwtClient.game.abstractWidgets.DevelopmentCardWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.abstractWidgets.factories.DevelopmentCardWidgetFactory;

public class DevelopmentCardBitmapWidgetFactory implements
        DevelopmentCardWidgetFactory
{

    @Override
    public DevelopmentCardWidget createWidget(DevelopmentCard devCard,
            IGamePanel gamePanel)
    {
        if (devCard instanceof Soldier)
        {
            return new PlaySoldierWidget((Soldier) devCard, gamePanel);
        }
        if (devCard instanceof YearOfPlenty)
        {
            return new PlayYearOfPlentyWidget((YearOfPlenty) devCard, gamePanel);
        }
        if (devCard instanceof Monopoly)
        {
            return new PlayMonopolyWidget((Monopoly) devCard, gamePanel);
        }
        if (devCard instanceof RoadBuilding)
        {
            return new PlayRoadBuildingWidget((RoadBuilding) devCard, gamePanel);
        }
        if (devCard instanceof VictoryPoint)
        {
            return new PlayVictoryPointWidget((VictoryPoint) devCard, gamePanel);
        }

        return null;
    }

}
