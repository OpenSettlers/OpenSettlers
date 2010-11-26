package soc.common.board.hexes;

import soc.common.board.ports.Port;

public class SeaHex extends Hex
{
    /*
     * A SeaHex may have a port associated with it
     */
    private Port port = null;

    /**
     * @return the port
     */
    public Port getPort()
    {
        return port;
    }

    /**
     * @param port the port to set
     */
    public SeaHex setPort(Port port)
    {
        this.port = port;
        
        return this;
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#Copy()
     */
    @Override
    public Hex Copy()
    {
        // TODO Auto-generated method stub
        return new SeaHex();
    }

    /* (non-Javadoc)
     * @see soc.common.board.hexes.Hex#getColor()
     */
    @Override
    public String getColor()
    {
        // TODO Auto-generated method stub
        return "DarkBlue";
    }
}
