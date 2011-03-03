package soc.gwtClient.game.widgetsBitmap.generic;

import soc.common.actions.gameAction.GameAction;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class StringActionCell extends AbstractCell<GameAction>
{

    // @Override
    // public void render(com.google.gwt.cell.client.Cell.Context context,
    // GameAction value, SafeHtmlBuilder sb)
    // {
    // SafeHtml safeValue = SafeHtmlUtils.fromString(value.getMessage());
    // sb.append(safeValue);
    // }

    @Override
    public void render(GameAction value, Object key, SafeHtmlBuilder sb)
    {
        SafeHtml safeValue = SafeHtmlUtils.fromString(value.getMessage());
        sb.append(safeValue);
    }
}
