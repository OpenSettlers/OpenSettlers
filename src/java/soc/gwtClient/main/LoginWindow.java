package soc.gwtClient.main;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class LoginWindow extends DialogBox
{

    public LoginWindow()
    {
        setHTML("Welcome");
        
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(10);
        setWidget(verticalPanel);
        verticalPanel.setSize("100%", "100%");
        
        Label lblHiThereColonist = new Label("Hi there, colonist!");
        verticalPanel.add(lblHiThereColonist);
        
        Label lblMakeUpSome = new Label("Make up some name");
        verticalPanel.add(lblMakeUpSome);
        
        //TextBox txtName = new TextBox();
        //verticalPanel.add(txtName);
    }

}
