package soc.gwtClient.editor;

import soc.common.board.hexes.AbstractHex;
import soc.common.board.hexes.DesertHex;
import soc.common.board.hexes.DiscoveryHex;
import soc.common.board.hexes.NoneHex;
import soc.common.board.hexes.RandomHex;
import soc.common.board.hexes.ResourceHex;
import soc.common.board.hexes.SeaHex;
import soc.common.board.hexes.VolcanoHex;
import soc.common.board.resources.Clay;
import soc.common.board.resources.Diamond;
import soc.common.board.resources.Gold;
import soc.common.board.resources.Ore;
import soc.common.board.resources.Sheep;
import soc.common.board.resources.Timber;
import soc.common.board.resources.Wheat;
import soc.common.client.behaviour.editor.SetChitBehaviour;
import soc.common.client.behaviour.editor.SetHexBehaviour;
import soc.common.client.behaviour.editor.SetTerritoryBehaviour;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HexPanel extends VerticalPanel implements HasHandlers
{
    private SetHexBehaviour editBehaviour;
    private SetTerritoryBehaviour setTerritoryBehaviour;
    private SetChitBehaviour setChitBehaviour;
    private final AbstractHex wheatHex = new ResourceHex(new Wheat());
    private final AbstractHex timberHex = new ResourceHex(new Timber());
    private final AbstractHex oreHex = new ResourceHex(new Ore());
    private final AbstractHex clayHex = new ResourceHex(new Clay());
    private final AbstractHex sheepHex = new ResourceHex(new Sheep());
    private final AbstractHex goldHex = new ResourceHex(new Gold());
    private final AbstractHex jungleHex = new ResourceHex(new Diamond());
    private final AbstractHex volcanoHex = new VolcanoHex();
    private final AbstractHex noneHex = new NoneHex();
    private final AbstractHex discoveryHex = new DiscoveryHex();
    private final AbstractHex randomHex = new RandomHex();
    private final AbstractHex seaHex = new SeaHex();
    private final AbstractHex desertHex = new DesertHex();

    @SuppressWarnings("deprecation")
    private HandlerManager handlerManager = new HandlerManager(this);

    @SuppressWarnings("deprecation")
    @Override
    public void fireEvent(GwtEvent<?> event)
    {
        handlerManager.fireEvent(event);
    }

    @SuppressWarnings("deprecation")
    public HandlerRegistration addBehaviourChangedEventHandler(
            IBehaviourChangedHandler handler)
    {
        return handlerManager.addHandler(BehaviourChanged.TYPE, handler);
    }

    public void addActivatedHandler(Event event)
    {

    }

    private AbstractHex createDefaultHex(AbstractHex hex)
    {
        hex.setTerritory(setTerritoryBehaviour.getTerritory());
        if (hex instanceof ResourceHex)
        {
            ResourceHex resourceHex = (ResourceHex) hex;
            resourceHex.setChit(setChitBehaviour.getCurrentChit());
        }
        return hex;
    }

    private void vuur()
    {
        fireEvent(new BehaviourChanged(editBehaviour));
    }

    public HexPanel(SetHexBehaviour behaviour,
            SetTerritoryBehaviour setTerritoryBehaviour,
            SetChitBehaviour setChitBehaviour)
    {
        super();

        editBehaviour = behaviour;
        this.setTerritoryBehaviour = setTerritoryBehaviour;
        this.setChitBehaviour = setChitBehaviour;

        PushButton btnTimber = new PushButton(new Image(Resources.icons()
                .timberHex()));
        btnTimber.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(timberHex));
                vuur();
            }
        });
        this.add(btnTimber);

        PushButton btnWheat = new PushButton(new Image(Resources.icons()
                .wheatHex()));
        btnWheat.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(wheatHex));
                vuur();
            }
        });
        this.add(btnWheat);

        PushButton btnOre = new PushButton(
                new Image(Resources.icons().oreHex()));
        btnOre.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(oreHex));
                vuur();
            }
        });
        this.add(btnOre);

        PushButton btnClay = new PushButton(new Image(Resources.icons()
                .clayHex()));
        btnClay.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(clayHex));
                vuur();
            }
        });
        this.add(btnClay);

        PushButton btnSheep = new PushButton(new Image(Resources.icons()
                .sheepHex()));
        btnSheep.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(sheepHex));
                vuur();
            }
        });
        this.add(btnSheep);

        PushButton btnGold = new PushButton(new Image(Resources.icons()
                .goldHex()));
        btnGold.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(goldHex));
                vuur();
            }
        });
        this.add(btnGold);

        PushButton btnJungle = new PushButton(new Image(Resources.icons()
                .jungleHex()));
        btnJungle.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(jungleHex));
                vuur();
            }
        });
        this.add(btnJungle);

        PushButton btnVolcano = new PushButton(new Image(Resources.icons()
                .volcanoHex()));
        btnVolcano.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(volcanoHex));
                vuur();
            }
        });
        this.add(btnVolcano);

        PushButton btnDesert = new PushButton(new Image(Resources.icons()
                .desertHex()));
        btnDesert.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(desertHex));
                vuur();
            }
        });
        this.add(btnDesert);

        PushButton btnSea = new PushButton(
                new Image(Resources.icons().seaHex()));
        btnSea.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(seaHex));
                vuur();
            }
        });
        this.add(btnSea);

        PushButton btnNone = new PushButton(new Image(Resources.icons()
                .noneHex()));
        btnNone.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(noneHex));
                vuur();
            }
        });
        this.add(btnNone);

        PushButton btnRandom = new PushButton(new Image(Resources.icons()
                .randomHex()));
        btnRandom.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(randomHex));
                vuur();
            }
        });
        this.add(btnRandom);

        PushButton btnDiscovery = new PushButton(new Image(Resources.icons()
                .discoveryHex()));
        btnDiscovery.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                editBehaviour.setHex(createDefaultHex(discoveryHex));
                vuur();
            }
        });
        this.add(btnDiscovery);
    }
}
