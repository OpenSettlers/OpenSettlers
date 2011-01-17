package soc.common.board.pieces;

import soc.common.board.Board;
import soc.common.board.resources.ResourceList;
import soc.common.game.player.GamePlayer;
import soc.common.utils.ClassUtils;

public abstract class AbstractPlayerPiece extends AbstractPiece implements
        PlayerPiece
{
    private static final long serialVersionUID = 1421211880859160448L;
    protected GamePlayer player;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.pieces.PlayerPiece#getPlayer()
     */
    public GamePlayer getPlayer()
    {
        return player;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.board.pieces.PlayerPiece#setPlayer(soc.common.game.GamePlayerImpl
     * )
     */
    public PlayerPiece setPlayer(GamePlayer player)
    {
        this.player = player;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.pieces.PlayerPiece#getCost()
     */
    public ResourceList getCost()
    {
        throw new RuntimeException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.board.pieces.PlayerPiece#canPay(soc.common.game.GamePlayer)
     */
    public boolean canPay(GamePlayer player)
    {
        // First, create a copy so we can safely remove resources from it
        ResourceList copy = player.getResources().copy();

        // Pay resources player can simply pay for
        copy.subtractResources(getCost());

        // Calculate amount of gold we need
        int neededGold =
        // amount of resources this piece needs, minus...
        getCost().size() -
        // the resources the player can simply pay for
                (player.getResources().size() - copy.size());

        // Player can pay given piece if he can trade exactly or more gold as
        // needed
        return player.getPorts().amountGold(copy) >= neededGold;
    }

    public boolean canBuild(Board board, GamePlayer player)
    {
        throw new RuntimeException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.pieces.PlayerPiece#getName()
     */
    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }
}
