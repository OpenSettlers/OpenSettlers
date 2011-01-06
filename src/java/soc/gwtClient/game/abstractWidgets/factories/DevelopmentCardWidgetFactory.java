package soc.gwtClient.game.abstractWidgets.factories;

import soc.common.game.developmentCards.DevelopmentCard;
import soc.gwtClient.game.abstractWidgets.DevelopmentCardWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;

/*
 * Creates a Development card widget from a development card instance
 */
public interface DevelopmentCardWidgetFactory
{
    public DevelopmentCardWidget createWidget(DevelopmentCard devCard,
            GamePanel gamePanel);
}
