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
  boolean hasFiredAddEvent;
  boolean hasFiredRemoveEvent;
  final Resource wheat = new Wheat();

  @Test public void testHalfCount() {
    ResourceList resources = new ResourceList();
    for (int i = 0; i < 9; i++)
      resources.add(new Wheat());
    assertTrue("Expected 5 resources for ResourceList with 9 resources halfed rounded down",
            resources.halfCount() == 5);
  }
  @Test public void testResourceTypesCount() {
    ResourceList resources = ResourceList.newFiveBasicResources();
    assertTrue(resources.ofType(new Wheat()).size() == 1);
    assertTrue(resources.ofType(new Timber()).size() == 1);
    assertTrue(resources.ofType(new Ore()).size() == 1);
    assertTrue(resources.ofType(new Sheep()).size() == 1);
    assertTrue(resources.ofType(new Clay()).size() == 1);
  }
  @Test public void testHasAtLeast() {
    ResourceList resources = ResourceList.newFiveBasicResources();
    assertTrue(resources.hasAtLeast(ResourceList.newFiveBasicResources()));
  }
  @Test public void testFiresEventWhenAdding() {
    ResourceList resources = new ResourceList();
    resources.addResourcesChangedHandler(new ResourcesChangedHandler() {
      @Override public void onResourcesChanged(ResourcesChangedEvent event) {
        hasFiredAddEvent = true;
        assertTrue(event.getAddedResources().get(0).equals(wheat));
      }
    });
    resources.add(wheat);
    assertTrue(hasFiredAddEvent);
  }
  @Test public void testFiresEventWhenRemoving() {
    ResourceList resources = new ResourceList();
    resources.add(wheat);
    resources.addResourcesChangedHandler(new ResourcesChangedHandler() {
      @Override public void onResourcesChanged(ResourcesChangedEvent event) {
        hasFiredRemoveEvent = true;
        assertTrue(event.getRemovedResources().get(0).equals(wheat));
      }
    });
    resources.remove(wheat);
    assertTrue(hasFiredRemoveEvent);
  }
  @Test public void testSwap() {
    ResourceList from = ResourceList.newFiveBasicResources();
    ResourceList to = new ResourceList();
    to.swapResourcesFrom(ResourceList.newFiveBasicResources(), from);
    assertTrue(to.hasAtLeast(ResourceList.newFiveBasicResources()));
  }
}
