package org.soc.gwt.client.editor;

import org.soc.common.game.actions.ActionOnBoard.SetChitOnBoard;
import org.soc.common.game.actions.ActionOnBoard.SetHexOnBoard;
import org.soc.common.game.actions.ActionOnBoard.SetPortOnBoard;
import org.soc.common.game.actions.ActionOnBoard.SetTerritoryOnBoard;
import org.soc.common.game.board.Board;
import org.soc.common.views.widgetsInterface.main.CenterWidget;
import org.soc.gwt.client.game.widgetsSvg.visuals.BoardSvg;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;

public class SvgMapEditor extends MapEditor implements BehaviourChangedHandler,
        CenterWidget
{
  Label lblStatus = new Label();
  LayoutPanel rootPanel = new LayoutPanel();
  HorizontalPanel horizontalBaseLayout = new HorizontalPanel();

  public SvgMapEditor()
  {
    Board board = new Board(8, 8);
    board.initialize();
    SetChitOnBoard setChitBehaviour = new SetChitOnBoard();
    SetTerritoryOnBoard setTerritoryBehaviour = new SetTerritoryOnBoard();
    setTerritoryBehaviour.setTerritory(board.getTerritories().get(0));
    final BoardSvg boardVisual = new BoardSvg(800, 800, board);
    final ChitPanel chitPanel = new ChitPanel(setChitBehaviour);
    final PortPanel portPanel = new PortPanel(new SetPortOnBoard());
    final HexPanel p = new HexPanel(new SetHexOnBoard(),
            setTerritoryBehaviour, setChitBehaviour);
    final HorizontalPanel statusPanel = new HorizontalPanel();
    final TerritoryPanel territoryPanel = new TerritoryPanel(
            setTerritoryBehaviour, boardVisual);
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
  @Override public void onBehaviourChanged(BehaviourChanged behaviourChanged)
  {
    lblStatus.setText(behaviourChanged.getBehaviour().toString());
  }
  @Override public LayoutPanel getRootWidget()
  {
    return rootPanel;
  }
}