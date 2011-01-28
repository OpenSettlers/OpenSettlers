package soc.common.game.variants.rules;

import soc.common.game.gamePhase.DetermineFirstPlayerGamePhase;
import soc.common.game.gamePhase.EndedGamePhase;
import soc.common.game.gamePhase.InitialPlacementGamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.variants.GameRules;

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
