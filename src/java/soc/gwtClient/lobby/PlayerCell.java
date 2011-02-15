package soc.gwtClient.lobby;

import soc.common.server.entities.Player;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class PlayerCell extends AbstractCell<Player>
{

    @Override
    public void render(Player value, Object key, SafeHtmlBuilder sb)
    {
        sb.append(SafeHtmlUtils.fromString(value.getName()));
    }

}
