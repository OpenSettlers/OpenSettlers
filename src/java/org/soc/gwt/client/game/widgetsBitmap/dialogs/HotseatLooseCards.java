package org.soc.gwt.client.game.widgetsBitmap.dialogs;

import java.util.HashMap;

import org.soc.common.actions.gameAction.standard.LooseCards;
import org.soc.common.actions.gameAction.standard.RollDice;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.game.player.GamePlayerList;
import org.soc.common.views.behaviour.gameWidget.received.RollDiceResult;
import org.soc.common.views.widgetsInterface.dialogs.HotseatLooseCardsDialog;
import org.soc.common.views.widgetsInterface.dialogs.LooseCardsWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsBitmap.main.LooseCardsBitmapWidget;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HotseatLooseCards extends DialogBox implements
        HotseatLooseCardsDialog
{
    private VerticalPanel panelPlayerCards;
    private GameWidget gameWidget;
    private HashMap<LooseCardsWidget, Boolean> lostCards = new HashMap<LooseCardsWidget, Boolean>();
    private HashMap<GamePlayer, LooseCardsWidget> playerWidgets = new HashMap<GamePlayer, LooseCardsWidget>();
    private Button btnDone;
    private RollDiceResult rollDiceResult;

    /**
     * @wbp.parser.constructor
     */
    public HotseatLooseCards()
    {
        setHTML("Players should loose cards");

        panelPlayerCards = new VerticalPanel();
        panelPlayerCards.setSpacing(10);
        setWidget(panelPlayerCards);
        panelPlayerCards.setSize("100%", "100%");

        btnDone = new Button("Done here");
        panelPlayerCards.add(btnDone);
        btnDone.setEnabled(false);
        btnDone.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                hide();
                for (LooseCardsWidget widget : lostCards.keySet())
                {
                    LooseCards looseCards = (LooseCards) new LooseCards()
                            .setLostCards(widget.getLostCards()).setPlayer(
                                    widget.getPlayer());
                    gameWidget.sendAction(looseCards);
                }
                rollDiceResult.doneLoosingCards();
            }
        });
    }

    public HotseatLooseCards(GameWidget gameWidget)
    {
        this();
        this.gameWidget = gameWidget;

        for (GamePlayer player : gameWidget.getGame().getPlayers())
        {
            LooseCardsWidget widget = new LooseCardsBitmapWidget(gameWidget,
                    player, this);
            panelPlayerCards.add(widget);
            playerWidgets.put(player, widget);
        }
    }

    public void update(RollDice rollDice, RollDiceResult rollDiceResult)
    {
        this.rollDiceResult = rollDiceResult;
        lostCards.clear();
        btnDone.setEnabled(false);

        GamePlayerList playersToLooseCards = new GamePlayerList();
        for (int playerID : rollDice.getLooserPlayers())
        {
            playersToLooseCards
                    .add(gameWidget.getGame().getPlayerByID(playerID));
        }
        for (GamePlayer player : playerWidgets.keySet())
        {
            if (playersToLooseCards.contains(player))
            {
                playerWidgets.get(player).setVisible(true);
                playerWidgets.get(player).update();
                lostCards.put(playerWidgets.get(player), false);
            }
            else
            {
                playerWidgets.get(player).setVisible(false);
            }
        }
        show();
    }

    @Override
    public void doneLoosingCards(LooseCardsWidget child)
    {
        lostCards.put(child, true);
        checkDone();
    }

    private void checkDone()
    {
        for (boolean isDone : lostCards.values())
        {
            if (!isDone)
            {
                btnDone.setEnabled(false);
                return;
            }
        }
        btnDone.setEnabled(true);
    }

}
