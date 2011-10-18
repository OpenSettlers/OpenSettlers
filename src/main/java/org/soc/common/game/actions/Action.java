package org.soc.common.game.actions;

import java.util.*;

import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.core.property.*;
import org.soc.common.game.*;
import org.soc.common.utils.*;

import com.google.gwt.user.client.ui.*;

public interface Action extends OsModel<Integer> {
  public String toDoMessage();
  public Date dateTimeExecuted();
  public int senderId();
  public Action setSender(int sender);
  public String message();

  public abstract class AbstractAction implements Action {
    protected String message = "No message implemented yet for " + name().value();
    protected Date dateTimeExecuted;
    protected int sender;

    public Date dateTimeExecuted() {
      return dateTimeExecuted;
    }
    @Override public String message() {
      return message;
    }
    @Override public Name name() {
      return new Name.Impl(Util.shortName(this.getClass()));
    }
    public int senderId() {
      return sender;
    }
    public Action setSender(int sender) {
      this.sender = sender;
      return this;
    }
    @Override public Property getProp(Property type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  /* Interface for a widget that shows UI elements representing actions in a turn, such as building
   * a town or ending turn */
  public interface ActionPresenter extends IsWidget
  {
    public GamePlayer getPlayer();
    public ActionPresenter setEnabled(boolean enabled);

    /* Widget interface to create UI based on an action which is allowed in a game. */
    public interface ActionWidgetFactory
    {
      public ActionPresenter createBuildTownWidget();
      public ActionPresenter createBuildCityWidget();
      public ActionPresenter createBuildRoadWidget();
      public ActionPresenter createRollDiceWidget();
      public ActionPresenter createEndTurnWidget();
      public ActionPresenter createBuyDevelopmentCardWidget();
      public ActionPresenter createPlayDevelopmentCardWidget();
      public ActionPresenter createTradeBankWidget();
      public ActionPresenter createTradePlayerWidget();
      public ActionPresenter createClaimVictoryWidget();
    }
  }
}
