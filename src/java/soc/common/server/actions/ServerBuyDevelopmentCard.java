package soc.common.server.actions;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.server.GameServer;

public class ServerBuyDevelopmentCard implements ServerAction
{
    private BuyDevelopmentCard buyDevCard;
    private GameServer gameServer;

    public ServerBuyDevelopmentCard(BuyDevelopmentCard buyDevCard,
            GameServer gameServer)
    {
        super();
        this.buyDevCard = buyDevCard;
        this.gameServer = gameServer;
    }

    @Override
    public void execute()
    {
        buyDevCard.setDevCard(gameServer.getGame().getDevelopmentCardStack()
                .drawTop());

        gameServer.getGame().performAction(buyDevCard);
    }

    @Override
    public AbstractGameAction getAction()
    {
        return buyDevCard;
    }

    @Override
    public GameAction getOpponentAction()
    {
        return (BuyDevelopmentCard) new BuyDevelopmentCard().setResources(
                buyDevCard.getResources()).setPlayer(buyDevCard.getPlayer());
    }
}
