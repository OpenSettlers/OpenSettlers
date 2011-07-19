package org.soc.common.game.variants.rules;

import org.soc.common.board.layout.HexLocation;
import org.soc.common.board.pieces.Robber;
import org.soc.common.game.variants.GameRules;

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
