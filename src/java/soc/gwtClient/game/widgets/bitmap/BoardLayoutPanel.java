package soc.gwtClient.game.widgets.bitmap;

import org.vaadin.gwtgraphics.client.DrawingArea;

import com.google.gwt.user.client.ui.LayoutPanel;

public class BoardLayoutPanel extends LayoutPanel
{
    DrawingArea canvas;
    
    /**
     * @param canvas the canvas to set
     */
    public void setCanvas(DrawingArea canvas)
    {
        this.canvas = canvas;
        this.add(canvas);
    }

    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.LayoutPanel#onResize()
     */
    @Override
    public void onResize()
    {
        super.onResize();
        canvas.setSize(this.getOffsetWidth() + "px", this.getOffsetHeight()+ "px");
    }
    
}
