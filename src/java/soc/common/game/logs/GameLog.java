package soc.common.game.logs;

import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;

/*
 * List of GameActions which occurred during a game
 */
public interface GameLog extends Iterable<GameAction>
{
    /*
     * Adds an action to this gamelog
     */
    public void addAction(GameAction inGameAction);

    /*
     * Returns a list of RollDice actions performed during current dicerolling
     * round
     */
    public List<RollDice> getCurrentRoundRolls(Game game);

    /*
     * Returns the beginning player which won the dice rolling round if this
     * player exists, otherwise returns null
     */
    public GamePlayer firstPlayerIsDetermined(Game game, int highRoll);

    /*
     * Adds an actionPerformed event listener to this gamelog
     */
    public void addActionPerformedEventHandler(
            ActionPerformedEventHandler handler);
}
