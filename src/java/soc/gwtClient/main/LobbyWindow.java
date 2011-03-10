package soc.gwtClient.main;

import net.zschech.gwt.comet.client.CometClient;
import net.zschech.gwt.comet.client.CometSerializer;
import soc.common.server.GreetingService;
import soc.common.server.GreetingServiceAsync;
import soc.common.server.OpenSettlersSerializer;
import soc.common.views.widgetsInterface.lobby.LobbyWidget;
import soc.common.views.widgetsInterface.main.CenterWidget;
import soc.gwtClient.lobby.LobbyBitmapWidget;
import soc.gwtClient.lobby.LoggedInEvent;
import soc.gwtClient.lobby.LoggedInEventHandler;
import soc.gwtClient.lobby.LoginDialog;
import soc.gwtClient.lobby.RegisterEvent;
import soc.gwtClient.lobby.RegisterEventHandler;

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
