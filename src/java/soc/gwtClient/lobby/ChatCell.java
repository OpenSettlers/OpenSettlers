package soc.gwtClient.lobby;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class ChatCell extends AbstractCell<String>
{

    @Override
    public void render(String value, Object key, SafeHtmlBuilder sb)
    {
        sb.append(SafeHtmlUtils.fromString(value));
    }

}
