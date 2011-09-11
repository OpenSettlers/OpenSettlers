package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourceList;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.common.views.widgetsInterface.dialogs.LooseCardsDialog;
import org.soc.common.views.widgetsInterface.dialogs.LooseCardsWidget;
import org.soc.common.views.widgetsInterface.generic.ResourceListWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.ResourceListBitmapWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LooseCardsBitmapWidget implements LooseCardsWidget,
        ResourcesChangedHandler
{
  private HorizontalPanel rootPanel = new HorizontalPanel();
  private GamePlayer player;
  private GameWidget gameWidget;
  private ResourceListWidget handCardsWidget;
  private ResourceListWidget cardsToLooseWidget;
  private Label lblPlayerName;
  private Button btnOK = new Button("Loose cards");
  private LooseCardsDialog parent;
  private ResourceList cardsToLoose = new ResourceList();
  private ResourceList handCards;

  public LooseCardsBitmapWidget(GameWidget gameWidget, GamePlayer player,
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
    cardsToLoose.addResourcesChangedHandler(this);
  }
  private void doneLoosingCards()
  {
    parent.doneLoosingCards(this);
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  public void update()
  {
    handCards = player.resources().copy();
    handCardsWidget.setResources(handCards);
    cardsToLoose.clear();
  }
  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
  {
    btnOK.setEnabled(cardsToLoose.size() == player.resources()
            .halfCount());
  }
  @Override public void setVisible(boolean visible)
  {
    rootPanel.setVisible(visible);
  }
  @Override public GamePlayer getPlayer()
  {
    return player;
  }
  @Override public ResourceList getLostCards()
  {
    return cardsToLoose;
  }
}
