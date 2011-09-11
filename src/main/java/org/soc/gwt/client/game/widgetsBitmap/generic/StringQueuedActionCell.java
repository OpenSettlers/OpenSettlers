package org.soc.gwt.client.game.widgetsBitmap.generic;

import org.soc.common.game.actions.GameAction;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class StringQueuedActionCell extends AbstractCell<GameAction>
{
    // @Override
    // public void render(com.google.gwt.cell.client.Cell.Context context,
    // GameAction value, SafeHtmlBuilder sb)
    // {
    // sb.append(SafeHtmlUtils.fromString(value.getToDoMessage()));
    // }

    @Override
    public void render(com.google.gwt.cell.client.Cell.Context context,
                    GameAction value, SafeHtmlBuilder sb)
    {
        sb.append(SafeHtmlUtils.fromString(value.toDoMessage()));
    }

}
