package soc.gwtClient.game.widgetsInterface.developmentCards;

import soc.common.game.developmentCards.standard.Monopoly;
import soc.common.game.developmentCards.standard.RoadBuilding;
import soc.common.game.developmentCards.standard.Soldier;
import soc.common.game.developmentCards.standard.VictoryPoint;
import soc.common.game.developmentCards.standard.YearOfPlenty;

/*
 * Creates a Development card widget from a development card instance
 */
public interface DevelopmentCardWidgetFactory
{
    public DevelopmentCardWidget createSoldierWidget(Soldier soldier);

    public DevelopmentCardWidget createVictoryPointWidget(
            VictoryPoint victoryPoint);

    public DevelopmentCardWidget createYearOfPlentyWidget(
            YearOfPlenty yearOfPlenty);

    public DevelopmentCardWidget createMonopolyWidget(Monopoly monopoly);

    public DevelopmentCardWidget createRoadBuildingWidget(
            RoadBuilding roadBuilding);
}
