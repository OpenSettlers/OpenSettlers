package soc.common.server.actions;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.standard.BuyDevelopmentCard;
import soc.common.game.Game;

public class ServerBuyDevelopmentCard implements IServerAction
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
        buyDevCard.setDevelopmentCard(game.getDevelopmentCards().drawTop());
    }

    @Override
    public GameAction getAction()
    {
        return buyDevCard;
    }

    @Override
    public GameAction getOpponentAction()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
