package soc.common.game.developmentCards.standard;

import soc.common.game.Game;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.player.GamePlayer;

public class RoadBuilding extends DevelopmentCard
{

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.developmentCards.DevelopmentCard#play(soc.common.game
     * .Game, soc.common.game.Player)
     */
    @Override
    public void play(Game game, GamePlayer player)
    {
        int roadBuildingTokens = player.getRoadBuildingTokens();
        player.setRoadBuildingTokens(roadBuildingTokens += 2);

        message = player.getUser().getName() + " played a road building card";

        super.play(game, player);
    }

}
