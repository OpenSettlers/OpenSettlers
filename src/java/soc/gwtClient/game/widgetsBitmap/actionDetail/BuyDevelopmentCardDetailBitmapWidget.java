package soc.gwtClient.game.widgetsBitmap.actionDetail;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import soc.gwtClient.game.widgetsAbstract.actionDetail.AbstractActionDetailWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.images.Resources;

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

        rootPanel.add(new Image(Resources.icons().buyDvelopmentCard()));
    }

    @Override
    public GameAction getGameAction()
    {
        return buyDevelopmentCard;
    }
}
