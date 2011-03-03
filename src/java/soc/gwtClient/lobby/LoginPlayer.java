package soc.gwtClient.lobby;

import soc.common.server.GreetingServiceAsync;
import soc.common.server.LoginResponse;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginPlayer extends PopupPanel
{
    private PasswordTextBox passwordPassword;
    private TextBox textboxNickname;
    private GreetingServiceAsync chatService;
    private Label labelStatus;
    private EventBus eventBus = new SimpleEventBus();

    public LoginPlayer(GreetingServiceAsync chatService)
    {
        this();
        this.chatService = chatService;
    }

    /**
     * @wbp.parser.constructor
     */
    public LoginPlayer()
    {

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(10);
        add(verticalPanel);
        verticalPanel.setHeight("172px");

        VerticalPanel verticalPanel_1 = new VerticalPanel();
        verticalPanel.add(verticalPanel_1);

        Label lblNewLabel = new Label("Nickname:");
        verticalPanel_1.add(lblNewLabel);

        textboxNickname = new TextBox();
        verticalPanel_1.add(textboxNickname);

        VerticalPanel verticalPanel_2 = new VerticalPanel();
        verticalPanel.add(verticalPanel_2);

        Label lblNewLabel_1 = new Label("Password:");
        verticalPanel_2.add(lblNewLabel_1);

        passwordPassword = new PasswordTextBox();
        verticalPanel_2.add(passwordPassword);

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setSpacing(5);
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        verticalPanel.add(horizontalPanel);

        Button buttonLogin = new Button("New button");
        horizontalPanel.add(buttonLogin);
        buttonLogin.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                tryLogin();
            }

        });
        buttonLogin.setText("Login");

        labelStatus = new Label("");
        horizontalPanel.add(labelStatus);
    }

    public HandlerRegistration addLoggedInEventHandler(
            LoggedInEventHandler handler)
    {
        return eventBus.addHandler(LoggedInEvent.TYPE, handler);
    }

    private void tryLogin()
    {
        labelStatus.setText("Logging in...");
        chatService.login(textboxNickname.getText(),
                new AsyncCallback<LoginResponse>()
                {

                    @Override
                    public void onSuccess(LoginResponse result)
                    {
                        labelStatus.setText("Logged in!");
                        eventBus.fireEvent(new LoggedInEvent(result));
                    }

                    @Override
                    public void onFailure(Throwable caught)
                    {
                        labelStatus.setText("Login failed!");
                    }
                });
    }
}
