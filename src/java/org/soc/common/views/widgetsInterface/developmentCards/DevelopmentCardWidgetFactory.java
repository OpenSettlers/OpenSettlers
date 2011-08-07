package org.soc.common.views.widgetsInterface.developmentCards;

import org.soc.common.game.developmentCards.standard.Monopoly;
import org.soc.common.game.developmentCards.standard.RoadBuilding;
import org.soc.common.game.developmentCards.standard.Soldier;
import org.soc.common.game.developmentCards.standard.VictoryPoint;
import org.soc.common.game.developmentCards.standard.YearOfPlenty;

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
