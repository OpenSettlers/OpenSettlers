package soc.gwtClient.visuals.svg;

import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Circle;

import soc.common.board.HexLocation;
import soc.gwtClient.visuals.abstractVisuals.BoardVisual;
import soc.gwtClient.visuals.abstractVisuals.PieceVisual;
import soc.gwtClient.visuals.abstractVisuals.PortPossibilitiesVisual;

public class DummyPortPossibilitiesSvg implements PortPossibilitiesVisual,
        SvgVisual
{

    public DummyPortPossibilitiesSvg(HexLocation hexLocation, BoardVisual parent)
    {
    }

    @Override
    public void updatePossibilities()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isEnabled()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSelected()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isVisible()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public PieceVisual setEnabled(boolean enabled)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PieceVisual setSelected(boolean selected)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PieceVisual setVisible(boolean visible)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VectorObject getVectorObject()
    {
        return new Circle(1, 1, 1);
    }

}
