package soc.common.client.behaviour.game;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.board.HexPoint;
import soc.common.client.behaviour.IInteractionBehaviour;
import soc.common.client.visuals.IPieceVisual;
import soc.common.client.visuals.board.IBoardVisual;
import soc.common.client.visuals.game.IGameBoardVisual;
import soc.common.client.visuals.game.IHexPointVisual;

public class BuildTownBehaviour extends BuildPointBehaviour
{
    BuildTown buildTown;
    
    public BuildTownBehaviour(BuildTown buildTown)
    {
        super();
        this.buildTown = buildTown;
    }

    @Override
    public void clicked(IPieceVisual pieceVisual, IBoardVisual board)
    {
        
    }
}
