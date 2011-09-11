package org.soc.gwt.client.lobby;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class ChatCell extends AbstractCell<String>
{
    // @Override
    // public void render(com.google.gwt.cell.client.Cell.Context context,
    // String value, SafeHtmlBuilder sb)
    // {
    // sb.append(SafeHtmlUtils.fromString(value));
    // }

    @Override
    public void render(com.google.gwt.cell.client.Cell.Context context,
                    String value, SafeHtmlBuilder sb)
    {
        sb.append(SafeHtmlUtils.fromString(value));
    }

}
