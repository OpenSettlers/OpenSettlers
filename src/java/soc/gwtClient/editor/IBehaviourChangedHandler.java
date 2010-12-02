package soc.gwtClient.editor;

import com.google.gwt.event.shared.EventHandler;

public interface IBehaviourChangedHandler extends EventHandler
{
    void onBehaviourChanged(BehaviourChanged behaviourChanged);
}
