package soc.common.server.actions;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.game.Game;

public class ServerBuyDevelopmentCard implements ServerAction
{
    private BuyDevelopmentCard buyDevCard;
    private Game game;
    
    public ServerBuyDevelopmentCard(BuyDevelopmentCard buyDevCard, Game game)
    {
        super();
        this.buyDevCard = buyDevCard;
        this.game = game;
    }

    @Override
    public void execute()
    {
        buyDevCard.setDevelopmentCard(game.getDevelopmentCardStack().drawTop());
    }

    @Override
    public AbstractGameAction getAction()
    {
        return buyDevCard;
    }

    @Override
    public AbstractGameAction getOpponentAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
