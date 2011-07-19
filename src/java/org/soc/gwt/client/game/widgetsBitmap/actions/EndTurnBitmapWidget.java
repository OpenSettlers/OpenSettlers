package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.actions.gameAction.turns.AbstractTurnAction;
import org.soc.common.actions.gameAction.turns.EndTurn;
import org.soc.common.game.GamePhaseChangedEvent;
import org.soc.common.game.GamePhaseChangedEventHandler;
import org.soc.common.game.TurnChangedEvent;
import org.soc.common.game.TurnChangedEventHandler;
import org.soc.common.game.phases.turnPhase.TurnPhaseChangedEvent;
import org.soc.common.game.phases.turnPhase.TurnPhaseChangedHandler;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actions.AbstractActionWidget;
import org.soc.gwt.client.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class EndTurnBitmapWidget extends AbstractActionWidget implements
        GamePhaseChangedEventHandler, TurnChangedEventHandler,
        TurnPhaseChangedHandler
{
    public PushButton btnEndTurn = new PushButton(new Image(Resources.icons()
            .endTurn32()));
    private EndTurn endTurn = new EndTurn();

    public EndTurnBitmapWidget(final GameWidget gameWidget,
            final GamePlayer player)
    {
        super(gameWidget, player);

        endTurn.setPlayer(player);

        gameWidget.getGame().addTurnChangedEventHandler(this);
        gameWidget.getGame().addGamePhaseChangedEventHandler(this);
        gameWidget.getGame().addTurnPhaseChangedHandler(this);

        btnEndTurn.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                gameWidget.startAction((AbstractTurnAction) new EndTurn()
                        .setPlayer(player));
            }
        });
    }

    @Override
    public Widget asWidget()
    {
        return btnEndTurn;
    }

    @Override
    protected void updateEnabled()
    {
        checkEnabled();
    }

    private void enableUI()
    {
        btnEndTurn.setEnabled(true);
    }

    private void disableUI()
    {
        btnEndTurn.setEnabled(false);
    }

    private void checkEnabled()
    {
        if (enabled && player.isOnTurn())
        {
            if (gameWidget.getGame().isAllowed(endTurn))
            {
                enableUI();
                return;
            }
        }

        disableUI();
    }

    @Override
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        checkEnabled();
    }

    @Override
    public void onTurnChanged(TurnChangedEvent event)
    {
        checkEnabled();
    }

    @Override
    public void onTurnPhaseChanged(TurnPhaseChangedEvent event)
    {
        checkEnabled();
    }
}
