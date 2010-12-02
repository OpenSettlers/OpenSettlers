package soc.gwtClient.editor;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.board.Board;
import soc.common.client.behaviour.SetPortBehaviour;
import soc.common.client.behaviour.editor.SetChitBehaviour;
import soc.common.client.behaviour.editor.SetHexBehaviour;
import soc.common.client.behaviour.editor.SetTerritoryBehaviour;
import soc.gwtClient.client.ICenterWidget;
import soc.gwtClient.client.visuals.svg.SvgBoardVisual;

public class SvgMapEditor extends MapEditor 
    implements IBehaviourChangedHandler, ICenterWidget
{
    Label lblStatus = new Label();
    VerticalPanel rootPanel = new VerticalPanel();
    HorizontalPanel horizontalBaseLayout = new HorizontalPanel();

    public SvgMapEditor()
    {
        Board board = new Board(8,8);
        SetChitBehaviour setChitBehaviour = new SetChitBehaviour();
        SetTerritoryBehaviour setTerritoryBehaviour = new SetTerritoryBehaviour();
        setTerritoryBehaviour.setTerritory(board.getTerritories().get(0));
        final SvgBoardVisual boardVisual = new SvgBoardVisual(800,800, board);
        
        final ChitPanel chitPanel = new ChitPanel(setChitBehaviour);
        final PortPanel portPanel = new PortPanel(new SetPortBehaviour());
        final HexPanel p = new HexPanel(new SetHexBehaviour(), setTerritoryBehaviour, setChitBehaviour);
        final HorizontalPanel statusPanel = new HorizontalPanel();
        final TerritoryPanel territoryPanel = new TerritoryPanel(setTerritoryBehaviour, boardVisual);
        statusPanel.add(lblStatus);
        
        p.addBehaviourChangedEventHandler(boardVisual);
        p.addBehaviourChangedEventHandler(this);
        chitPanel.addBehaviourChangedEventHandler(boardVisual);
        chitPanel.addBehaviourChangedEventHandler(this);
        portPanel.addBehaviourChangedEventHandler(boardVisual);
        portPanel.addBehaviourChangedEventHandler(this);
        territoryPanel.addBehaviourChangedEventHandler(boardVisual);
        territoryPanel.addBehaviourChangedEventHandler(this);
        
        horizontalBaseLayout.add(statusPanel);
        horizontalBaseLayout.add(p);
        horizontalBaseLayout.add(chitPanel);
        horizontalBaseLayout.add(portPanel);
        horizontalBaseLayout.add(territoryPanel);
        horizontalBaseLayout.add(boardVisual.getDrawingArea());

        rootPanel.add(statusPanel);
        rootPanel.add(horizontalBaseLayout);
    }

    @Override
    public void onBehaviourChanged(BehaviourChanged behaviourChanged)
    {
        lblStatus.setText(behaviourChanged.getBehaviour().toString());
    }

    @Override
    public Panel getRootWidget()
    {
        return rootPanel;
    }
}