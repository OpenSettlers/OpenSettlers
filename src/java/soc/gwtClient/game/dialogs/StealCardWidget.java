package soc.gwtClient.game.dialogs;

import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.widgets.standard.bitmap.StealPlayerCardBitmapWidget;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;

/*
 * Displays a dialogbox showing a list of players and their resource cards faced down
 * When a player clicks a card, it only picked a player to steal a card from.
 * The actual stealing is a random function which is performed in at the server class.
 */
public class StealCardWidget extends DialogBox
{
    private GamePanel gamePanel;
    private GamePlayer player;
    HorizontalPanel playersCards = new HorizontalPanel();
    private RobPlayer robPlayer;

    public StealCardWidget(GamePanel gamePanel, GamePlayer player)
    {
        this();
        this.gamePanel = gamePanel;
        this.player = player;
        robPlayer.setPlayer(player);
        
        for (GamePlayer opponent : gamePanel.getGame().getPlayers())
        {
            // Don't steal from ourselves
            if (!opponent.equals(player))
            {
                playersCards.add(new StealPlayerCardBitmapWidget(player));
            }
        }
    }

    /**
     * @wbp.parser.constructor
     */
    public StealCardWidget()
    {
        setText("Steal a card from an opponent");
        setHTML("New dialog");
        
        VerticalPanel verticalPanel = new VerticalPanel();
        setWidget(verticalPanel);
        verticalPanel.setSize("100%", "100%");
        
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setSpacing(10);
        verticalPanel.add(horizontalPanel);
        
        Label lblPickACard = new Label("Pick a card from any player to steal it");
        horizontalPanel.add(lblPickACard);
        
        playersCards.setSpacing(10);
        verticalPanel.add(playersCards);
        
        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1.setSpacing(10);
        verticalPanel.add(horizontalPanel_1);
        
        Button button = new Button("New button");
        button.setText("Don't steal a card & be refreshing");
        horizontalPanel_1.add(button);
    }
    
    public void cardPicked(GamePlayer opponent)
    {
        robPlayer.setRobbedPlayer(opponent);
        gamePanel.startAction(robPlayer);
    }
}
