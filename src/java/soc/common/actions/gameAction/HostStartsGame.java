package soc.common.actions.gameAction;

import soc.common.core.Core;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;

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
        return gamePhase instanceof LobbyGamePhase;
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
    public ActionWidget createActionWidget(GamePlayer player)
    {
        return null;
    }

    @Override
    public GameBehaviour getNextActionBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getNextActionBehaviourFactory()
                .createHostStartsBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getOpponentReceiveBehaviourFactory()
                .createHostStartsBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getReceiveBehaviourFactory().createHostStartsBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getSendBehaviourFactory().createHostStartsBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}
