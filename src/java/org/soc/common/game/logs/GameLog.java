package org.soc.common.game.logs;

import java.io.Serializable;
import java.util.List;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.RollDice;
import org.soc.common.game.Game;
import org.soc.common.game.player.GamePlayer;


import com.google.gwt.event.shared.HandlerRegistration;

/*
 * List of GameActions which occurred during a game
 */
public interface GameLog extends Iterable<GameAction>, Serializable
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
    public HandlerRegistration addActionPerformedEventHandler(
            ActionPerformedEventHandler handler);
}
