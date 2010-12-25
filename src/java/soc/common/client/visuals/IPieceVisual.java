package soc.common.client.visuals;

/*
 * Base interface of every visual representation of a piece
 * Every piece on the board should be able to be selected, enabled and visibility toggled on/off. 
 */
public interface IPieceVisual
{
    public IPieceVisual setSelected(boolean selected);

    public boolean isSelected();

    public IPieceVisual setEnabled(boolean enabled);

    public boolean isEnabled();

    public IPieceVisual setVisible(boolean visible);

    public boolean isVisible();
}
