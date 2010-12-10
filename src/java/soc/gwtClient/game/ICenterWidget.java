package soc.gwtClient.game;

import com.google.gwt.user.client.ui.Panel;

/*
 * Widget to be placed in the center of the app, between the titlebar and the statusbar 
 * For instance: map editor, game, welcome panel
 */
public interface ICenterWidget
{
    public Panel getRootWidget();
}
