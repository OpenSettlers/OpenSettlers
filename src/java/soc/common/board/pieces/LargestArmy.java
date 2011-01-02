package soc.common.board.pieces;

import soc.common.game.VictoryPointItem;

public class LargestArmy extends AbstractPlayerPiece implements
        VictoryPointItem
{
    private static final long serialVersionUID = 8037446795922927966L;

    private int amountSoldiers;

    @Override
    public int getVictoryPoints()
    {
        return 2;
    }

    public LargestArmy setAmountSoldiers(int amountSoldiers)
    {
        this.amountSoldiers = amountSoldiers;

        return this;
    }

    public int getAmountSoldiers()
    {
        return amountSoldiers;
    }

    @Override
    public boolean isStockPiece()
    {
        return false;
    }

}
