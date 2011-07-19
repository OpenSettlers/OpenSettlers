package org.soc.gwt.client.lobby;

import org.soc.common.lobby.LoginResponse;
import org.soc.common.server.GreetingServiceAsync;
import org.soc.common.server.RegisterResult;
import org.soc.common.server.entities.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginDialog extends Composite
{
    private PasswordTextBox passwordPassword;
    private TextBox textboxNickname;
    private GreetingServiceAsync chatService;
    private Label labelLoginStatus;
    private EventBus eventBus = new SimpleEventBus();
    private Label labelRegisterStatus;
    private User currentUser;

    public LoginDialog(GreetingServiceAsync chatService)
    {
        this();
        this.chatService = chatService;
    }

    /** @wbp.parser.constructor */
    public LoginDialog()
    {

        VerticalPanel panelRoot = new VerticalPanel();
        initWidget(panelRoot);
        panelRoot.setSize("100%", "100%");

        VerticalPanel verticalPanel = new VerticalPanel();
        panelRoot.add(verticalPanel);
        verticalPanel.setSpacing(10);

        VerticalPanel panelNickName = new VerticalPanel();
        panelNickName.setSpacing(5);
        panelNickName.setStyleName("common-panel");
        verticalPanel.add(panelNickName);

        Label lblNewLabel = new Label("Nickname:");
        panelNickName.add(lblNewLabel);

        textboxNickname = new TextBox();
        textboxNickname.addKeyUpHandler(new KeyUpHandler()
        {
            public void onKeyUp(KeyUpEvent event)
            {
                checkName();
            }
        });
        panelNickName.add(textboxNickname);

        VerticalPanel panelPassword = new VerticalPanel();
        panelPassword.setSpacing(5);
        panelPassword.setStyleName("common-panel");
        verticalPanel.add(panelPassword);

        Label lblNewLabel_1 = new Label("Password:");
        panelPassword.add(lblNewLabel_1);

        passwordPassword = new PasswordTextBox();
        panelPassword.add(passwordPassword);

        HorizontalPanel panelLoginRegister = new HorizontalPanel();
        panelRoot.add(panelLoginRegister);
        panelLoginRegister.setStyleName("ok-button");
        panelLoginRegister
                        .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        panelLoginRegister.setWidth("100%");

        CaptionPanel captionpanelLogin = new CaptionPanel("Login");
        captionpanelLogin.setStyleName("common-panel");
        panelLoginRegister.add(captionpanelLogin);
        captionpanelLogin.setWidth("25em");

        VerticalPanel verticalPanel_4 = new VerticalPanel();
        verticalPanel_4.setSpacing(10);
        captionpanelLogin.setContentWidget(verticalPanel_4);
        verticalPanel_4.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanel_4.setSize("100%", "3cm");

        DockPanel dockPanel = new DockPanel();
        dockPanel.setSpacing(10);
        verticalPanel_4.add(dockPanel);
        dockPanel.setWidth("100%");

        Image image_2 = new Image(
                        "WEB-INF/classes/org.soc/gwtClient/images/defaultTheme/size48/DetermineFirstPlayerGamePhase.png");
        dockPanel.add(image_2, DockPanel.WEST);
        image_2.setSize("48", "48");

        Label lblNewLabel_2 = new Label(
                        "Login as registered user or login as anyonymous user without registration");
        dockPanel.add(lblNewLabel_2, DockPanel.EAST);
        lblNewLabel_2.setStyleName("just-text");

        labelLoginStatus = new Label("Loginstatus");
        verticalPanel_4.add(labelLoginStatus);
        labelLoginStatus.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        labelLoginStatus.setSize("100%", "5em");

        HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
        horizontalPanel_1
                        .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        verticalPanel_4.add(horizontalPanel_1);

        Button buttonLogin = new Button("New button");
        horizontalPanel_1.add(buttonLogin);
        buttonLogin.setStyleName("ok-button");
        buttonLogin.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                tryLogin();
            }

        });
        buttonLogin.setText("Login");

        Label lblNewLabel_3 = new Label("or");
        lblNewLabel_3.setStyleName("or-label");
        lblNewLabel_3.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panelLoginRegister.add(lblNewLabel_3);

        CaptionPanel captionpanelRegister = new CaptionPanel("Register");
        captionpanelRegister.setStyleName("common-panel");
        panelLoginRegister.add(captionpanelRegister);
        captionpanelRegister.setWidth("25em");

        VerticalPanel verticalPanel_3 = new VerticalPanel();
        verticalPanel_3.setSpacing(10);
        captionpanelRegister.setContentWidget(verticalPanel_3);
        verticalPanel_3.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanel_3.setSize("100%", "3cm");

        DockPanel dockPanel_1 = new DockPanel();
        dockPanel_1.setSpacing(10);
        verticalPanel_3.add(dockPanel_1);
        dockPanel_1.setWidth("100%");

        Image image = new Image(
                        "WEB-INF/classes/org.soc/gwtClient/images/defaultTheme/size48/TradePlayer.png");
        dockPanel_1.add(image, DockPanel.WEST);
        image.setSize("48", "48");

        Label lblNewLabel_4 = new Label(
                        "Register to password protect your nickname");
        dockPanel_1.add(lblNewLabel_4, DockPanel.EAST);
        lblNewLabel_4.setWidth("100%");
        lblNewLabel_4.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

        labelRegisterStatus = new Label(
                        "registerstatus and more text to see what overflow method is used");
        labelRegisterStatus
                        .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        verticalPanel_3.add(labelRegisterStatus);
        labelRegisterStatus.setSize("100%", "5em");

        HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
        horizontalPanel_2
                        .setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        verticalPanel_3.add(horizontalPanel_2);

        Image image_1 = new Image(
                        "WEB-INF/classes/org.soc/gwtClient/images/defaultTheme/size32/AcceptOffer.png");
        horizontalPanel_2.add(image_1);
        image_1.setSize("32", "32");

        Button btnRegister = new Button("Register");
        btnRegister.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                tryRegisterUser();
            }
        });
        horizontalPanel_2.add(btnRegister);
        btnRegister.setStyleName("ok-button");
        btnRegister.setWidth("10em");
    }

    protected void tryRegisterUser()
    {
        String username = textboxNickname.getText();
        String password = passwordPassword.getText();

        if (username.length() > 0 && password.length() > 0)
        {
            chatService.register(username, password,
                            new AsyncCallback<RegisterResult>()
                            {

                                @Override
                                public void onSuccess(RegisterResult result)
                                {
                                    if (result.isRegistered().isValid())
                                    {
                                        labelRegisterStatus
                                                        .setText("You are registered as"
                                                                        + result.getUser()
                                                                                        .getName()
                                                                        + ". Logging you in...");

                                        currentUser = result.getUser();
                                        tryLogin();
                                    } else
                                    {
                                        labelRegisterStatus
                                                        .setText("Something went wrong registering you, sorry.");
                                    }
                                }

                                @Override
                                public void onFailure(Throwable caught)
                                {
                                    labelRegisterStatus
                                                    .setText("Something went wrong registering you, sorry.");
                                }
                            });
        }
    }

    public HandlerRegistration addLoggedInEventHandler(
                    LoggedInEventHandler handler)
    {
        return eventBus.addHandler(LoggedInEvent.TYPE, handler);
    }

    private void checkName()
    {
        chatService.isAvailableName(textboxNickname.getText(),
                        new AsyncCallback<Boolean>()
                        {
                            @Override
                            public void onSuccess(Boolean result)
                            {
                                if (result)
                                    labelLoginStatus.setText("This username exists. Provide a password to login");
                                else
                                    labelLoginStatus.setText("This username does not exist. Click the login button to enter as unregistered user");

                            }
                            @Override
                            public void onFailure(Throwable caught)
                            {
                                labelLoginStatus.setText("Something went wrong checking username");
                            }
                        });
    }

    private void tryLogin()
    {
        labelLoginStatus.setText("Logging in...");
        chatService.login(textboxNickname.getText(),
                        passwordPassword.getText(),
                        new AsyncCallback<LoginResponse>()
                        {

                            @Override
                            public void onSuccess(LoginResponse result)
                            {
                                labelLoginStatus.setText("Logged in!");
                                eventBus.fireEvent(new LoggedInEvent(result));
                            }

                            @Override
                            public void onFailure(Throwable caught)
                            {
                                labelLoginStatus.setText("Login failed!");
                            }
                        });
    }
}
