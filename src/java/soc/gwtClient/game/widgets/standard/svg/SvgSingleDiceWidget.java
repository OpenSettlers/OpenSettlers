package soc.gwtClient.game.widgets.standard.svg;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.shape.Circle;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.user.client.ui.Widget;

import soc.gwtClient.game.abstractWidgets.ISingleDiceWidget;

public class SvgSingleDiceWidget implements ISingleDiceWidget
{
    private int offset = 3;
    private int diceOffset = 7;
    private DrawingArea canvas;
    private Circle dot1;
    private Circle dot2;
    private Circle dot3;
    private Circle dot4;
    private Circle dot5;
    private Circle dot6;
    private Circle dot7;
    private Group group;
    private Rectangle rectangle;
    
    public SvgSingleDiceWidget()
    {
        canvas = new DrawingArea(56, 56);
        group = new Group();
        rectangle = new Rectangle(offset, offset, 50, 50);
        rectangle.setRoundedCorners(10);
        rectangle.setStrokeWidth(3);
        dot1 = createCircle(diceOffset + 8,diceOffset + 9);
        dot2 = createCircle(diceOffset + 8,diceOffset + 21);
        dot3 = createCircle(diceOffset + 8,diceOffset + 33);
        dot4 = createCircle(diceOffset + 34,diceOffset + 9);
        dot5 = createCircle(diceOffset + 34,diceOffset + 21);
        dot6 = createCircle(diceOffset + 34,diceOffset + 33);
        dot7 = createCircle(diceOffset + 22,diceOffset + 21);
        group.add(rectangle);
        group.add(dot1);
        group.add(dot2);
        group.add(dot3);
        group.add(dot4);
        group.add(dot5);
        group.add(dot6);
        group.add(dot7);
        canvas.add(group);
    }
    private Circle createCircle(int x, int y)
    {
        int radius = 4;
        Circle result = new Circle(x, y, radius);
        result.setFillColor("Black");
        return result;
    }
    public void setNumber(int number)
    {
        switch (number)
        {
        case 1:
            dot1.setVisible(false);
            dot2.setVisible(false);
            dot3.setVisible(false);
            dot4.setVisible(false);
            dot5.setVisible(false);
            dot6.setVisible(false);
            dot7.setVisible(true);
            break;
        case 2:
            dot1.setVisible(true);
            dot2.setVisible(false);
            dot3.setVisible(false);
            dot4.setVisible(false);
            dot5.setVisible(false);
            dot6.setVisible(true);
            dot7.setVisible(false);
            break;
        case 3:
            dot1.setVisible(true);
            dot2.setVisible(false);
            dot3.setVisible(false);
            dot4.setVisible(false);
            dot5.setVisible(false);
            dot6.setVisible(true);
            dot7.setVisible(true);
            break;
        case 4:
            dot1.setVisible(false);
            dot2.setVisible(false);
            dot3.setVisible(false);
            dot4.setVisible(false);
            dot5.setVisible(false);
            dot6.setVisible(false);
            dot7.setVisible(true);
            break;
        case 5:
            dot1.setVisible(true);
            dot2.setVisible(false);
            dot3.setVisible(true);
            dot4.setVisible(true);
            dot5.setVisible(false);
            dot6.setVisible(true);
            dot7.setVisible(true);
            break;
        case 6:
            dot1.setVisible(true);
            dot2.setVisible(true);
            dot3.setVisible(true);
            dot4.setVisible(true);
            dot5.setVisible(true);
            dot6.setVisible(true);
            dot7.setVisible(false);
            break;
        }
    }
    @Override
    public Widget asWidget()
    {
        return canvas;
    }
}
