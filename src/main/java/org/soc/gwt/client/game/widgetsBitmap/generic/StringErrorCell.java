package org.soc.gwt.client.game.widgetsBitmap.generic;

import org.soc.common.game.actions.MessageFromServer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class StringErrorCell extends AbstractCell<MessageFromServer>
{
    //
    // @Override
    // public void render(com.google.gwt.cell.client.Cell.Context context,
    // MessageFromServer value, SafeHtmlBuilder sb)
    // {
    // SafeHtml safeValue = SafeHtmlUtils.fromString(value.getServerMessage());
    // sb.append(safeValue);
    // }
    @Override
    public void render(com.google.gwt.cell.client.Cell.Context context,
                    MessageFromServer value, SafeHtmlBuilder sb)
    {
        SafeHtml safeValue = SafeHtmlUtils.fromString(value.getServerMessage());
        sb.append(safeValue);
    }

}
