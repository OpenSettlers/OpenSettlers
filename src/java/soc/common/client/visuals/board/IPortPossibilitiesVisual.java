package soc.common.client.visuals.board;

import soc.common.client.visuals.IPieceVisual;

/*
 * Visual interface for 6 triangles depicting a port possibility
 * for a seahex
 */
public interface IPortPossibilitiesVisual extends IPieceVisual
{
    public void updatePossibilities();
}
