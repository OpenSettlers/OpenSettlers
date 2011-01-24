package soc.common.board.pieces.abstractPieces;

import soc.common.board.Board;

/*
 * Represents a piece that is put on the board upon playing
 */
public interface BoardPiece
{
    public void addToBoard(Board board);

    public void removeFromBoard(Board board);
}
