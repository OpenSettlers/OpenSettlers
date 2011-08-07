package org.soc.common.game.variants.rules;

import org.soc.common.game.phases.DetermineFirstPlayerGamePhase;
import org.soc.common.game.phases.EndedGamePhase;
import org.soc.common.game.phases.InitialPlacementGamePhase;
import org.soc.common.game.phases.LobbyGamePhase;
import org.soc.common.game.phases.PlayTurnsGamePhase;
import org.soc.common.game.variants.GameRules;

public class BasicGamePhases implements GameRule
{

    @Override
    public String getDescription()
    {
        // TODO fix message
        return null;
    }

    @Override
    public void set(GameRules rules)
    {
        rules.getSupportedPhases().add(new LobbyGamePhase());
        rules.getSupportedPhases().add(new DetermineFirstPlayerGamePhase());
        rules.getSupportedPhases().add(new InitialPlacementGamePhase());
        rules.getSupportedPhases().add(new PlayTurnsGamePhase());
        rules.getSupportedPhases().add(new EndedGamePhase());
    }

}
