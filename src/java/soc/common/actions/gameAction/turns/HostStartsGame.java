package soc.common.actions.gameAction.turns;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;
import soc.common.server.actions.GameServerActionFactory;
import soc.common.server.actions.ServerAction;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.meta.Graphics;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import soc.common.views.widgetsInterface.generic.ToolTip;

public class HostStartsGame extends AbstractGameAction
{
    private static final long serialVersionUID = 4729872692877969851L;
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
    private Game game;

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#perform(soc.common.game
     * .Game)
     */
    @Override
    public void perform(Game game)
    {
        game.initialize();
        game.start();
        game.getActionsQueue().enqueue(
                (GameAction) new GamePhaseHasEnded().setSender(0), true);

        super.perform(game);
    }

    /**
     * @return the game
     */
    public Game getGame()
    {
        return game;
    }

    /**
     * @param game
     *            the game to set
     */
    public HostStartsGame setGame(Game game)
    {
        this.game = game;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#isAllowed(soc.common.game.gamePhase
     * .GamePhase)
     */
    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isLobby();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.GameAction#isAllowed(soc.common.game.gamePhase
     * .turnPhase.TurnPhase)
     */
    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return false;
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
        return gameBehaviourFactory.createHostStartsBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createHostStartsBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createHostStartsBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createHostStartsBehaviour(this);
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
     * soc.common.actions.gameAction.AbstractGameAction#createServerAction(soc
     * .common.server.actions.ServerActionFactory)
     */
    @Override
    public ServerAction createServerAction(GameServerActionFactory factory)
    {
        return factory.createHostStartsGameServerAction(this);
    }

}
