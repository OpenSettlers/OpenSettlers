package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.DevelopmentCard.Soldier;
import org.soc.common.game.DevelopmentCardsChangedEvent;
import org.soc.common.game.GamePlayer;
import org.soc.common.views.widgetsInterface.generic.ToolTip;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.playerInfo.AbstractLargestArmyWidget;
import org.soc.gwt.client.game.widgetsBitmap.tooltips.SoldiersToolTip;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class LargestArmyBitmapWidget extends AbstractLargestArmyWidget
        implements MouseOutHandler, MouseOverHandler
{
  Image largestAmryImage = new Image(R.icons().robber16());
  Label amountSoldiers = new Label();
  Soldier soldier = new Soldier();
  ToolTip toolTip;

  public LargestArmyBitmapWidget(GameWidget gameWidget, GamePlayer player)
  {
    super(gameWidget, player);
    toolTip = new SoldiersToolTip(gameWidget, player);
    amountSoldiers.setText(Integer.toString(player
            .playedDevelopmentCards().ofType(new Soldier())
            .size()));
    largestAmryImage.setSize("16px", "16px");
    rootPanel.add(largestAmryImage);
    rootPanel.add(amountSoldiers);
    rootPanel.addDomHandler(this, MouseOutEvent.getType());
    rootPanel.addDomHandler(this, MouseOverEvent.getType());
  }
  @Override public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
  {
    amountSoldiers.setText(Integer.toString(player
            .playedDevelopmentCards().ofType(soldier).size()));
  }
  @Override public void onMouseOut(MouseOutEvent event)
  {
    gameWidget.toolTipManager().hide(toolTip);
  }
  @Override public void onMouseOver(MouseOverEvent event)
  {
    gameWidget.toolTipManager().show(toolTip);
  }
}
