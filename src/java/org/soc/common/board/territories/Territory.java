package org.soc.common.board.territories;

import java.io.Serializable;

import org.soc.common.board.chits.ChitList;
import org.soc.common.board.hexes.HexList;
import org.soc.common.board.ports.Port;
import org.soc.common.board.ports.PortList;
import org.soc.common.server.randomization.Random;


/*
 * Represents a group of hexes on a Board. Each hex is assigned a territory.
 * 
 * At playtime, territories enable Trade Routes, bonus island vicotory points,
 * and chit swapping. At designtime, territories keep lists of chits, ports and
 * hexes for LayoutStrategies, to transform the designtime board to a playable board.
 */
public interface Territory extends Serializable
{
    /** @return the isBonus */
    public boolean isBonus();

    /** @param isBonus
     *            the isBonus to set */
    public Territory setBonus(boolean isBonus);

    public String getName();

    /** @return the ports */
    public PortList getPorts();

    /** @param ports
     *            the ports to set */
    public Territory setPorts(PortList ports);

    public void setName(String name);

    public int getID();

    public Territory setID(int iD);

    public boolean isMainland();

    public Territory setMainland(boolean isMainland);

    public boolean isIsland();

    public Territory setIsland(boolean isIsland);

    public ChitList getChits();

    public Territory setChits(ChitList chits);

    public HexList getHexes();

    public Port grabPort(Random random, PortList supportedPorts);

}