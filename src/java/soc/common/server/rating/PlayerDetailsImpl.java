package soc.common.server.rating;

import java.util.Date;
import java.util.List;

import soc.common.board.Board;
import soc.common.board.hexes.Hex;
import soc.common.board.resources.ResourceList;
import soc.common.server.entities.User;

public class PlayerDetailsImpl implements PlayerDetails
{
    private AllRating allRating = null;
    private Board board = null;
    private BoardRatingList boardRatings = new BoardRatingList();
    private VariantRatingList variantRatings = new VariantRatingList();

    @Override
    public AllRating getAllRating()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Board getBoard()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<BoardRating> getBoardRatings()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Hex> getHexes()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getPlayer()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Date getRegisteredDate()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResourceList getResources()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<VariantRating> getVariantRatings()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addRating(PlayedGame playedGame)
    {
        allRating.addRating(playedGame);
        boardRatings.addRating(playedGame);
        variantRatings.addRating(playedGame);
    }
}
