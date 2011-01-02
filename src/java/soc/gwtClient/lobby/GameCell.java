package soc.gwtClient.lobby;

import soc.common.game.Game;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class GameCell extends AbstractCell<Game>
{

    @Override
    public void render(Game value, Object key, SafeHtmlBuilder sb)
    {
        sb.append(SafeHtmlUtils.fromString(value.getGameSettings().getName()));
    }

}
