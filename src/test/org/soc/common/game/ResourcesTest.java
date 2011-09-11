package org.soc.common.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.soc.common.game.Resource.Clay;
import org.soc.common.game.Resource.Ore;
import org.soc.common.game.Resource.Sheep;
import org.soc.common.game.Resource.Timber;
import org.soc.common.game.Resource.Wheat;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;

public class ResourcesTest {
  boolean hasFired;

  @Test public void testHalfCount() {
    ResourceList resources = new ResourceList();
    for (int i = 0; i < 9; i++)
      resources.add(new Wheat());
    assertTrue(resources.halfCount() == 5);
  }
  @Test public void testResourceTypesCount() {
    ResourceList resources = createFromFiveBasicResources();
    assertTrue(resources.ofType(new Wheat()).size() == 1);
    assertTrue(resources.ofType(new Timber()).size() == 1);
    assertTrue(resources.ofType(new Ore()).size() == 1);
    assertTrue(resources.ofType(new Sheep()).size() == 1);
    assertTrue(resources.ofType(new Clay()).size() == 1);
  }
  @Test public void testHasAtLeast() {
    ResourceList resources = createFromFiveBasicResources();
    assertTrue(resources.hasAtLeast(createFromFiveBasicResources()));
  }
  @Test public void testFiresEvents() {
    ResourceList resources = new ResourceList();
    resources.addResourcesChangedHandler(new ResourcesChangedHandler() {
      @Override public void onResourcesChanged(ResourcesChangedEvent event) {
        hasFired = true;
        assertTrue(event.getAddedResources().get(0).equals(new Wheat()));
      }
    });
    resources.add(new Wheat());
    assertTrue(hasFired);
  }
  private static ResourceList createFromFiveBasicResources() {
    return new ResourceList()
            .add(new Wheat())
            .add(new Timber())
            .add(new Ore())
            .add(new Sheep())
            .add(new Clay());
  }
}
