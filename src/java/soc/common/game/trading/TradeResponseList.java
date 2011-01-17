package soc.common.game.trading;

import java.util.ArrayList;
import java.util.List;

import soc.common.game.player.GamePlayer;

public class TradeResponseList
{
    private List<TradeResponse> tradeResponses = new ArrayList<TradeResponse>();

    public void addResponse(TradeResponse response)
    {
        tradeResponses.add(response);
    }

    public int size()
    {
        return tradeResponses.size();
    }

    public boolean containsResponse(GamePlayer player)
    {
        for (TradeResponse response : tradeResponses)
            if (response.getPlayer().equals(player))
                return true;

        return false;
    }
}
