package org.soc.common.server;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserCredentialsDto implements IsSerializable { 

  java.lang.String name;
  java.lang.String password;

  public UserCredentialsDto(java.lang.String name, java.lang.String password) {
    this.name = name;
    this.password = password;
  }

  protected UserCredentialsDto() {
    // Possibly for serialization.
  }

  public java.lang.String getName() {
    return name;
  }

  public java.lang.String getPassword() {
    return password;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    UserCredentialsDto other = (UserCredentialsDto) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (name == null ? 1 : name.hashCode());
    hashCode = (hashCode * 37) + (password == null ? 1 : password.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "UserCredentialsDto["
                 + name
                 + ","
                 + password
    + "]";
  }
}
