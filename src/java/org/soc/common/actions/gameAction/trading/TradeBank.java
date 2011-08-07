package org.soc.common.actions.gameAction.trading;

import org.soc.common.actions.gameAction.turns.AbstractTurnAction;
import org.soc.common.board.resources.Resource;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.game.Game;
import org.soc.common.game.phases.GamePhase;
import org.soc.common.game.phases.turnPhase.TurnPhase;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.behaviour.gameWidget.GameBehaviour;
import org.soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import org.soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import org.soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;
import org.soc.gwt.client.images.Resources;

public class TradeBank extends AbstractTurnAction
{
    private static final long serialVersionUID = 7756281155996246492L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().bankTrade16(),
                        Resources.icons().bankTrade32(), Resources.icons()
                                        .bankTrade48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getDescription()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };
    private ResourceList wantedResources;
    private ResourceList offeredResources;

    /** @return the wantedResources */
    public ResourceList getWantedResources()
    {
        return wantedResources;
    }

    /** @param wantedResources
     *            the wantedResources to set */
    public TradeBank setWantedResources(ResourceList wantedResources)
    {
        this.wantedResources = wantedResources;

        return this;
    }

    /** @return the offeredResources */
    public ResourceList getOfferedResources()
    {
        return offeredResources;
    }

    /** @param offeredResources
     *            the offeredResources to set */
    public TradeBank setOfferedResources(ResourceList offeredResources)
    {
        this.offeredResources = offeredResources;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.GameAction#isValid(org.soc.common.game.Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
            return false;

        if (offeredResources == null || wantedResources == null)
        {
            invalidMessage = "OfferedCards or WantedCards cannot be null";
            return false;
        }

        if (offeredResources.getNonTradeableResources().size() > 0)
        {
            invalidMessage = "You offer resources which are not tradeable";
            return false;
        }
        if (wantedResources.getNonTradeableResources().size() > 0)
        {
            invalidMessage = "You want resources which are not tradeable";
            return false;
        }

        if (offeredResources.size() == 0)
        {
            invalidMessage = "You need to offer at last one resource";
            return false;
        }

        if (wantedResources.size() == 0)
        {
            invalidMessage = "You need to desire at last one resource";
            return false;
        }

        player = game.getPlayerByID(sender);

        // check if the player has the offered cards in hand
        if (!player.getResources().hasAtLeast(offeredResources))
        {
            invalidMessage = "You don't have the resource available you are offering";
            return false;
        }

        for (Resource resource : game.getRules().getSupportedResources())
        {
            int amountOfType = offeredResources.ofType(resource).size();
            if (amountOfType > 0)
            {
                int amountNeeded = player.getPorts().amountNeededToTrade(
                                resource);
                if ((amountOfType % amountNeeded) != 0)
                {
                    invalidMessage = "For " + resource.getName()
                                    + " you need to offer a multiple of "
                                    + amountNeeded;
                    return false;
                }
            }
        }

        if (player.getPorts().amountGold(offeredResources) != wantedResources
                        .size())
        {
            invalidMessage = "Amount of desired resources should match offered resources divided by portdiveder";
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.GameAction#perform(org.soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        player = game.getPlayerByID(sender);

        player.getResources().subtractResources(offeredResources);
        player.getResources().addList(wantedResources);

        game.getBank().addList(offeredResources);
        game.getBank().subtractResources(wantedResources);

        message = player.getUser().getName() + " exchanges "
                        + offeredResources.toString() + " for "
                        + wantedResources.toString() + ".";

        super.perform(game);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase.isBuilding();
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isPlayTurns();
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

    @Override
    public ActionWidget createActionWidget(
                    ActionWidgetFactory actionWidgetFactory)
    {
        return actionWidgetFactory.createTradeBankWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createTradeBankBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createTradeBankBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createTradeBankBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createTradeBankBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.AbstractGameAction#createActionDetailWidget
     * (org.soc.common.ui.ActionDetailWidgetFactory)
     */
    @Override
    public ActionDetailWidget createActionDetailWidget(
                    ActionDetailWidgetFactory factory)
    {
        return factory.getTradeBankDetailWidget(this);
    }
}
