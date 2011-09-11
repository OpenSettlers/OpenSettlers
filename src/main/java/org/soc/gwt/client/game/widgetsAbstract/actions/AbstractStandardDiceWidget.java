package org.soc.gwt.client.game.widgetsAbstract.actions;

import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.actions.DiceWidget.StandardDiceWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStandardDiceWidget implements StandardDiceWidget
{
  protected GameWidget gameWidget;
  protected AbsolutePanel rootPanel;
  protected GamePlayer player;
  protected boolean enabled = false;

  protected abstract ComplexPanel createRootPanel();
  public AbstractStandardDiceWidget(GameWidget gameWidget, GamePlayer player)
  {
    this.gameWidget = gameWidget;
    this.player = player;
    rootPanel = new AbsolutePanel();
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
}
