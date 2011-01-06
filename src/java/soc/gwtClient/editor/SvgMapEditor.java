package soc.gwtClient.editor;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.board.Board;
import soc.gwtClient.game.CenterWidget;
import soc.gwtClient.visuals.behaviour.SetPortBehaviour;
import soc.gwtClient.visuals.behaviour.editor.SetChitBehaviour;
import soc.gwtClient.visuals.behaviour.editor.SetHexBehaviour;
import soc.gwtClient.visuals.behaviour.editor.SetTerritoryBehaviour;
import soc.gwtClient.visuals.svg.BoardSvg;

public class SvgMapEditor extends MapEditor 
    implements BehaviourChangedHandler, CenterWidget
{
    Label lblStatus = new Label();
    LayoutPanel rootPanel = new LayoutPanel();
    HorizontalPanel horizontalBaseLayout = new HorizontalPanel();

    public SvgMapEditor()
    {
        Board board = new Board(8,8);
        SetChitBehaviour setChitBehaviour = new SetChitBehaviour();
        SetTerritoryBehaviour setTerritoryBehaviour = new SetTerritoryBehaviour();
        setTerritoryBehaviour.setTerritory(board.getTerritories().get(0));
        final BoardSvg boardVisual = new BoardSvg(800,800, board);
        
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
    public LayoutPanel getRootWidget()
    {
        return rootPanel;
    }
}