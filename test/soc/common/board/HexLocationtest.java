package soc.common.board;

import soc.common.board.HexLocation;
import soc.common.board.HexPoint;

import com.google.gwt.junit.client.GWTTestCase;

public class HexLocationtest extends GWTTestCase
{

    @Override
    public String getModuleName()
    {
        return "soc.common.OSCommon";
    }

    public void testLocation()
    {
        HexLocation l1 = new HexLocation(0, 0);
        HexLocation l2 = new HexLocation(0, 1);
        HexLocation l3 = new HexLocation(1, 0);

        HexPoint poin1 = new HexPoint(l1, l2, l3);
        HexPoint poin2 = new HexPoint(l2, l1, l3);
        HexPoint poin3 = new HexPoint(l2, l3, l1);
        assertEquals(poin1, poin2);
        assertEquals(poin2, poin3);
        assertEquals(poin1, poin3);

        assertEquals(poin1.hashCode(), poin2.hashCode());
        assertEquals(poin2.hashCode(), poin3.hashCode());
        assertEquals(poin1.hashCode(), poin3.hashCode());
    }

    /**
     * Add as many tests as you like.
     */
    public void testSimple()
    { // (3)
        assertTrue(true);
    }
}
