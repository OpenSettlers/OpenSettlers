package soc.common.game.phases;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turns.GamePhaseHasEnded;
import soc.common.game.Game;
import soc.common.game.phases.setupStrategies.InitialPlacementStrategy;
import soc.common.game.phases.setupStrategies.TwoTowns;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidgetFactory;
import soc.gwtClient.images.Resources;

public class InitialPlacementGamePhase extends AbstractGamePhase
{
    private static final long serialVersionUID = -7144215557160903240L;
    private InitialPlacementStrategy placementStrategy = new TwoTowns();
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, Resources.icons()
                        .initialPlacementGamePhase32());

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

    /*
     * Empty default constructor
     */
    public InitialPlacementGamePhase()
    {
    }

    public InitialPlacementGamePhase(InitialPlacementStrategy strategy)
    {
        this.placementStrategy = strategy;
    }

    @Override
    public void start(Game game)
    {
        placementStrategy.executeStrategy(game);

        // Set end of phase
        game.getActionsQueue()
                        .enqueue((GameAction) new GamePhaseHasEnded()
                                        .setSender(0),
                                        true);
    }

    @Override
    public void performAction(GameAction gameAction, Game game)
    {
        gameAction.perform(game);

        // Advance turn when need to
        GameAction next = game.getActionsQueue().peek();

        if (!next.isServer()
                        && !next.getPlayer().equals(
                                        game.getCurrentTurn().getPlayer()))
            game.advanceTurn();
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

    /** @return the placementStrategy */
    public InitialPlacementStrategy getPlacementStrategy()
    {
        return placementStrategy;
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

}