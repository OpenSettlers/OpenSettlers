package soc.common.board.territories;

import soc.common.board.ChitList;
import soc.common.board.hexes.HexList;
import soc.common.board.ports.PortList;

public interface Territory
{

    /**
     * @return the isBonus
     */
    public boolean isBonus();

    /**
     * @param isBonus
     *            the isBonus to set
     */
    public Territory setBonus(boolean isBonus);

    public String getName();

    /**
     * @return the ports
     */
    public PortList getPorts();

    /**
     * @param ports
     *            the ports to set
     */
    public Territory setPorts(PortList ports);

    public void setName(String name);

    public int getID();

    public Territory setID(int iD);

    public boolean isMainland();

    public Territory setMainland(boolean isMainland);

    public boolean isIsland();

    public Territory setIsland(boolean isIsland);

    public ChitList getChits();

    public HexList getHexes();

}