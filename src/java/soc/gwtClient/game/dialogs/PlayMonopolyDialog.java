package soc.gwtClient.game.dialogs;

import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.game.developmentCards.Monopoly;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class PlayMonopolyDialog extends DialogBox
{
    private Monopoly monopoly;
    private IGamePanel gamePanel;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    
    public PlayMonopolyDialog(Monopoly monopoly, IGamePanel gamePanel)
    {
        this();
        
        this.monopoly = monopoly;
        this.gamePanel=gamePanel;
        
        playDevelopmentCard.setPlayer(gamePanel.getPlayingPlayer());
        playDevelopmentCard.setDevelopmentcard(monopoly);
    }

    /**
     * @wbp.parser.constructor
     */
    public PlayMonopolyDialog()
    {
        setHTML("New dialog");
        
        VerticalPanel verticalPanel = new VerticalPanel();
        setWidget(verticalPanel);
        verticalPanel.setSize("195px", "115px");
        
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanel.add(horizontalPanel);
        
        Image image = new Image("icons/48/Monopoly48.png");
        horizontalPanel.add(image);
        
        Label lblMonopoly = new Label("Monopoly");
        horizontalPanel.add(lblMonopoly);
        
        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2.setSpacing(10);
        verticalPanel.add(horizontalPanel_2);
        
        Label lblPickAResource = new Label("Pick a resource type, then steal all resources of that type from every player");
        horizontalPanel_2.add(lblPickAResource);
        
        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1.setSpacing(10);
        verticalPanel.add(horizontalPanel_1);
        
        Button btnCancel = new Button("New button");
        btnCancel.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent arg0) {
                hide();
            }
        });
        btnCancel.setText("Cancel");
        horizontalPanel_1.add(btnCancel);
        
        Button btnPlayMonopoly = new Button("New button");
        btnPlayMonopoly.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent arg0) {
                gamePanel.startAction(playDevelopmentCard);
                hide();
            }
        });
        btnPlayMonopoly.setText("Play monopoly");
        horizontalPanel_1.add(btnPlayMonopoly);
    }
}
