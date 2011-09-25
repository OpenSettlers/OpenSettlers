package org.soc.gwt.client.game.actions;

import java.util.HashMap;

import org.soc.common.game.DevelopmentCard;
import org.soc.common.game.actions.PlayDevelopmentCard.PlayDevelopmentCardView;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent;
import org.soc.common.game.actions.WantsPlayDevelopmentCardEvent.WantsPlayDevelopmentCardHandler;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget;
import org.soc.common.views.widgetsInterface.developmentCards.DevelopmentCardWidget.DevelopmentCardWidgetFactory;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PlayDevelopmentCardGwt implements PlayDevelopmentCardView {
  protected AbsolutePanel rootPanel = new AbsolutePanel();
  protected PopupPanel menuBar = new PopupPanel();
  protected VerticalPanel verticalPanel = new VerticalPanel();
  protected PushButton btnPlayDevelopmentCard = new PushButton(new Image(
          R.icons().developmentCardBack48()));
  protected Label lblAmountDvelopmentCards = new Label();
  protected boolean isMenubarShown = false;
  protected DevelopmentCardWidgetFactory devCardWidgetFactory;
  protected HashMap<DevelopmentCard, DevelopmentCardWidget> devCardsWidgets = new HashMap<DevelopmentCard, DevelopmentCardWidget>();
  private EventBus eventBus = new SimpleEventBus();

  public PlayDevelopmentCardGwt() {
    super();
    //devCardWidgetFactory = new DevelopmentCardBitmapWidgetFactory(
    //        gameWidget);
    //TODO: fix devcard factory
    rootPanel.add(btnPlayDevelopmentCard);
    rootPanel.add(lblAmountDvelopmentCards);
    menuBar.add(verticalPanel);
    btnPlayDevelopmentCard.addClickHandler(new ClickHandler() {
      @Override public void onClick(ClickEvent event) {
        if (isMenubarShown) {
          menuBar.hide();
        } else {
          menuBar.setPopupPosition(
                  btnPlayDevelopmentCard.getOffsetWidth(),
                  btnPlayDevelopmentCard.getOffsetHeight()
                          - menuBar.getAbsoluteTop());
          menuBar.show();
        }
        isMenubarShown = !isMenubarShown;
      }
    });
  }
  @Override public void remove(DevelopmentCard developmentCard) {
    DevelopmentCardWidget widget = devCardsWidgets.get(developmentCard);
    verticalPanel.remove(widget);
  }
  @Override public void add(DevelopmentCard developmentCard) {
    DevelopmentCardWidget devCardWidget = developmentCard
            .createPlayCardWidget(devCardWidgetFactory);
    verticalPanel.add(devCardWidget);
    devCardsWidgets.put(developmentCard, devCardWidget);
  }
  @Override public void enable() {
    btnPlayDevelopmentCard.setEnabled(true);
  }
  @Override public void disable() {
    btnPlayDevelopmentCard.setEnabled(false);
  }
  @Override public void setAmount(int amountDevelopmentCards) {
    lblAmountDvelopmentCards.setText(Integer.toString(amountDevelopmentCards));
  }
  @Override public HandlerRegistration addWantsPlayDevelopmentCardHandler(
          WantsPlayDevelopmentCardHandler handler) {
    return eventBus.addHandler(WantsPlayDevelopmentCardEvent.getType(), handler);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
}
