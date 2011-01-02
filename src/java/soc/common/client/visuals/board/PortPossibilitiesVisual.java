package soc.common.client.visuals.board;

import soc.common.client.visuals.PieceVisual;

/*
 * Visual interface for 6 triangles depicting a port possibility
 * for a seahex
 */
public interface PortPossibilitiesVisual extends PieceVisual
{
    public void updatePossibilities();
}
