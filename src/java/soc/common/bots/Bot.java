package soc.common.bots;

import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.LooseCards;
import soc.common.actions.gameAction.turnActions.standard.PlaceRobber;
import soc.common.actions.gameAction.turnActions.standard.RobPlayer;
import soc.common.actions.gameAction.turnActions.standard.TradeOffer;
import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.resources.ResourceList;
import soc.common.game.variants.Variant;
import soc.common.server.data.User;

/*
 * Represents a bot. Server is responsible for calling the bot his methods.
 */
public interface Bot extends User
{
    public void handTurn(BotPrincipal principal);

    public GameAction respondToTrade(TradeOffer offer);

    public ResourceList pickGold(int amount);

    public HexPoint pickFirstTown();

    public HexPoint pickSecondTown();

    public HexSide pickFirstRoad();

    public HexSide pickSecondRoad();

    public LooseCards looseCards(int amount);

    public PlaceRobber placeRobber();

    public RobPlayer robPlayer();

    public List<Class<? extends Variant>> getSupportedVariants();
}
