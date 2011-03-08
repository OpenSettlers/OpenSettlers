package soc.common.game.variants.rules;

import soc.common.board.layout.HexLocation;
import soc.common.board.pieces.Robber;
import soc.common.game.variants.GameRules;

public class UseRobber implements GameRule
{

    @Override
    public void set(GameRules rules)
    {
        rules.getPlayablePieces().add(new Robber(new HexLocation(0, 0)));
    }

    @Override
    public String getDescription()
    {
        // TODO fix message
        return null;
    }

}
