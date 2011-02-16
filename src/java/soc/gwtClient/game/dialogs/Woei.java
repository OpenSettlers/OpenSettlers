package soc.gwtClient.game.dialogs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Woei extends Composite
{

    private static final Binder binder = GWT.create(Binder.class);

    interface Binder extends UiBinder<Widget, Woei>
    {
    }

    public Woei()
    {
        initWidget(binder.createAndBindUi(this));
    }

}
