package soc.gwtClient.visuals.abstractVisuals;

import soc.common.board.pieces.City;
import soc.common.board.pieces.Pirate;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Robber;
import soc.common.board.pieces.Town;
import soc.common.board.pieces.abstractPieces.Piece;
import soc.common.board.pieces.abstractPieces.PlayerPiece;

public abstract class AbstractVisualFactory implements VisualFactory
{
    @Override
    public PieceVisual createPieceVisual(Piece piece)
    {
        if (piece instanceof PlayerPiece)
        {
            return createPlayerPieceVisual((PlayerPiece) piece);
        }

        if (piece instanceof Robber)
        {
            return createRobberVisual((Robber) piece);
        }
        if (piece instanceof Pirate)
        {
            return createPirateVisual((Pirate) piece);
        }
        return null;
    }

    @Override
    public PieceVisual createPlayerPieceVisual(PlayerPiece piece)
    {
        if (piece instanceof Town)
        {
            return createTownVisual((Town) piece);
        }

        if (piece instanceof City)
        {
            return createCityVisual((City) piece);
        }

        if (piece instanceof Road)
        {
            return createRoadVisual((Road) piece);
        }
        return null;
    }
}
