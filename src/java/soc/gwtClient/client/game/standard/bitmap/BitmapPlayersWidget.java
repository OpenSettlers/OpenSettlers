package soc.gwtClient.client.game.standard.bitmap;

import soc.common.game.Game;
import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractPlayersWidget;
import soc.gwtClient.client.game.IPlayerWidget;

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
