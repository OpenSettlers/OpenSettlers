/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2007, by Barak Naveh and Contributors.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */
/* -------------------
 * EmptyGraphGenerator.java
 * -------------------
 * (C) Copyright 2003-2007, by John V. Sichi and Contributors.
 *
 * Original Author:  John V. Sichi
 * Contributor(s):   -
 *
 * $Id: EmptyGraphGenerator.java 568 2007-09-30 00:12:18Z perfecthash $
 *
 * Changes
 * -------
 * 16-Sep-2003 : Initial revision (JVS);
 *
 */
package org.jgrapht.generate;

import java.util.*;

import com.google.gwt.core.client.GWT;
import org.jgrapht.*;
import soc.common.internationalization.OpenSettlersConstants;


/**
 * Generates an <a href="http://mathworld.wolfram.com/EmptyGraph.html">empty
 * graph</a> of any size. An empty graph is a graph that has no edges.
 *
 * @author John V. Sichi
 * @since Sep 16, 2003
 */
public class EmptyGraphGenerator<V, E>
    implements GraphGenerator<V, E, V>
{
  public static final String MUST_BE_NON_NEGATIVE = ((OpenSettlersConstants) GWT.create(OpenSettlersConstants.class)).mustBeNonNegative();
  //~ Instance fields --------------------------------------------------------

    private int size;

    //~ Constructors -----------------------------------------------------------

    /**
     * Construct a new EmptyGraphGenerator.
     *
     * @param size number of vertices to be generated
     *
     * @throws IllegalArgumentException if the specified size is negative.
     */
    public EmptyGraphGenerator(int size)
    {
        if (size < 0) {
            throw new IllegalArgumentException(MUST_BE_NON_NEGATIVE);
        }

        this.size = size;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    public void generateGraph(
        Graph<V, E> target,
        VertexFactory<V> vertexFactory,
        Map<String, V> resultMap)
    {
        for (int i = 0; i < size; ++i) {
            target.addVertex(vertexFactory.createVertex());
        }
    }
}

// End EmptyGraphGenerator.java
