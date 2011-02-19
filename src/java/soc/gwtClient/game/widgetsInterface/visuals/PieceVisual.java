package soc.gwtClient.game.widgetsInterface.visuals;

/*
 * Base interface of every visual representation of a piece
 * Every piece on the board should be able to be selected, enabled and visibility toggled on/off. 
 */
public interface PieceVisual
{
    public PieceVisual setSelected(boolean selected);

    public boolean isSelected();

    public PieceVisual setEnabled(boolean enabled);

    public boolean isEnabled();

    public PieceVisual setVisible(boolean visible);

    public boolean isVisible();
}
