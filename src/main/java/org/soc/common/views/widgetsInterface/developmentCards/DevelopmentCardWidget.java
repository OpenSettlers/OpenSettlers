package org.soc.common.views.widgetsInterface.developmentCards;

import org.soc.common.game.DevelopmentCard.Monopoly;
import org.soc.common.game.DevelopmentCard.RoadBuilding;
import org.soc.common.game.DevelopmentCard.Soldier;
import org.soc.common.game.DevelopmentCard.VictoryPoint;
import org.soc.common.game.DevelopmentCard.YearOfPlenty;

import com.google.gwt.user.client.ui.IsWidget;

public interface DevelopmentCardWidget extends IsWidget
{
  /* Creates a Development card widget from a development card instance */
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
}
