package org.soc.common.board.settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.game.variants.Standard;
import org.soc.common.game.variants.Variant;


/*
 * Each setting has a corresponding concrete class implementing BoardSetting. When a 
 * setting has no setter, you can safely assume the setting is never null. When a 
 * setting does have a setter, you must assume the setting can be null. 
 * 
 * Keeps an internal list of settings. When a set[SettingName] property setter is called,
 * the old reference is removed from the list and the new reference is added.
 * 
 * Call reset() to re-initialize all the settings of this board. It will then walk every
 * setting in the list of settings, and call set(this) on each setting.
 * 
 *  To add a setting, create a class implementing BoardSetting, and add a field to this class.
 *  Add a getter, and only add a setter when the setting is optional. 
 *  
 */
public class BoardSettings implements Serializable, Iterable<BoardSetting>
{
    private static final long serialVersionUID = 439520618383077352L;

    private List<Class<? extends Variant>> supportedVariants = new ArrayList<Class<? extends Variant>>();
    private List<BoardSetting> settings = new ArrayList<BoardSetting>();

    // Non-nullable settings
    private AmountPlayers amountPlayers = new AmountPlayers();
    private VpToWin vpToWin = new VpToWin();

    // Nullable settings
    private UsesTradeRoutes useTradeRoutes = null;
    private UsePlacePortsPhase usePlacePortsPhase = null;

    public BoardSettings()
    {
        settings.add(amountPlayers);
        settings.add(vpToWin);

        supportedVariants.add(Standard.class);
    }

    @Override
    public Iterator<BoardSetting> iterator()
    {
        return settings.iterator();
    }

    public void setAllSettings()
    {
        for (BoardSetting setting : settings)
            setting.set(this);
    }

    public List<Class<? extends Variant>> getSupportedVariants()
    {
        return supportedVariants;
    }

    private void addSetting(BoardSetting setting)
    {
        settings.add(setting);
    }

    private void removeSetting(BoardSetting setting)
    {
        if (settings.contains(setting))
            settings.remove(setting);
    }

    /**
     * @return the useTradeRoutes
     */
    public UsesTradeRoutes getUseTradeRoutes()
    {
        return useTradeRoutes;
    }

    /**
     * @param useTradeRoutes
     *            the useTradeRoutes to set
     */
    public BoardSettings setUseTradeRoutes(UsesTradeRoutes useTradeRoutes)
    {
        removeSetting(this.useTradeRoutes);
        this.useTradeRoutes = useTradeRoutes;
        addSetting(useTradeRoutes);
        return this;
    }

    /**
     * @return the usePlacePortsPhase
     */
    public UsePlacePortsPhase getUsePlacePortsPhase()
    {
        return usePlacePortsPhase;
    }

    /**
     * @param usePlacePortsPhase
     *            the usePlacePortsPhase to set
     */
    public BoardSettings setUsePlacePortsPhase(
            UsePlacePortsPhase usePlacePortsPhase)
    {
        removeSetting(this.usePlacePortsPhase);
        this.usePlacePortsPhase = usePlacePortsPhase;
        addSetting(usePlacePortsPhase);
        return this;
    }

    /**
     * @return the amountPlayers
     */
    public AmountPlayers getAmountPlayers()
    {
        return amountPlayers;
    }

    /**
     * @return the vpToWin
     */
    public VpToWin getVpToWin()
    {
        return vpToWin;
    }
}
