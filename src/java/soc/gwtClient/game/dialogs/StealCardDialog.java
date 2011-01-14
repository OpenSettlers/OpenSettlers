package soc.gwtClient.game.dialogs;

import java.util.HashMap;

import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.game.player.GamePlayer;
import soc.common.game.player.GamePlayerList;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.behaviour.SoldierPlayBehaviour;
import soc.gwtClient.game.widgets.abstractWidgets.StealCardWidget;
import soc.gwtClient.game.widgets.abstractWidgets.StealPlayerCardWidget;
import soc.gwtClient.game.widgets.standard.bitmap.StealPlayerCardBitmapWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/*
 * Displays ui showing a list of players and their resource cards faced down
 * When a player clicks a card, it only picked a player to steal a card from.
 * The actual stealing is a random function which is performed in at the server class.
 */
public class StealCardDialog extends DialogBox implements StealCardWidget
{
    private GamePanel gamePanel;
    private GamePlayer player;
    private VerticalPanel playersCards = new VerticalPanel();
    private HashMap<GamePlayer, StealPlayerCardWidget> playerWidgets = new HashMap<GamePlayer, StealPlayerCardWidget>();
    private SoldierPlayBehaviour soldierPlayBehaviour;

    public StealCardDialog(GamePanel gamePanel, GamePlayer player)
    {
        this();
        this.gamePanel = gamePanel;
        this.player = player;

        // Add a widget for all players to the rootpanel
        for (GamePlayer playerr : gamePanel.getGame().getPlayers())
        {
            StealPlayerCardWidget widget = new StealPlayerCardBitmapWidget(
                    playerr, this);
            playersCards.add(widget);
            playerWidgets.put(playerr, widget);
        }
    }

    /**
     * @wbp.parser.constructor
     */
    public StealCardDialog()
    {
        setText("Steal a card from an opponent");
        setHTML("New dialog");

        VerticalPanel verticalPanel = new VerticalPanel();
        setWidget(verticalPanel);
        verticalPanel.setSize("100%", "100%");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setSpacing(10);
        verticalPanel.add(horizontalPanel);

        Label lblPickACard = new Label(
                "Pick a card from any player to steal it");
        horizontalPanel.add(lblPickACard);

        playersCards.setSpacing(10);
        verticalPanel.add(playersCards);

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1.setSpacing(10);
        verticalPanel.add(horizontalPanel_1);

        Button button = new Button("New button");
        button.setText("Don't steal a card & be refreshing");
        button.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                cardPicked(null);
            }
        });
        horizontalPanel_1.add(button);
    }

    @Override
    public void cardPicked(GamePlayer opponent)
    {
        RobPlayer robPlayer = (RobPlayer) new RobPlayer().setRobbedPlayer(
                opponent).setPlayer(player);
        soldierPlayBehaviour.robbedPlayer(robPlayer);
        hide();
    }

    @Override
    public void update(SoldierPlayBehaviour soldierPlayBehaviour,
            PlaceRobber placeRobber, GamePlayer player)
    {
        this.soldierPlayBehaviour = soldierPlayBehaviour;
        this.player = player;

        GamePlayerList robCandidates = gamePanel.getGame().getPlayersAtHex(
                placeRobber.getNewLocation(), placeRobber.getPlayer());

        // Only make the players' visible who can be robbed.
        for (GamePlayer playerr : playerWidgets.keySet())
        {
            if (robCandidates.contains(playerr))
            {
                playerWidgets.get(playerr).update(player);
                playerWidgets.get(playerr).setVisible(true);
            }
            else
            {
                playerWidgets.get(playerr).setVisible(false);
            }
        }
        show();
    }
}
