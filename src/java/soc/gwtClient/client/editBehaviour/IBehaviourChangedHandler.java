package soc.gwtClient.client.editBehaviour;

import com.google.gwt.event.shared.EventHandler;

public interface IBehaviourChangedHandler extends EventHandler
{
    void onBehaviourChanged(BehaviourChanged behaviourChanged);
}
