package soc.gwtClient.game.abstractWidgets;

import soc.common.board.resources.Resource;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface IBankStockPanel extends IsWidget
{
    public ComplexPanel createRootPanel();
    public IBankStockResourceWidget createBankStockResourceWidget(Resource resource);
}
