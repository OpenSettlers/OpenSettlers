package soc.gwtClient.main;

import soc.gwtClient.game.ICenterWidget;

import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class JavadocPanel extends LayoutPanel implements ICenterWidget
{
    private Frame frame;

    public JavadocPanel()
    {
        super();
        frame = new Frame("documentation/javadoc/index.html");
        frame.setWidth("100%");
        frame.setHeight("100%");
        // initWidget(frame);
        this.add(frame);
    }

    @Override
    public Widget getRootWidget()
    {
        return this;
    }

}
