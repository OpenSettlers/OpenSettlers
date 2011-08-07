package org.soc.common.server.actions;

import org.soc.common.actions.gameAction.AbstractGameAction;
import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.standard.BuyDevelopmentCard;
import org.soc.common.server.GameServer;

public class ServerBuyDevelopmentCard implements ServerAction
{
    private BuyDevelopmentCard buyDevCard;
    private GameServer gameServer;

    public ServerBuyDevelopmentCard(GameServer gameServer,
            BuyDevelopmentCard buyDevCard)
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
