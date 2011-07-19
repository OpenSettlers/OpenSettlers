package soc.gwt.client.game.widgetsBitmap.developmentCards;

import soc.common.game.developmentCards.standard.Monopoly;
import soc.common.game.developmentCards.standard.RoadBuilding;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.common.game.developmentCards.standard.YearOfPlenty;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidgetFactory;
import soc.common.views.widgetsInterface.main.GameWidget;

public class DevelopmentCardBitmapWidgetFactory implements
        DevelopmentCardWidgetFactory
{
    private GameWidget gameWidget;

    public DevelopmentCardBitmapWidgetFactory(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;
    }

    @Override
    public DevelopmentCardWidget createMonopolyWidget(Monopoly monopoly)
    {
        return new PlayMonopolyWidget(gameWidget, monopoly);
    }

    @Override
    public DevelopmentCardWidget createRoadBuildingWidget(
            RoadBuilding roadBuilding)
    {
        return new PlayRoadBuildingWidget(gameWidget, roadBuilding);
    }

    @Override
    public DevelopmentCardWidget createSoldierWidget(Soldier soldier)
    {
        return new PlaySoldierWidget(gameWidget, soldier);
    }

    @Override
    public DevelopmentCardWidget createVictoryPointWidget(
            VictoryPoint victoryPoint)
    {
        return new PlayVictoryPointWidget(gameWidget, victoryPoint);
    }

    @Override
    public DevelopmentCardWidget createYearOfPlentyWidget(
            YearOfPlenty yearOfPlenty)
    {
        return new PlayYearOfPlentyWidget(gameWidget, yearOfPlenty);
    }

}