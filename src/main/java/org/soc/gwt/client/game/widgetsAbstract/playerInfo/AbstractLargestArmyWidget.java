package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.game.DevelopmentCardsChangedEvent.DevelopmentCardsChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.playerInfo.LargestArmyDetailWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractLargestArmyWidget implements LargestArmyDetailWidget,
        DevelopmentCardsChangedHandler
{
  protected ComplexPanel rootPanel;
  protected GamePlayer player;
  protected GameWidget gameWidget;

  public AbstractLargestArmyWidget(GameWidget gameWidget, GamePlayer player)
  {
    this.gameWidget = gameWidget;
    this.player = player;
    rootPanel = createRootPanel();
    player.playedDevelopmentCards()
            .addDevelopmentCardsChangedHandler(this);
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  /* (non-Javadoc)
   * 
   * @see org.soc.gwt.client.client.game.ILargestArmyWidget#createRootPanel() */
  @Override public ComplexPanel createRootPanel()
  {
    return new VerticalPanel();
  }
}
