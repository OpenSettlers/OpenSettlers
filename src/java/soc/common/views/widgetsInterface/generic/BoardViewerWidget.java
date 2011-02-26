package soc.common.views.widgetsInterface.generic;

/*
 * Displays a board visual for previewing a board. Shows various information
 * about the board, such as rules, stock etc.
 */
public interface BoardViewerWidget
{
    /*
     * Transforms the board hexes into a playable board, to hint the user how a
     * game using this board might look like
     */
    public void layoutBoard();

    /*
     * When the board is layouted for preview, resets the board to it's
     * before-previewing-state.
     */
    public void unLayoutBoard();
}
