package soc.common.actions.gameAction.seaFarers;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.board.ports.Port;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameBoard.factories.GameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.factories.ReceiveGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;

public class PlacePort extends AbstractGameAction
{
    private static final long serialVersionUID = -9211239639325281661L;
    private int territoryID;
    private Port port;

    /**
     * @return the port
     */
    public Port getPort()
    {
        return port;
    }

    /**
     * @param port
     *            the port to set
     */
    public PlacePort setPort(Port port)
    {
        this.port = port;
        return this;
    }

    /**
     * @return the territoryID
     */
    public int getTerritoryID()
    {
        return territoryID;
    }

    /**
     * @param territoryID
     *            the territoryID to set
     */
    public PlacePort setTerritoryID(int territoryID)
    {
        this.territoryID = territoryID;
        return this;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().placePortToDo(player.getUser().getName());
    }

    @Override
    public ActionWidget createActionWidget(GamePlayer player)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour getNextActionBehaviour(GameBehaviourFactory gameBehaviourFactory)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour getSendBehaviour(GameBehaviourFactory gameBehaviourFactory)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }
}