package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.game.DevelopmentCardsChangedEvent.DevelopmentCardsChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.playerInfo.DevelopmentCardsAmountWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractDevelopmentCardsAmountWidget implements
        DevelopmentCardsAmountWidget, DevelopmentCardsChangedHandler
{
  protected GamePlayer player;
  protected ComplexPanel rootPanel;

  public AbstractDevelopmentCardsAmountWidget(GamePlayer player)
  {
    this.player = player;
    rootPanel = new VerticalPanel();
    player.developmentCards().addDevelopmentCardsChangedHandler(this);
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
}
