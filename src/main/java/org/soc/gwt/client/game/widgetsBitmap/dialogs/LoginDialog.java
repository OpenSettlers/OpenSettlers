package org.soc.gwt.client.game.widgetsBitmap.dialogs;

import org.soc.common.server.Server.ServerService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginDialog extends DialogBox
{
  public LoginDialog()
  {
    setSize("533px", "513px");
    setHTML("Login/Register");
    VerticalPanel verticalPanel = new VerticalPanel();
    verticalPanel.setSpacing(10);
    setWidget(verticalPanel);
    verticalPanel.setSize("837px", "468px");
    Label lblToEnterTh = new Label(
            "To enter the lobby, only a name is needed.");
    verticalPanel.add(lblToEnterTh);
    HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
    horizontalPanel_2.setSpacing(5);
    horizontalPanel_2
            .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    verticalPanel.add(horizontalPanel_2);
    TextBox textBox = new TextBox();
    textBox.addKeyUpHandler(new KeyUpHandler()
    {
      public void onKeyUp(KeyUpEvent event)
      {}
    });
    horizontalPanel_2.add(textBox);
    Label lblUniqueName = new Label("No name entered");
    horizontalPanel_2.add(lblUniqueName);
    Label lblIfYouHave = new Label(
            "If you have a registered name, enter your password");
    verticalPanel.add(lblIfYouHave);
    TextBox textBox_1 = new TextBox();
    verticalPanel.add(textBox_1);
    DockPanel dockPanel = new DockPanel();
    dockPanel.setSpacing(5);
    verticalPanel.add(dockPanel);
    dockPanel.setWidth("343px");
    Button button_1 = new Button("New button");
    dockPanel.add(button_1, DockPanel.WEST);
    button_1.setText("Bugger out");
    Button btnEnterLobby = new Button("Enter lobby");
    dockPanel.add(btnEnterLobby, DockPanel.EAST);
    Label lblYouMayChoose = new Label(
            "You may choose to register a name. That way, you are sure no one else can claim your name. Also, your settings are saved and stats are saved, too.");
    verticalPanel.add(lblYouMayChoose);
    lblYouMayChoose.setWidth("362px");
    DisclosurePanel disclosurePanel = new DisclosurePanel("Register", true);
    verticalPanel.add(disclosurePanel);
    disclosurePanel.setWidth("359px");
    VerticalPanel verticalPanel_1 = new VerticalPanel();
    disclosurePanel.setContent(verticalPanel_1);
    verticalPanel_1.setSize("5cm", "4cm");
    Label lblNickname = new Label("Nickname:");
    verticalPanel_1.add(lblNickname);
    HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
    horizontalPanel_1.setSpacing(5);
    horizontalPanel_1
            .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    verticalPanel_1.add(horizontalPanel_1);
    horizontalPanel_1.setWidth("315px");
    TextBox textBox_2 = new TextBox();
    horizontalPanel_1.add(textBox_2);
    Label label_1 = new Label("New label");
    horizontalPanel_1.add(label_1);
    Label lblPassword = new Label("Password:");
    verticalPanel_1.add(lblPassword);
    TextBox textBox_3 = new TextBox();
    verticalPanel_1.add(textBox_3);
    Label lblEmailAddress = new Label(
            "Email address (optional - for password recovery):");
    verticalPanel_1.add(lblEmailAddress);
    lblEmailAddress.setWidth("328px");
    TextBox textBox_4 = new TextBox();
    verticalPanel_1.add(textBox_4);
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.setSpacing(5);
    horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    verticalPanel_1.add(horizontalPanel);
    horizontalPanel.setWidth("314px");
    Button button = new Button("New button");
    horizontalPanel.add(button);
    button.setText("Try registering");
    Label label = new Label("New label");
    label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    horizontalPanel.add(label);
    initializeConnection();
  }
  private void initializeConnection()
  {
    ServerService server = GWT.create(ServerService.class);
  }
}
