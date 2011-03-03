package soc.gwtClient.lobby;

import net.zschech.gwt.comet.client.CometClient;
import net.zschech.gwt.comet.client.CometSerializer;
import soc.common.server.GreetingService;
import soc.common.server.GreetingServiceAsync;
import soc.common.server.OpenSettlersSerializer;
import soc.common.views.widgetsInterface.lobby.LobbyWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class OpenSettlersWebsite implements EntryPoint, RegisterEventHandler,
        LoggedInEventHandler
{
    private CometClient client;
    private LobbyWidget lobbyWidget;
    private LoginPlayer login;
    private GreetingServiceAsync greetingService;

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad()
    {
        greetingService = GWT.create(GreetingService.class);
        lobbyWidget = new LobbyBitmapWidget(greetingService);
        login = new LoginPlayer(greetingService);
        login.addLoggedInEventHandler(this);
        RootLayoutPanel.get().add(lobbyWidget);
        RootLayoutPanel.get().add(login);
        login.center();
        login.show();
    }

    @Override
    public boolean onRegister(RegisterEvent event)
    {
        return true;
    }

    @Override
    public void onLoggedIn(LoggedInEvent event)
    {
        lobbyWidget.getLobby().getUsers()
                .addUsers(event.getLoginResponse().getLoggedInUsers());

        CometSerializer serializer = GWT.create(OpenSettlersSerializer.class);
        client = new CometClient(GWT.getModuleBaseURL() + "comet", serializer,
                lobbyWidget);
        client.start();
        login.hide();
    }
}
