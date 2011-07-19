package org.soc.common.actions.gameAction.turns;

import org.soc.common.actions.gameAction.AbstractGameAction;
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
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidgetFactory;

/*
 * Announces a gamephase which has been ended
 */
public class GamePhaseHasEnded extends AbstractGameAction
{
    private static final long serialVersionUID = 3377193429519428414L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, null, null);

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
    private GamePhase endedGamePhase;
    private GamePhase newPhase;

    /** @return the newPhase */
    public GamePhase getNewPhase()
    {
        return newPhase;
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
        endedGamePhase = game.getCurrentPhase();
        game.advanceGamePhase();
        newPhase = game.getCurrentPhase();

        // TODO: fix message
        message = endedGamePhase.getName() + " has ended, entering"
                        + newPhase.getName();

        super.perform(game);
    }

    /** @return the endedGamePhase */
    public GamePhase getEndedGamePhase()
    {
        return endedGamePhase;
    }

    /** @param endedGamePhase
     *            the endedGamePhase to set */
    public GamePhaseHasEnded setEndedGamePhase(GamePhase endedGamePhase)
    {
        this.endedGamePhase = endedGamePhase;

        return this;
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return true;
    }

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
    public ActionWidget createActionWidget(
                    ActionWidgetFactory actionWidgetFactory)
    {
        return null;
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createGamePhaseHasEndedBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory
                        .createGamePhaseHasEndedBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory
                        .createGamePhaseHasEndedBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createGamePhaseHasEndedBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
