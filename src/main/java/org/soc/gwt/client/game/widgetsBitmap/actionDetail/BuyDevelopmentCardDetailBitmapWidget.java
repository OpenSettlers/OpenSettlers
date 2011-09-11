package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.game.actions.BuyDevelopmentCard;
import org.soc.common.game.actions.GameAction;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.user.client.ui.Image;

public class BuyDevelopmentCardDetailBitmapWidget extends
                AbstractActionDetailWidget
{
    private BuyDevelopmentCard buyDevelopmentCard;

    public BuyDevelopmentCardDetailBitmapWidget(GameWidget gameWidget,
                    BuyDevelopmentCard buyDevelopmentCard)
    {
        super(gameWidget, buyDevelopmentCard.player());
        this.buyDevelopmentCard = buyDevelopmentCard;

        rootPanel.add(new Image(R.mediumIcon(buyDevelopmentCard)));
    }

    @Override
    public GameAction getGameAction()
    {
        return buyDevelopmentCard;
    }
}
