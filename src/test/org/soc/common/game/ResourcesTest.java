package org.soc.common.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.soc.common.game.Resource.Clay;
import org.soc.common.game.Resource.Ore;
import org.soc.common.game.Resource.Sheep;
import org.soc.common.game.Resource.Timber;
import org.soc.common.game.Resource.Wheat;
import org.soc.common.game.ResourceList;

public class ResourcesTest {
  @Test public void testHalfCount() {
    ResourceList resources = new ResourceList();
    for (int i = 0; i < 9; i++)
      resources.add(new Wheat());
    assertTrue(resources.halfCount() == 5);
  }
  @Test public void testResourceTypesCount() {
    ResourceList resources = new ResourceList();
    resources.add(new Wheat());
    resources.add(new Timber());
    resources.add(new Ore());
    resources.add(new Sheep());
    resources.add(new Clay());
    assertTrue(resources.ofType(new Wheat()).size() == 1);
    assertTrue(resources.ofType(new Timber()).size() == 1);
    assertTrue(resources.ofType(new Ore()).size() == 1);
    assertTrue(resources.ofType(new Sheep()).size() == 1);
    assertTrue(resources.ofType(new Clay()).size() == 1);
  }
}
