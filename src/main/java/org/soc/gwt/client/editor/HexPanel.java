package org.soc.gwt.client.editor;

import java.util.*;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.actions.ActionOnBoard.SetChitOnBoard;
import org.soc.common.game.actions.ActionOnBoard.SetHexOnBoard;
import org.soc.common.game.actions.ActionOnBoard.SetTerritoryOnBoard;
import org.soc.common.game.hexes.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.user.client.ui.*;

public class HexPanel extends HorizontalPanel implements HasHandlers
{
  private VerticalPanel panel1 = new VerticalPanel();
  private VerticalPanel panel2 = new VerticalPanel();
  private SetHexOnBoard editBehaviour;
  private SetTerritoryOnBoard setTerritoryBehaviour;
  private SetChitOnBoard setChitBehaviour;
  private static List<Hex> editableHexes1 = new ArrayList<Hex>();
  private static List<Hex> editableHexes2 = new ArrayList<Hex>();
  static
  {
    editableHexes1.add(new WheatHex());
    editableHexes1.add(new TimberHex());
    editableHexes1.add(new OreHex());
    editableHexes1.add(new ClayHex());
    editableHexes1.add(new SheepHex());
    editableHexes1.add(new GoldHex());
    editableHexes2.add(new DiamondHex());
    editableHexes2.add(new VolcanoHex());
    editableHexes2.add(new NoneHex());
    editableHexes2.add(new DiscoveryHex());
    editableHexes2.add(new RandomHex());
    editableHexes2.add(new SeaHex());
    editableHexes2.add(new DesertHex());
  }
  private SimpleEventBus eventBus = new SimpleEventBus();

  public HandlerRegistration addBehaviourChangedEventHandler(
          BehaviourChangedHandler handler)
  {
    return eventBus.addHandler(BehaviourChanged.TYPE, handler);
  }
  private Hex createDefaultHex(Hex hex)
  {
    hex.setTerritory(setTerritoryBehaviour.territory());
    if (hex.canHaveChit())
      hex.setChit(setChitBehaviour.chit());
    return hex;
  }
  public HexPanel(SetHexOnBoard behaviour,
          SetTerritoryOnBoard setTerritoryBehaviour,
          SetChitOnBoard setChitBehaviour)
  {
    super();
    editBehaviour = behaviour;
    this.setTerritoryBehaviour = setTerritoryBehaviour;
    this.setChitBehaviour = setChitBehaviour;
    this.add(panel1);
    this.add(panel2);
    for (Hex hex : editableHexes1)
      panel1.add(new HexButton(hex));
    for (Hex hex : editableHexes2)
      panel2.add(new HexButton(hex));
  }

  private class HexButton extends PushButton
  {
    public HexButton(final Hex hex)
    {
      super(new Image(Models.mediumIcon(hex)));
      addClickHandler(new ClickHandler()
      {
        @Override public void onClick(ClickEvent event)
        {
          editBehaviour.setHex(createDefaultHex(hex));
          fireEvent(new BehaviourChanged(editBehaviour));
        }
      });
    }
  }
}
