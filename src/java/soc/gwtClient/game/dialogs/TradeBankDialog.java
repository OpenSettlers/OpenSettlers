package soc.gwtClient.game.dialogs;

import soc.common.board.resources.ResourceList;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class TradeBankDialog extends DialogBox
{
    ResourceList giveResources = new ResourceList();
    ResourceList wantResources = new ResourceList();
    
    public TradeBankDialog()
    {
        setHTML("Trade with the bank");
        
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        setWidget(verticalPanel);
        verticalPanel.setSize("525px", "423px");
        
        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2.setSpacing(10);
        verticalPanel.add(horizontalPanel_2);
        
        Label lblTradeWithThe = new Label("Trade with the bank");
        horizontalPanel_2.add(lblTradeWithThe);
        
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setSpacing(10);
        verticalPanel.add(horizontalPanel);
        
        VerticalPanel verticalPanel_1 = new VerticalPanel();
        horizontalPanel.add(verticalPanel_1);
        
        Label lblWhatYouNeed = new Label("What you need");
        verticalPanel_1.add(lblWhatYouNeed);
        
        VerticalPanel verticalPanel_2 = new VerticalPanel();
        horizontalPanel.add(verticalPanel_2);
        
        Label lblWhatYouWant = new Label("What you want");
        verticalPanel_2.add(lblWhatYouWant);
        
        HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
        horizontalPanel_4.setSpacing(10);
        verticalPanel.add(horizontalPanel_4);
        
        Button button = new Button("New button");
        button.setText("Cancel");
        horizontalPanel_4.add(button);
        
        Button button_1 = new Button("New button");
        button_1.setText("Trade!");
        horizontalPanel_4.add(button_1);
        
        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1.setSpacing(10);
        verticalPanel.add(horizontalPanel_1);
        
        AbsolutePanel absolutePanel = new AbsolutePanel();
        horizontalPanel_1.add(absolutePanel);
        
        Image image = new Image("icons/24/Bank.png");
        absolutePanel.add(image);
        image.setSize("96", "96");
        
        Image image_1 = new Image("icons/48/City48.png");
        absolutePanel.add(image_1, 48, 48);
        image_1.setSize("48", "48");
        
        HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
        horizontalPanel_3.setSpacing(5);
        horizontalPanel_3.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel_1.add(horizontalPanel_3);
        
        Label lblTradeForA = new Label("Trade for a city");
        horizontalPanel_3.add(lblTradeForA);
    }

}
