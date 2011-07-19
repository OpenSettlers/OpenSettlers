package org.soc.gwt.client.editor;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.board.hexes.ClayHex;
import org.soc.common.board.hexes.DesertHex;
import org.soc.common.board.hexes.DiamondHex;
import org.soc.common.board.hexes.DiscoveryHex;
import org.soc.common.board.hexes.GoldHex;
import org.soc.common.board.hexes.Hex;
import org.soc.common.board.hexes.NoneHex;
import org.soc.common.board.hexes.OreHex;
import org.soc.common.board.hexes.RandomHex;
import org.soc.common.board.hexes.SeaHex;
import org.soc.common.board.hexes.SheepHex;
import org.soc.common.board.hexes.TimberHex;
import org.soc.common.board.hexes.VolcanoHex;
import org.soc.common.board.hexes.WheatHex;
import org.soc.common.views.behaviour.board.SetChitBehaviour;
import org.soc.common.views.behaviour.board.SetHexBehaviour;
import org.soc.common.views.behaviour.board.SetTerritoryBehaviour;
import org.soc.gwt.client.images.Resources;


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
    private SetHexBehaviour editBehaviour;
    private SetTerritoryBehaviour setTerritoryBehaviour;
    private SetChitBehaviour setChitBehaviour;

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
        hex.setTerritory(setTerritoryBehaviour.getTerritory());

        if (hex.canHaveChit())
            hex.setChit(setChitBehaviour.getCurrentChit());

        return hex;
    }

    public HexPanel(SetHexBehaviour behaviour,
                    SetTerritoryBehaviour setTerritoryBehaviour,
                    SetChitBehaviour setChitBehaviour)
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
            super(new Image(Resources.mediumIcon(hex)));

            addClickHandler(new ClickHandler()
            {
                @Override
                public void onClick(ClickEvent event)
                {
                    editBehaviour.setHex(createDefaultHex(hex));
                    fireEvent(new BehaviourChanged(editBehaviour));
                }
            });
        }
    }
}
