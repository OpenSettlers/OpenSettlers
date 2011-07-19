package org.soc.common.server.entities;

import java.io.Serializable;

public interface ServerInfo extends Serializable
{
    public String getName();
    public int connectedPlayers();
    public String getURL();
    public int getPort();
}
