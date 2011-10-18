package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.AddsList.*;
import org.soc.common.core.GenericList.RemovesList.*;
import org.soc.common.game.*;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.views.widgetsInterface.dialogs.*;
import org.soc.common.views.widgetsInterface.generic.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsBitmap.generic.*;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class LooseCardsBitmapWidget implements LooseCardsWidget {
  private HorizontalPanel rootPanel = new HorizontalPanel();
  private GamePlayer player;
  private GameWidget gameWidget;
  private ResourceListWidget handCardsWidget;
  private ResourceListWidget cardsToLooseWidget;
  private Label lblPlayerName;
  private Button btnOK = new Button("Loose cards");
  private LooseCardsDialog parent;
  private MutableResourceList cardsToLoose = new MutableResourceListImpl();
  private MutableResourceList handCards;

  public LooseCardsBitmapWidget(GameWidget gameWidget, final GamePlayer player,
          final LooseCardsDialog parent)
  {
    super();
    this.gameWidget = gameWidget;
    this.player = player;
    this.parent = parent;
    handCards = player.resources().copy();
    lblPlayerName = new Label(player.user().name());
    handCardsWidget = new ResourceListBitmapWidget(handCards, cardsToLoose,
            null);
    handCardsWidget.setHeight("3em");
    cardsToLooseWidget = new ResourceListBitmapWidget(cardsToLoose,
            handCards, null);
    cardsToLooseWidget.setHeight("3em");
    HorizontalPanel spacer = new HorizontalPanel();
    spacer.setWidth("1em");
    rootPanel.add(lblPlayerName);
    rootPanel.add(handCardsWidget);
    rootPanel.add(spacer);
    rootPanel.add(cardsToLooseWidget);
    rootPanel.add(btnOK);
    btnOK.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        doneLoosingCards();
      }
    });
    cardsToLoose.addListRemovedHandler(new ListRemoved<Resource>() {
      @Override public void listRemoved(ImmutableList<Resource> items) {
        updateButton();
      }
    });
    cardsToLoose.addListAddedHandler(new ListAdded<Resource>() {
      @Override public void listAdded(ImmutableList<Resource> items) {
        updateButton();
      }
    });
  }
  private void doneLoosingCards() {
    parent.doneLoosingCards(this);
  }
  @Override public Widget asWidget() {
    return rootPanel;
  }
  public void update() {
    handCards = player.resources().copy();
    handCardsWidget.setResources(handCards);
    cardsToLoose.clear();
  }
  public void updateButton() {
    btnOK.setEnabled(cardsToLoose.size() == player.resources().halfCount());
  }
  @Override public void setVisible(boolean visible) {
    rootPanel.setVisible(visible);
  }
  @Override public GamePlayer getPlayer() {
    return player;
  }
  @Override public ResourceList getLostCards() {
    return cardsToLoose;
  }
}
