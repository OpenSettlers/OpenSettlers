package org.soc.common.views.widgetsInterface.visuals;

import java.util.HashMap;

import org.soc.common.board.Board;
import org.soc.common.board.hexes.Hex;
import org.soc.common.views.behaviour.board.BoardBehaviour;


import com.google.gwt.user.client.ui.IsWidget;

public interface BoardVisual extends PieceVisual, IsWidget, BoardBehaviour
{
    public boolean isEnabled();

    public Board getBoard();

    public BoardBehaviour getBoardBehaviour();

    public BoardVisual setBoardBehaviour(BoardBehaviour behaviour);

    /*
     * Toggle visbility of territory to show
     */
    public void showTerritories();

    /*
     * Toggle visbility of territory to hide
     */
    public void hideTerritories();

    /*
     * Called by the parent widget
     */
    public void resize(int width, int height);

    // / <summary>
    // / The width of the hex measured from outer left to the middle
    // /
    // / HalfWidth
    // / | |
    // / __
    // / / \
    // / | |
    // / \ /
    // / --
    // / </summary>
    public double getHalfWidth();

    // / <summary>
    // / Total width of the hex
    // / </summary>
    // /
    // / Width
    // / | |
    // / __
    // / / \
    // / | |
    // / \ /
    // / --
    // / </summary>
    public double getHexagonWidth();

    // / <summary>
    // / Total height of the hex
    // / </summary>
    public double getHeight();

    // / <summary>
    // / Height measured from top to the first line
    // / __ _
    // / / \ _ } PartialHeight
    // / | |
    // / \ /
    // / --
    // / </summary>
    public double getPartialHeight();

    // / <summary>
    // / Height measured from the top to the second line
    // / __ _
    // / / \ } BottomHeight
    // / | | _ }
    // / \ /
    // / --
    // / </summary>
    public double getBottomHeight();

    public double getHalfHeight();

    // / <summary>
    // / Size of the hex, measured one line
    // /
    // / size
    // / | |
    // / __
    // / / \ _
    // / | | _ } size
    // / \ /
    // / --
    // / </summary>
    public int getSize();

    /**
     * @return the hexVisuals
     */
    public HashMap<Hex, HexVisual> getHexVisuals();
}
