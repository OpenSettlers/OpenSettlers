package soc.common.client.visuals;
  
/*
 * Base implementation of visual representation of a board object.
 * Implements selected, visible and enabled properties. 
 */
public abstract class AbstractPieceVisual implements IPieceVisual
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
    public IPieceVisual setEnabled(boolean enabled)
    {
        this.enabled=enabled;
        
        updateEnabled();

        return this;
    }

    @Override
    public IPieceVisual setSelected(boolean selected)
    {
        this.selected=selected;
        
        updateSelected();

        return this;
    }

    @Override
    public IPieceVisual setVisible(boolean visible)
    {
        this.visible=visible;
        
        updateVisible();

        return this;
    }
}
