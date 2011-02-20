package soc.common.actions.gameAction;

import soc.common.core.Core;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.ToolTip;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;

/*
 * A generic message from the server. Currently used as error message
 * for debugging purposes. POssible future use is for example servers
 * rebooting.
 */
public class MessageFromServer extends AbstractGameAction
{
    private static final long serialVersionUID = -4467388096445126041L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, null, null);

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
    private String serverMessage;

    /**
     * @return the serverMessage
     */
    public String getServerMessage()
    {
        return serverMessage;
    }

    /**
     * @param serverMessage
     *            the serverMessage to set
     */
    public MessageFromServer setServerMessage(String serverMessage)
    {
        this.serverMessage = serverMessage;

        return this;
    }

    /*
     * Returns true: message from server is always allowed
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#isAllowed(soc.common.game.gamePhase
     * .turnPhase.TurnPhase)
     */
    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return true;
    }

    /*
     * Returns true: message from server is always allowed
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#isAllowed(soc.common.game.gamePhase
     * .GamePhase)
     */
    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return true;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

    @Override
    public GameBehaviour getNextActionBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getNextActionBehaviourFactory()
                .createMessageFromServerBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getOpponentReceiveBehaviourFactory()
                .createMessageFromServerBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getReceiveBehaviourFactory().createMessageFromServerBehaviour(
                        this);
    }

    @Override
    public GameBehaviour getSendBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getSendBehaviourFactory().createMessageFromServerBehaviour(
                        this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
