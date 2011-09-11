package org.soc.common.views.widgetsInterface.generic;

import org.soc.common.game.Resource;
import org.soc.common.views.widgetsInterface.generic.ResourceClickedEvent.HasResourceClickedHandlers;

import com.google.gwt.user.client.ui.IsWidget;

public interface ResourceWidget extends IsWidget, HasResourceClickedHandlers
{
  public Resource getResource();
  public ResourceWidget setEnabled(boolean enabled);
}
