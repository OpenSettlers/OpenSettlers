package soc.common.board.pieces.pieceLists;

import soc.common.board.pieces.abstractPieces.PlayerPiece;

import com.google.gwt.event.shared.GwtEvent;

/*
 * Event to notify what piece has been changed in a list of player pieces.
 * No List of added/removed pieces is kept, since this will not happen in the game
 * (except on creation maybe)
 */
public class PiecesChangedEvent extends GwtEvent<PiecesChangedEventHandler>
{
    public static Type<PiecesChangedEventHandler> TYPE = new Type<PiecesChangedEventHandler>();
    private PlayerPiece addedPiece;
    private PlayerPiece removedPiece;

    public PiecesChangedEvent(PlayerPiece addedPiece, PlayerPiece removedPiece)
    {
        this.addedPiece = addedPiece;
        this.removedPiece = removedPiece;
    }

    /**
     * @return the addedPiece
     */
    public PlayerPiece getAddedPiece()
    {
        return addedPiece;
    }

    /**
     * @return the removedPiece
     */
    public PlayerPiece getRemovedPiece()
    {
        return removedPiece;
    }

    /*
     * Returns the either the removed of added piece depending on what piece is
     * not null
     */
    public PlayerPiece getChangedPiece()
    {
        return addedPiece == null ? removedPiece : addedPiece;
    }

    @Override
    protected void dispatch(PiecesChangedEventHandler handler)
    {
        handler.onPiecesChanged(this);
    }

    @Override
    public Type<PiecesChangedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

}
