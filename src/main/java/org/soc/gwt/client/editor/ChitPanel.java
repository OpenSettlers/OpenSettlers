package org.soc.gwt.client.editor;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.Chit;
import org.soc.common.game.Chit.Chit10;
import org.soc.common.game.Chit.Chit11;
import org.soc.common.game.Chit.Chit12;
import org.soc.common.game.Chit.Chit2;
import org.soc.common.game.Chit.Chit3;
import org.soc.common.game.Chit.Chit4;
import org.soc.common.game.Chit.Chit5;
import org.soc.common.game.Chit.Chit6;
import org.soc.common.game.Chit.Chit8;
import org.soc.common.game.Chit.Chit9;
import org.soc.common.game.Chit.RandomChit;
import org.soc.common.game.actions.ActionOnBoard.SetChitOnBoard;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ChitPanel extends HorizontalPanel implements HasHandlers {
  private VerticalPanel panel1 = new VerticalPanel();
  private VerticalPanel panel2 = new VerticalPanel();
  private SetChitOnBoard editBehaviour;
  private static List<Chit> editableChits1 = new ArrayList<Chit>();
  private static List<Chit> editableChits2 = new ArrayList<Chit>();
  // TODO: Rafeactor into GameSettings
  static {
    editableChits1.add(new Chit2());
    editableChits1.add(new Chit3());
    editableChits1.add(new Chit4());
    editableChits1.add(new Chit5());
    editableChits1.add(new Chit6());
    editableChits2.add(new Chit12());
    editableChits2.add(new Chit11());
    editableChits2.add(new Chit10());
    editableChits2.add(new Chit9());
    editableChits2.add(new Chit8());
    editableChits2.add(new RandomChit());
  }
  private SimpleEventBus eventBus = new SimpleEventBus();

  public HandlerRegistration addBehaviourChangedEventHandler(BehaviourChangedHandler handler) {
    return eventBus.addHandler(BehaviourChanged.TYPE, handler);
  }
  public ChitPanel(SetChitOnBoard behaviour) {
    super();
    editBehaviour = behaviour;
    this.add(panel1);
    this.add(panel2);
    for (Chit chit : editableChits1)
      panel1.add(new ChitButton(chit));
    for (Chit chit : editableChits2)
      panel2.add(new ChitButton(chit));
  }

  private class ChitButton extends PushButton {
    public ChitButton(final Chit chit) {
      super(new Image(R.mediumIcon(chit)));
      addClickHandler(new ClickHandler() {
        @Override public void onClick(ClickEvent event) {
          editBehaviour.setChit(chit);
          fireEvent(new BehaviourChanged(editBehaviour));
        }
      });
    }
  }
}
