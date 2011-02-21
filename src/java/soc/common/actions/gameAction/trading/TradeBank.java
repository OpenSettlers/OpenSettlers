package soc.common.actions.gameAction.trading;

import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.core.Core;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.BuildingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.ui.ActionDetailWidgetFactory;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameBoard.factories.GameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.ReceiveGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.playerDetail.ActionDetailWidget;
import soc.gwtClient.images.Resources;

public class TradeBank extends AbstractTurnAction
{
    private static final long serialVersionUID = 7756281155996246492L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().bankTrade(), null,
                null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public Graphics graphics()
        {
            // TODO Auto-generated method stub
            return null;
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

        @Override
        public ToolTip createToolTip()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };
    private ResourceList wantedResources;
    private ResourceList offeredResources;

    /**
     * @return the wantedResources
     */
    public ResourceList getWantedResources()
    {
        return wantedResources;
    }

    /**
     * @param wantedResources
     *            the wantedResources to set
     */
    public TradeBank setWantedResources(ResourceList wantedResources)
    {
        this.wantedResources = wantedResources;

        return this;
    }

    /**
     * @return the offeredResources
     */
    public ResourceList getOfferedResources()
    {
        return offeredResources;
    }

    /**
     * @param offeredResources
     *            the offeredResources to set
     */
    public TradeBank setOfferedResources(ResourceList offeredResources)
    {
        this.offeredResources = offeredResources;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#isValid(soc.common.game.Game)
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
     * soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
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
        return turnPhase instanceof BuildingTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

    @Override
    public ActionWidget createActionWidget(GamePlayer player)
    {
        return Core.get().getClientFactory().getActionWidgetFactory(player)
                .createTradeBankWidget();
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
     * soc.common.actions.gameAction.AbstractGameAction#createActionDetailWidget
     * (soc.common.ui.ActionDetailWidgetFactory)
     */
    @Override
    public ActionDetailWidget createActionDetailWidget(
            ActionDetailWidgetFactory factory)
    {
        return factory.getTradeBankDetailWidget(this);
    }
}
