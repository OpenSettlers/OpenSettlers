package soc.gwtClient.game.widgetsBitmap.generic;

import soc.common.actions.gameAction.GameAction;

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
    public void render(GameAction value, Object key, SafeHtmlBuilder sb)
    {
        sb.append(SafeHtmlUtils.fromString(value.getToDoMessage()));

    }

}
