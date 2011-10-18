package org.soc.gwt.client.editor;

import java.util.*;

import org.soc.common.core.GenericList.Adds.*;
import org.soc.common.core.GenericList.Removes.*;
import org.soc.common.game.*;
import org.soc.common.game.actions.ActionOnBoard.SetTerritoryOnBoard;
import org.soc.common.views.widgetsInterface.visuals.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.resources.client.*;
import com.google.gwt.user.client.ui.*;

public class TerritoryPanel extends VerticalPanel
{
  SetTerritoryOnBoard behaviour;
  BoardVisual board;
  final DialogBox dialogBox = new DialogBox();
  private HashMap<Territory, PushButton> territoryButtons = new HashMap<Territory, PushButton>();
  private HandlerManager handlerManager = new HandlerManager(this);

  @Override public void fireEvent(GwtEvent<?> event)
  {
    handlerManager.fireEvent(event);
  }
  public HandlerRegistration addBehaviourChangedEventHandler(
          BehaviourChangedHandler handler)
  {
    return handlerManager.addHandler(BehaviourChanged.TYPE, handler);
  }
  public TerritoryPanel(SetTerritoryOnBoard b, final BoardVisual board)
  {
    super();
    behaviour = b;
    this.board = board;
    createAddTerritoryWindow();
    createPanel();
    board.board().getTerritories().addAddedHandler(new Added<Territory>() {
      @Override public void added(Territory item) {
        addTerritoryButton(item);
      }
    });
    board.board().getTerritories().addRemovedHandler(new Removed<Territory>() {
      @Override public void removed(Territory item) {
        removeTerritoryButton(item);
      }
    });
  }
  private void createPanel()
  {
    PushButton btnShowTerritories = new PushButton(new Image(R
            .icons().showTerritories32()));
    btnShowTerritories.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        // Show all territory visuals on TerritoryHexes
        board.showTerritories();
      }
    });
    this.add(btnShowTerritories);
    PushButton btnHideTerritories = new PushButton(new Image(R
            .icons().hideTerritories32()));
    btnHideTerritories.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        // Show all territory visuals on TerritoryHexes
        board.hideTerritories();
      }
    });
    this.add(btnHideTerritories);
    PushButton btnAddTerritory = new PushButton(new Image(R.icons()
            .addTerritory()));
    btnAddTerritory.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        dialogBox.show();
      }
    });
    this.add(btnAddTerritory);
    PushButton btnRemoveTerritory = new PushButton(new Image(R
            .icons().removeTerritory32()));
    btnRemoveTerritory.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        // Show all territory visuals on TerritoryHexes
        board.hideTerritories();
      }
    });
    this.add(btnRemoveTerritory);
    for (final Territory t : board.board().getTerritories())
    {
      addTerritoryButton(t);
    }
  }
  private void addTerritoryButton(final Territory territory)
  {
    ImageResource img = null;
    if (territory.isMainland())
    {
      img = R.icons().mainland32();
    } else
    {
      switch (territory.id())
      {
        case 1:
          img = R.icons().island132();
          break;
        case 2:
          img = R.icons().island232();
          break;
        case 3:
          img = R.icons().island332();
          break;
        case 4:
          img = R.icons().island432();
          break;
        case 5:
          img = R.icons().island532();
          break;
        case 6:
          img = R.icons().island632();
          break;
      }
    }
    PushButton btnTerritory = new PushButton(new Image(img));
    btnTerritory.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        behaviour.setTerritory(territory);
        fireEvent(new BehaviourChanged(behaviour));
      }
    });
    this.add(btnTerritory);
    territoryButtons.put(territory, btnTerritory);
  }
  private void createAddTerritoryWindow()
  {
    // create addTerritory dialogbox
    dialogBox.setText("Add a new territory");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    final Button addButton = new Button("Add territory");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    final Label textToServerLabel = new Label();
    final HTML serverResponseLabel = new HTML();
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
    dialogVPanel.add(textToServerLabel);
    dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
    dialogVPanel.add(serverResponseLabel);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(addButton);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);
    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler()
    {
      public void onClick(ClickEvent event)
      {
        dialogBox.hide();
      }
    });
    closeButton.addClickHandler(new ClickHandler()
    {
      public void onClick(ClickEvent event)
      {
        /* board.getBoard().getTerritories().add( new Territory() .setName(txtName.getText()) .setID
         * dialogBox.hide(); */
      }
    });
  }
  private void removeTerritoryButton(Territory removedTerritory)
  {
    PushButton territoryButton = territoryButtons.get(removedTerritory);
    this.remove(territoryButton);
    territoryButtons.remove(removedTerritory);
  }
}
