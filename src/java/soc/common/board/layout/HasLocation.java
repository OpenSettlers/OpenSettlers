package soc.common.board.layout;

/*
 * For pieces on the board located on a HexLocation, such as a robber, pirate or a Hex itself
 */
public interface HasLocation
{
    public HexLocation getLocation();
}
