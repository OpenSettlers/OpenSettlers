package soc.common.game.logs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.RolledSame;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;

import com.google.gwt.event.shared.SimpleEventBus;

public class GameLogImpl implements GameLog
{
    private static final long serialVersionUID = -2732262503732907260L;
    private List<GameAction> actions = new ArrayList<GameAction>();
    private transient SimpleEventBus eventBus;

    private void safelyFireEvent(ActionPerformedEvent actionPerformedEvent)
    {
        if (eventBus != null)
        {
            eventBus.fireEvent(actionPerformedEvent);
        }
    }

    private SimpleEventBus getEventBus()
    {
        if (eventBus == null)
        {
            eventBus = new SimpleEventBus();
        }

        return eventBus;
    }

    @Override
    public void addAction(GameAction inGameAction)
    {
        actions.add(inGameAction);

        safelyFireEvent(new ActionPerformedEvent(inGameAction));
    }

    public List<RollDice> getCurrentRoundRolls(Game game)
    {
        List<RollDice> result = new ArrayList<RollDice>();

        // We run the like a stack, first to examine is last in the list
        for (int i = actions.size() - 1; i > 0; i--)
        {
            GameAction action = actions.get(i);
            String x = action.getPlayer().getUser().getName();
            // we break the loop when we encountered a rolledsame action
            if (action instanceof RolledSame)
                break;

            // When we encounter a RollDiceAction, add it to the list
            if (action instanceof RollDice)
                result.add((RollDice) action);

            // We always have maximum of PlayerCount RollDiceAction
            if (result.size() == game.getPlayers().size())
                break;
        }

        return result;
    }

    public GamePlayer firstPlayerIsDetermined(Game game, int highRoll)
    {
        List<RollDice> rolledDices = getCurrentRoundRolls(game);

        // Get a list of all highrolls
        List<RollDice> highRolls = new ArrayList<RollDice>();
        for (RollDice rollDice : rolledDices)
        {
            if (rollDice.getDice().getDiceTotal() == highRoll)
                highRolls.add(rollDice);
        }

        // the player with highest dice is determined when we
        // have only one result
        if (highRolls.size() == 1)
        {
            return highRolls.get(0).getPlayer();
        }
        else
        {
            // return false
            return null;
        }
    }

    @Override
    public Iterator<GameAction> iterator()
    {
        return actions.iterator();
    }

    @Override
    public void addActionPerformedEventHandler(
            ActionPerformedEventHandler handler)
    {
        getEventBus().addHandler(ActionPerformedEvent.TYPE, handler);
    }
}
