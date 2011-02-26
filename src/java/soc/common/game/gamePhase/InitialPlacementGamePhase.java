package soc.common.game.gamePhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.standard.BuildCity;
import soc.common.actions.gameAction.standard.BuildRoad;
import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.actions.gameAction.turns.GamePhaseHasEnded;
import soc.common.game.Game;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidgetFactory;

public class InitialPlacementGamePhase extends AbstractGamePhase
{
    private static final long serialVersionUID = -7144215557160903240L;
    private int actionCount = 0;

    @Override
    public void start(Game game)
    {
        // Expect each player to place town/road - town/road
        int i = 0;
        boolean back = false;

        // A loop going backward. Each index should be hit twice.
        // Example with 4 players: p1 - p2 - p3 - p4 - p4 - p3 - p2 - p1
        while (i > -1)
        {
            // If tournament starting rules are set, second building should be a
            // city
            if (back && game.getGameSettings().isTournamentStart())
            {
                // Tournament starting rules, add a city
                game.getActionsQueue().enqueue(
                        new BuildCity().setPlayer(game.getPlayers().get(i)),
                        true);
            }
            else
            {
                // Normal starting rules, add two towns
                game.getActionsQueue().enqueue(
                        new BuildTown().setPlayer(game.getPlayers().get(i)),
                        true);
            }

            // This action actually might be a BuildShipAction too.
            // TODO: implement this somewhere
            game.getActionsQueue().enqueue(
                    new BuildRoad().setPlayer(game.getPlayers().get(i)), true);

            // if the "back" flag is set, we should decrease the counter
            if (back)
            {
                i--;
            }
            else
            {
                i++;
            }

            // flip the flag when counter reaches maximum value
            // (maximum value equals amount of players)
            if (i == game.getPlayers().size())
            {
                // next loop is walked with same maximum value
                i--;

                // switch flag
                back = true;
            }
        }

        // When in tournament phase, every player may build a third road
        if (game.getGameSettings().isTournamentStart())
        {
            for (int j = game.getPlayers().size(); j > 0; j++)
            {
                game.getActionsQueue().enqueue(
                        new BuildRoad().setPlayer(game.getPlayers().get(i)),
                        true);
            }
        }

        // Set end of phase
        game.getActionsQueue().enqueue(
                (GameAction) new GamePhaseHasEnded().setSender(0), true);
    }

    @Override
    public void performAction(GameAction gameAction, Game game)
    {
        gameAction.perform(game);
        actionCount++;
        if (actionCount < (game.getPlayers().size() * 4))
        {
            if (actionCount % 2 == 0)
            {
                game.advanceTurn();
            }
        }
    }

    @Override
    public GamePhase next(Game game)
    {
        return new PlayTurnsGamePhase();
    }

    @Override
    public String getMessage()
    {
        // TODO fix message
        return "Place initial towns and roads";
    }

    @Override
    public boolean isInitialPlacement()
    {
        return true;
    }

    @Override
    public GamePhaseStatusWidget createGamePhaseStatusWidget(
            GamePhaseStatusWidgetFactory factory)
    {
        return factory.createInitialPlacementStatusWidget(this);
    }
}