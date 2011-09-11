package org.soc.common.internationalization;

import com.google.gwt.core.client.GWT;

public class ClientInternationalization implements
        OpenSettlersInternationalization
{
    public OpenSettlersConstants constants = GWT
            .create(OpenSettlersConstants.class);
    public OpenSettlersActions actions = GWT.create(OpenSettlersActions.class);
    public OpenSettlersMisc misc = GWT.create(OpenSettlersMisc.class);
    public OpenSettlersStatuses statuses = GWT
            .create(OpenSettlersStatuses.class);
    public OpenSettlersLobby lobby = GWT.create(OpenSettlersLobby.class);
    public UserInterfaceMessages ui = GWT.create(UserInterfaceMessages.class);

    @Override
    public OpenSettlersActions actions()
    {
        return actions;
    }

    @Override
    public OpenSettlersConstants constants()
    {
        return constants;
    }

    @Override
    public OpenSettlersMisc misc()
    {
        return misc;
    }

    @Override
    public OpenSettlersStatuses statuses()
    {
        return statuses;
    }

    @Override
    public OpenSettlersLobby lobby()
    {
        return lobby;
    }

    @Override
    public UserInterfaceMessages ui()
    {
        return ui;
    }
}
