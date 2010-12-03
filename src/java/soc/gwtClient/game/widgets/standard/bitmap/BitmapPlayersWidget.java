package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.game.Game;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractPlayersWidget;
import soc.gwtClient.game.abstractWidgets.IPlayerWidget;

public class BitmapPlayersWidget extends AbstractPlayersWidget
{

    public BitmapPlayersWidget(Game game)
    {
        super(game);
    }

    @Override
    public IPlayerWidget createPlayerWidget(Game game, Player player)
    {
        return new BitmapPlayerWidget(game, player);
    }

}
