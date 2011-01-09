package soc.gwtClient.editor;

import java.util.HashMap;

import soc.common.board.territories.TerritoriesChangedEvent;
import soc.common.board.territories.TerritoriesChangedEventHandler;
import soc.common.board.territories.Territory;
import soc.gwtClient.images.Resources;
import soc.gwtClient.visuals.abstractVisuals.BoardVisual;
import soc.gwtClient.visuals.behaviour.editor.SetTerritoryBehaviour;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

@SuppressWarnings("deprecation")
public class TerritoryPanel extends VerticalPanel implements
        TerritoriesChangedEventHandler
{
    SetTerritoryBehaviour behaviour;
    BoardVisual board;
    final DialogBox dialogBox = new DialogBox();
    private HashMap<Territory, PushButton> territoryButtons = new HashMap<Territory, PushButton>();

    private HandlerManager handlerManager = new HandlerManager(this);

    @Override
    public void fireEvent(GwtEvent<?> event)
    {
        handlerManager.fireEvent(event);
    }

    public HandlerRegistration addBehaviourChangedEventHandler(
            BehaviourChangedHandler handler)
    {
        return handlerManager.addHandler(BehaviourChanged.TYPE, handler);
    }

    public TerritoryPanel(SetTerritoryBehaviour b, final BoardVisual board)
    {
        super();

        behaviour = b;
        this.board = board;

        createAddTerritoryWindow();
        createPanel();

        board.getBoard().getTerritories().addTerritoriesChangedEventHandler(
                this);
    }

    private void createPanel()
    {
        PushButton btnShowTerritories = new PushButton(new Image(Resources
                .icons().showTerritories()));
        btnShowTerritories.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                // Show all territory visuals on TerritoryHexes
                board.showTerritories();
            }
        });
        this.add(btnShowTerritories);

        PushButton btnHideTerritories = new PushButton(new Image(Resources
                .icons().hideTerritories()));
        btnHideTerritories.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                // Show all territory visuals on TerritoryHexes
                board.hideTerritories();
            }
        });
        this.add(btnHideTerritories);

        PushButton btnAddTerritory = new PushButton(new Image(Resources.icons()
                .addTerritory()));
        btnAddTerritory.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                dialogBox.show();
            }
        });
        this.add(btnAddTerritory);

        PushButton btnRemoveTerritory = new PushButton(new Image(Resources
                .icons().removeTerritory()));
        btnRemoveTerritory.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                // Show all territory visuals on TerritoryHexes
                board.hideTerritories();
            }
        });
        this.add(btnRemoveTerritory);

        for (final Territory t : board.getBoard().getTerritories())
        {
            addTerritoryButton(t);
        }
    }

    private void addTerritoryButton(final Territory territory)
    {
        ImageResource img = null;

        if (territory.isMainland())
        {
            img = Resources.icons().mainland();
        }
        else
        {
            switch (territory.getID())
            {
            case 1:
                img = Resources.icons().island1();
                break;
            case 2:
                img = Resources.icons().island2();
                break;
            case 3:
                img = Resources.icons().island3();
                break;
            case 4:
                img = Resources.icons().island4();
                break;
            case 5:
                img = Resources.icons().island5();
                break;
            case 6:
                img = Resources.icons().island6();
                break;
            }
        }

        PushButton btnTerritory = new PushButton(new Image(img));
        btnTerritory.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
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
        final TextBox txtName = new TextBox();
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
                /*
                 * board.getBoard().getTerritories().add( new Territory()
                 * .setName(txtName.getText()) .setID dialogBox.hide();
                 */
            }
        });
    }

    @Override
    public void onTerritoriesChanged(TerritoriesChangedEvent event)
    {
        if (event.getAddedTerritory() != null)
        {
            addTerritoryButton(event.getAddedTerritory());
        }
        if (event.getRemovedTerritory() != null)
        {
            removeTerritoryButton(event.getRemovedTerritory());
        }
    }

    private void removeTerritoryButton(Territory removedTerritory)
    {
        PushButton territoryButton = territoryButtons.get(removedTerritory);
        this.remove(territoryButton);
        territoryButtons.remove(removedTerritory);
    }
}
