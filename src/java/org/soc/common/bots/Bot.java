package org.soc.common.bots;

import java.util.List;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.BuildRoad;
import org.soc.common.actions.gameAction.standard.BuildTown;
import org.soc.common.actions.gameAction.standard.LooseCards;
import org.soc.common.actions.gameAction.standard.PlaceRobber;
import org.soc.common.actions.gameAction.standard.RobPlayer;
import org.soc.common.actions.gameAction.trading.TradeOffer;
import org.soc.common.board.resources.ResourceList;
import org.soc.common.game.variants.Variant;
import org.soc.common.server.entities.User;


/*
 * Represents a bot. Server is responsible for calling the bot his methods.
 */
public interface Bot extends User
{
    public void handTurn(BotPrincipal principal);

    public GameAction respondToTrade(TradeOffer offer);

    public ResourceList pickGold(int amount);

    public BuildTown pickFirstTown();

    public BuildTown pickSecondTown();

    public BuildRoad pickFirstRoad();

    public BuildRoad pickSecondRoad();

    public LooseCards looseCards(int amount);

    public PlaceRobber placeRobber();

    public RobPlayer robPlayer();

    public List<Class<? extends Variant>> getSupportedVariants();
}
