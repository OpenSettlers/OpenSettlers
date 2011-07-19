package org.soc.gwt.client.main;

import org.soc.common.server.GreetingService;
import org.soc.common.server.GreetingServiceAsync;
import org.soc.common.server.OpenSettlersSerializer;
import org.soc.common.views.widgetsInterface.lobby.LobbyWidget;
import org.soc.common.views.widgetsInterface.main.CenterWidget;
import org.soc.gwt.client.lobby.LobbyBitmapWidget;
import org.soc.gwt.client.lobby.LoggedInEvent;
import org.soc.gwt.client.lobby.LoggedInEventHandler;
import org.soc.gwt.client.lobby.LoginDialog;
import org.soc.gwt.client.lobby.RegisterEvent;
import org.soc.gwt.client.lobby.RegisterEventHandler;

import net.zschech.gwt.comet.client.CometClient;
import net.zschech.gwt.comet.client.CometSerializer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LobbyWindow extends Composite implements CenterWidget,
                RegisterEventHandler, LoggedInEventHandler
{
    private CometClient client;
    private LobbyWidget lobbyWidget;
    private LoginDialog login;
    private GreetingServiceAsync greetingService;
    private MainWindow mainWindow;

    /** This is the entry point method. */
    public LobbyWindow(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
        greetingService = GWT.create(GreetingService.class);
        lobbyWidget = new LobbyBitmapWidget(greetingService);
        login = new LoginDialog(greetingService);
        login.addLoggedInEventHandler(this);
        initWidget(login);
    }

    @Override
    public boolean onRegister(RegisterEvent event)
    {
        return true;
    }

    @Override
    public void onLoggedIn(LoggedInEvent event)
    {
        lobbyWidget.setUser(event.getLoginResponse().getUser());

        CometSerializer serializer = GWT.create(OpenSettlersSerializer.class);
        client = new CometClient(GWT.getModuleBaseURL() + "comet", serializer,
                        lobbyWidget);
        client.start();
        mainWindow.setCurrentWidget(lobbyWidget);
    }

    @Override
    public Widget getRootWidget()
    {
        return this;
    }
}
