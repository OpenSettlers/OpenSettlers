package soc.gwtClient.game.widgetsAbstract.visuals;

import soc.gwtClient.game.widgetsInterface.visuals.PieceVisual;
  
/*
 * Base implementation of visual representation of a board object.
 * Implements selected, visible and enabled properties. 
 */
public abstract class AbstractPieceVisual implements PieceVisual
{
    protected boolean visible = true;
    protected boolean selected = false;
    protected boolean enabled = true;
    
    protected void updateSelected() {}
    protected void updateVisible() {}
    protected void updateEnabled() {}
    
    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    @Override
    public boolean isSelected()
    {
        return selected;
    }

    @Override
    public boolean isVisible()
    {        
        return visible;
    }

    @Override
    public PieceVisual setEnabled(boolean enabled)
    {
        this.enabled=enabled;
        
        updateEnabled();

        return this;
    }

    @Override
    public PieceVisual setSelected(boolean selected)
    {
        this.selected=selected;
        
        updateSelected();

        return this;
    }

    @Override
    public PieceVisual setVisible(boolean visible)
    {
        this.visible=visible;
        
        updateVisible();

        return this;
    }
}
