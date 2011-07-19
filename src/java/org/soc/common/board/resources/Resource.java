package org.soc.common.board.resources;

import java.io.Serializable;

import org.soc.common.views.meta.HasMeta;


/*
 * Represents an resource to be traded by players to other opponents or the bank. Can
 * also be used to buy pieces, such as roads, development cards or towns.
 */
public interface Resource extends Serializable, HasMeta
{
    /*
     * Returns true when this resource can be traded among players
     */
    public boolean isTradeable();

    public String getName();

    public String getColor();

    public Resource copy();
}
