package soc.gwtClient.editor;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import soc.common.board.Board;
import soc.common.client.behaviour.SetPortBehaviour;
import soc.common.client.behaviour.design.SetChitBehaviour;
import soc.common.client.behaviour.design.SetHexBehaviour;
import soc.common.client.behaviour.design.SetTerritoryBehaviour;
import soc.gwtClient.client.editBehaviour.BehaviourChanged;
import soc.gwtClient.client.editBehaviour.IBehaviourChangedHandler;
import soc.gwtClient.client.visuals.svg.SvgBoardVisual;

public class SvgMapEditor extends MapEditor implements EntryPoint, IBehaviourChangedHandler
{
    Label lblStatus = new Label();

    @Override
    public void onModuleLoad() 
    {
        Board board = new Board(8,8);
        final SvgBoardVisual boardVisual = new SvgBoardVisual(800,800, board);
        
        final HexPanel p = new HexPanel(new SetHexBehaviour());
        final ChitPanel chitPanel = new ChitPanel(new SetChitBehaviour());
        final PortPanel portPanel = new PortPanel(new SetPortBehaviour());
        final HorizontalPanel statusPanel = new HorizontalPanel();
        final TerritoryPanel territoryPanel = new TerritoryPanel(new SetTerritoryBehaviour(), board);
        statusPanel.add(lblStatus);
        
        p.addBehaviourChangedEventHandler(boardVisual);
        p.addBehaviourChangedEventHandler(this);
        chitPanel.addBehaviourChangedEventHandler(boardVisual);
        chitPanel.addBehaviourChangedEventHandler(this);
        portPanel.addBehaviourChangedEventHandler(boardVisual);
        portPanel.addBehaviourChangedEventHandler(this);
        territoryPanel.addBehaviourChangedEventHandler(boardVisual);
        territoryPanel.addBehaviourChangedEventHandler(this);
        
        RootPanel.get().add(statusPanel);
        RootPanel.get().add(p);
        RootPanel.get().add(chitPanel);
        RootPanel.get().add(portPanel);
        RootPanel.get().add(territoryPanel);
        RootPanel.get().add(boardVisual.getDrawingArea());
        
    }

    @Override
    public void onBehaviourChanged(BehaviourChanged behaviourChanged)
    {
        lblStatus.setText(behaviourChanged.getBehaviour().toString());
    }
}