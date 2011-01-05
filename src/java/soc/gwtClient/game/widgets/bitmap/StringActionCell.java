package soc.gwtClient.game.widgets.bitmap;

import soc.common.actions.gameAction.GameAction;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class StringActionCell extends AbstractCell<GameAction>
{
    @Override
    public void render(GameAction arg0, Object arg1, SafeHtmlBuilder arg2)
    {
        SafeHtml safeValue = SafeHtmlUtils.fromString(arg0.getMessage());
        arg2.append(safeValue);
    }
}
