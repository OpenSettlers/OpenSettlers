package org.soc.gwt.client.editor;

import com.google.gwt.event.shared.EventHandler;

public interface BehaviourChangedHandler extends EventHandler
{
    void onBehaviourChanged(BehaviourChanged behaviourChanged);
}
