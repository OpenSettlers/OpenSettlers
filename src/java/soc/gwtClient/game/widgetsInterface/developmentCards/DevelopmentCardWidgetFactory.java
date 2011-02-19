package soc.gwtClient.game.widgetsInterface.developmentCards;

import soc.common.game.developmentCards.DevelopmentCard;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

/*
 * Creates a Development card widget from a development card instance
 */
public interface DevelopmentCardWidgetFactory
{
    public DevelopmentCardWidget createWidget(DevelopmentCard devCard,
            GameWidget gamePanel);
}
