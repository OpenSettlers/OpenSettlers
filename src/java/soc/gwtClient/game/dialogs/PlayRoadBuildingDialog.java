package soc.gwtClient.game.dialogs;

import soc.common.actions.gameAction.turnActions.standard.PlayDevelopmentCard;
import soc.common.game.developmentCards.RoadBuilding;
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

public class PlayRoadBuildingDialog extends DialogBox
{
    private RoadBuilding roadBuilding;
    private IGamePanel gamePanel;
    private PlayDevelopmentCard playDevelopmentCard = new PlayDevelopmentCard();
    
    public PlayRoadBuildingDialog(RoadBuilding roadBuilding, IGamePanel gamePanel)
    {
        this();
        this.roadBuilding = roadBuilding;
        this.gamePanel=gamePanel;
        
        playDevelopmentCard.setPlayer(gamePanel.getPlayingPlayer());
        playDevelopmentCard.setDevelopmentcard(roadBuilding);
    }

    /**
     * @wbp.parser.constructor
     */
    public PlayRoadBuildingDialog()
    {
        setText("Play Road building development card");
        setHTML("New dialog");
        
        VerticalPanel verticalPanel = new VerticalPanel();
        setWidget(verticalPanel);
        verticalPanel.setSize("100%", "100%");
        
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanel.add(horizontalPanel);
        
        Image image = new Image("icons/48/RoadBuilding48.png");
        horizontalPanel.add(image);
        
        Label lblRoadBuilding = new Label("Road building");
        horizontalPanel.add(lblRoadBuilding);
        
        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2.setSpacing(10);
        verticalPanel.add(horizontalPanel_2);
        
        Label lblBuildTwoRoads = new Label("Build two roads for free when playing this development card");
        horizontalPanel_2.add(lblBuildTwoRoads);
        lblBuildTwoRoads.setWidth("206px");
        
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
        
        Button btnPlayRoadBuilding = new Button("New button");
        btnPlayRoadBuilding.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent arg0) {
                gamePanel.startAction(playDevelopmentCard);
                hide();
            }
        });
        btnPlayRoadBuilding.setText("Play road building");
        horizontalPanel_1.add(btnPlayRoadBuilding);
    }
}
