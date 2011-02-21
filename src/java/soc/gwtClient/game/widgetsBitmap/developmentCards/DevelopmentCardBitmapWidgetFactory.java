package soc.gwtClient.game.widgetsBitmap.developmentCards;

import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.standard.Monopoly;
import soc.common.game.developmentCards.standard.RoadBuilding;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.common.game.developmentCards.standard.YearOfPlenty;
import soc.gwtClient.game.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.gwtClient.game.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class DevelopmentCardBitmapWidgetFactory implements
        DevelopmentCardWidgetFactory
{

    @Override
    public DevelopmentCardWidget createWidget(DevelopmentCard devCard,
            GameWidget gameWidget)
    {
        if (devCard instanceof Soldier)
        {
            return new PlaySoldierWidget((Soldier) devCard, gameWidget);
        }
        if (devCard instanceof YearOfPlenty)
        {
            return new PlayYearOfPlentyWidget((YearOfPlenty) devCard, gameWidget);
        }
        if (devCard instanceof Monopoly)
        {
            return new PlayMonopolyWidget((Monopoly) devCard, gameWidget);
        }
        if (devCard instanceof RoadBuilding)
        {
            return new PlayRoadBuildingWidget((RoadBuilding) devCard, gameWidget);
        }
        if (devCard instanceof VictoryPoint)
        {
            return new PlayVictoryPointWidget((VictoryPoint) devCard, gameWidget);
        }

        return null;
    }

}
