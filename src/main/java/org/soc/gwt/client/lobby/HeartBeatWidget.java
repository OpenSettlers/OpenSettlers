package org.soc.gwt.client.lobby;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.animation.Animate;
import org.vaadin.gwtgraphics.client.shape.Circle;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class HeartBeatWidget implements IsWidget
{
    private DrawingArea svgCanvas = new DrawingArea(32, 32);
    private Circle circle = new Circle(16, 16, 5);

    public HeartBeatWidget()
    {
        circle.setFillColor("Green");
        svgCanvas.add(circle);
    }

    @Override
    public Widget asWidget()
    {
        return svgCanvas;
    }

    public void beat()
    {
        new Animate(circle, "radius", 5, 20, 1000).start();
    }
}
