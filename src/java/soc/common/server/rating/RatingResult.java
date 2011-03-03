package soc.common.server.rating;

/*
 * New ratings given by the player after the game is over
 */
public interface RatingResult
{
    public AllRating getAllRating();

    public BoardRating getBoardRating();

    public VariantRating getVariantRating();
}
