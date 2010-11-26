package soc.common.client.visuals.board;

import soc.common.board.ports.Port;
import soc.common.client.visuals.IPieceVisual;

public interface IPortVisual extends IPieceVisual
{
    public IPortVisual setPort(Port port);
    public IPortVisual setValid(boolean valid);
    public Port getPort();
    public boolean isValid();
}
