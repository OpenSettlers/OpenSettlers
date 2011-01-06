package soc.gwtClient.visuals.abstractVisuals;

import java.util.HashMap;

import soc.common.board.Board;
import soc.common.board.hexes.Hex;
import soc.gwtClient.visuals.behaviour.InteractionBehaviour;

import com.google.gwt.user.client.ui.IsWidget;

public interface BoardVisual extends PieceVisual, IsWidget
{
    public boolean isEnabled();

    public Board getBoard();

    public InteractionBehaviour getBoardBehaviour();

    public BoardVisual setBoardBehaviour(InteractionBehaviour behaviour);

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
