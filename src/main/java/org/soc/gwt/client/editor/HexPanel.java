package org.soc.gwt.client.editor;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.actions.ActionOnBoard.SetChitOnBoard;
import org.soc.common.game.actions.ActionOnBoard.SetHexOnBoard;
import org.soc.common.game.actions.ActionOnBoard.SetTerritoryOnBoard;
import org.soc.common.game.hexes.ClayHex;
import org.soc.common.game.hexes.DesertHex;
import org.soc.common.game.hexes.DiamondHex;
import org.soc.common.game.hexes.DiscoveryHex;
import org.soc.common.game.hexes.GoldHex;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.hexes.NoneHex;
import org.soc.common.game.hexes.OreHex;
import org.soc.common.game.hexes.RandomHex;
import org.soc.common.game.hexes.SeaHex;
import org.soc.common.game.hexes.SheepHex;
import org.soc.common.game.hexes.TimberHex;
import org.soc.common.game.hexes.VolcanoHex;
import org.soc.common.game.hexes.WheatHex;
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
      super(new Image(R.mediumIcon(hex)));
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
