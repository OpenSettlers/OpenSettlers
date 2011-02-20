package soc.common.actions.gameAction.turns;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.trading.TradeOffer;
import soc.common.actions.gameAction.trading.TradePlayer;
import soc.common.core.Core;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.game.trading.TradeResponse;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.ToolTip;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.images.Resources;

public class QueuedTradeResponse extends AbstractGameAction implements
        TradeResponse
{
    private static final long serialVersionUID = 919585819869290645L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().trade(), null, null,
                null);

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

    @Override
    public TradeOffer getOriginatingOffer()
    {
        return null;
    }

    @Override
    public TradeResponse setOriginatingOffer(TradeOffer tradeOffer)
    {
        return null;
    }

    @Override
    public void setTradeResources(TradePlayer tradePlayer)
    {
    }

    @Override
    public String getToDoMessage()
    {
        return "Respond on the trade";
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return false;
    }

    @Override
    public ActionWidget createActionWidget(GamePlayer player)
    {
        return null;
    }

    @Override
    public GameBehaviour getNextActionBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getNextActionBehaviourFactory()
                .createQueuedTradeResponseBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getOpponentReceiveBehaviourFactory()
                .createQueuedTradeResponseBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour()
    {
        return null;
    }

    @Override
    public GameBehaviour getSendBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getSendBehaviourFactory().createQueuedTradeResponseBehaviour(
                        this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
