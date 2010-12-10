package soc.common.game.logs;

import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.game.Game;
import soc.common.game.Player;

public interface IGameLog extends Iterable<GameAction>
{
    public void addAction(GameAction inGameAction);
    public List<RollDice> getCurrentRoundRolls(Game game);
    public Player firstPlayerIsDetermined(Game game, int highRoll);
}
