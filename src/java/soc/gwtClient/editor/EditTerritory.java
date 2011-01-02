package soc.gwtClient.editor;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditTerritory extends DialogBox
{

    public EditTerritory()
    {
        setWidth("617px");
        setHTML("Territory properties");

        DockPanel dockPanel = new DockPanel();
        setWidget(dockPanel);
        dockPanel.setSize("598px", "397px");

        VerticalPanel verticalPanel = new VerticalPanel();
        dockPanel.add(verticalPanel, DockPanel.WEST);

        CellList territoryList = new CellList(new TextCell());
        verticalPanel.add(territoryList);
        territoryList.setWidth("85px");
    }

}
