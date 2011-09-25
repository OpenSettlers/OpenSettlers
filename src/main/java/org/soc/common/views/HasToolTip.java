package org.soc.common.views;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public interface HasToolTip {
  public interface ToolTipView {
  }

  @GenEvent public class ShowToolTip {
    @Order(0) ToolTipView toolTipView;
  }

  @GenEvent public class HideToolTip {
    @Order(0) ToolTipView toolTipView;
  }
}
