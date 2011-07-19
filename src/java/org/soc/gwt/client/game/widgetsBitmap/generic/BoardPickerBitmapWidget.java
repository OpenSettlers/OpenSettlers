package org.soc.gwt.client.game.widgetsBitmap.generic;

import java.util.List;

import org.soc.common.board.Board;
import org.soc.common.server.entities.BoardProvider;
import org.soc.common.server.entities.ConstructorBoardProvider;
import org.soc.common.views.widgetsInterface.generic.BoardChangedEvent;
import org.soc.common.views.widgetsInterface.generic.BoardChangedEventHandler;
import org.soc.common.views.widgetsInterface.generic.BoardPicker;
import org.soc.gwt.client.game.widgetsSvg.BoardViewerSvgWidget;


import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/*
 * Displays a table with detailed board information. When the user picks a board
 * from the table, the board is previewed in a small encapsulated board viewer widget
 */
public class BoardPickerBitmapWidget extends Composite implements BoardPicker
{
    private EventBus eventBus = new SimpleEventBus();
    private HorizontalPanel rootPanel = new HorizontalPanel();
    private BoardProvider boardProvider = new ConstructorBoardProvider();
    private CellTable<Board> cellTable;
    private List<Board> boards = boardProvider.getAllBoards();
    private SimplePanel panelBoardPreview;
    private Board selectedBoard = null;
    private ProvidesKey<Board> boardKeyProvider = new ProvidesKey<Board>()
    {
        @Override
        public Object getKey(Board item)
        {
            return item == null ? null : item.getId();
        }
    };
    private SingleSelectionModel selectionModel;

    public BoardPickerBitmapWidget()
    {
        super();

        initWidget(rootPanel);

        cellTable = new CellTable<Board>(boardKeyProvider);
        selectionModel = new SingleSelectionModel<Board>();
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler()
        {
            @Override
            public void onSelectionChange(SelectionChangeEvent event)
            {
                if (selectionModel.getSelectedObject() != null)
                {
                    selectedBoard = (Board) selectionModel.getSelectedObject();
                    int width = panelBoardPreview.getOffsetWidth();
                    int height = panelBoardPreview.getOffsetHeight();
                    BoardViewerSvgWidget widget = new BoardViewerSvgWidget(
                                    selectedBoard, width, height);
                    panelBoardPreview.setWidget(widget);
                } else
                {
                    panelBoardPreview.setWidget(null);
                    selectedBoard = null;
                }

                eventBus.fireEvent(new BoardChangedEvent(selectedBoard));
            }
        });
        cellTable.setSelectionModel(selectionModel);
        Column<Board, ?> columnVP = new Column<Board, Number>(new NumberCell())
        {
            @Override
            public Number getValue(Board object)
            {
                return object.getBoardSettings().getVpToWin().getVpToWin();
            }
        };
        cellTable.addColumn(columnVP, "Points");

        TextColumn designerColumn = new TextColumn<Board>()
        {
            @Override
            public String getValue(Board object)
            {
                return object.getDesigner();
            }
        };
        cellTable.addColumn(designerColumn, "Designer");

        TextColumn nameColumn = new TextColumn<Board>()
        {
            @Override
            public String getValue(Board object)
            {
                return object.getName();
            }
        };
        cellTable.addColumn(nameColumn, "Name");

        TextColumn amountPlayersColumn = new TextColumn<Board>()
        {
            @Override
            public String getValue(Board object)
            {
                return Integer.toString(object.getBoardSettings()
                                .getAmountPlayers().getAmountPlayers());
            }
        };
        cellTable.addColumn(amountPlayersColumn, "# players");

        rootPanel.add(cellTable);
        cellTable.setWidth("15em");
        cellTable.setRowCount(boards.size());
        cellTable.setRowData(0, boards);
        panelBoardPreview = new SimplePanel();
        rootPanel.add(panelBoardPreview);
        panelBoardPreview.setSize("500px", "500px");
    }

    @Override
    public HandlerRegistration addBoardChangedHandler(
                    BoardChangedEventHandler handler)
    {
        return eventBus.addHandler(BoardChangedEvent.TYPE, handler);
    }

    @Override
    public Board getSelectedBoard()
    {
        return selectedBoard;
    }

    @Override
    public boolean hasBoardSelected()
    {
        return selectedBoard != null;
    }

    @Override
    public void selectFirst()
    {
        selectionModel.setSelected(boardProvider.getAllBoards().get(0), true);
    }
}