package org.soc.common.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChitListTest {
  @Test public void test68Chits() {
    ChitList chits = ChitList.newStandard4p();
    assertTrue("Expected 4 chits to be a 6 or 8 (red) in a chitbag for a 4p board",
            chits.get68Chits().size() == 4);
    assertTrue("Expected 18 chits for a 4p standard game board", chits.size() == 18);
  }
}
