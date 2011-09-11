package org.soc.gwt.client.lobby;

import org.soc.common.server.entities.User.Player;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class PlayerCell extends AbstractCell<Player>
{
  @Override public void render(com.google.gwt.cell.client.Cell.Context context,
          Player value, SafeHtmlBuilder sb)
  {
    sb.append(SafeHtmlUtils.fromString(value.name()));
  }
  // @Override
  // public void render(com.google.gwt.cell.client.Cell.Context context,
  // Player value, SafeHtmlBuilder sb)
  // {
  // sb.append(SafeHtmlUtils.fromString(value.getName()));
  // }
  //
  // @Override
  // public void render(com.google.gwt.cell.client.Cell.Context context,
  // Player value, SafeHtmlBuilder sb)
  // {
  //
  // }
}
