package org.soc.common.server;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ServerDto implements IsSerializable { 

  java.lang.String getName;
  int connectedPlayers;
  java.lang.String url;
  int port;

  public ServerDto(java.lang.String getName, int connectedPlayers, java.lang.String url, int port) {
    this.getName = getName;
    this.connectedPlayers = connectedPlayers;
    this.url = url;
    this.port = port;
  }

  protected ServerDto() {
    // Possibly for serialization.
  }

  public java.lang.String getGetName() {
    return getName;
  }

  public int getConnectedPlayers() {
    return connectedPlayers;
  }

  public java.lang.String getUrl() {
    return url;
  }

  public int getPort() {
    return port;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    ServerDto other = (ServerDto) obj;
    if (getName == null) {
      if (other.getName != null)
        return false;
    } else if (!getName.equals(other.getName))
      return false;
    if (connectedPlayers != other.connectedPlayers)
        return false;
    if (url == null) {
      if (other.url != null)
        return false;
    } else if (!url.equals(other.url))
      return false;
    if (port != other.port)
        return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (getName == null ? 1 : getName.hashCode());
    hashCode = (hashCode * 37) + new Integer(connectedPlayers).hashCode();
    hashCode = (hashCode * 37) + (url == null ? 1 : url.hashCode());
    hashCode = (hashCode * 37) + new Integer(port).hashCode();
    return hashCode;
  }

  @Override
  public String toString() {
    return "ServerDto["
                 + getName
                 + ","
                 + connectedPlayers
                 + ","
                 + url
                 + ","
                 + port
    + "]";
  }
}
