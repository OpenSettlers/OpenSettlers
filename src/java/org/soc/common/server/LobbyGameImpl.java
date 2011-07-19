package org.soc.common.server;

public class LobbyGameImpl implements LobbyGame
{
    private static final long serialVersionUID = -2146487043452545145L;
    private String name = "";

    @Override
    public String getName()
    {
        return name;
    }

    public LobbyGameImpl()
    {
    }

}
