package org.soc.common.game.phases;

import java.io.Serializable;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.game.Game;
import org.soc.common.game.Turn;
import org.soc.common.views.meta.HasMeta;
import org.soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import org.soc.common.views.widgetsInterface.main.GamePhaseStatusWidgetFactory;


public interface GamePhase extends Serializable, HasMeta
{
    public void performAction(GameAction action, Game game);

    public void start(Game game);

    public GamePhase next(Game game);

    public Turn nextTurn(Game game);

    public boolean isAllowed(GameAction action);

    public String getName();

    public String getMessage();

    public boolean isLobby();

    public boolean isInitialPlacement();

    public boolean isPlayTurns();

    public boolean isEnded();

    public boolean isDetermineFirstPlayer();

    public boolean isPlacePorts();

    public GamePhaseStatusWidget createGamePhaseStatusWidget(
                    GamePhaseStatusWidgetFactory factory);
}