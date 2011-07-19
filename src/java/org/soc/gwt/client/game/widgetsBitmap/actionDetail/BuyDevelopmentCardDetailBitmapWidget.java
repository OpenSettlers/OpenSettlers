package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.user.client.ui.Image;

public class BuyDevelopmentCardDetailBitmapWidget extends
                AbstractActionDetailWidget
{
    private BuyDevelopmentCard buyDevelopmentCard;

    public BuyDevelopmentCardDetailBitmapWidget(GameWidget gameWidget,
                    BuyDevelopmentCard buyDevelopmentCard)
    {
        super(gameWidget, buyDevelopmentCard.getPlayer());
        this.buyDevelopmentCard = buyDevelopmentCard;

        rootPanel.add(new Image(Resources.mediumIcon(buyDevelopmentCard)));
    }

    @Override
    public GameAction getGameAction()
    {
        return buyDevelopmentCard;
    }
}
