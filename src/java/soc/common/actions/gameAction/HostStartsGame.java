package soc.common.actions.gameAction;

import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.logs.QueuedAction;
import soc.common.internationalization.I18n;

public class HostStartsGame extends AbstractGameAction
{
    private static final long serialVersionUID = 4729872692877969851L;
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
        game.getActionsQueue().enqueue(
                new QueuedAction().setAction(new GamePhaseHasEnded()
                        .setSender(0)));

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

}
