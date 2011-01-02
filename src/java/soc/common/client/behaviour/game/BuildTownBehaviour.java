package soc.common.client.behaviour.game;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.board.HexPoint;
import soc.common.client.behaviour.InteractionBehaviour;
import soc.common.client.visuals.PieceVisual;
import soc.common.client.visuals.board.BoardVisual;
import soc.common.client.visuals.game.GameBoardVisual;

public class BuildTownBehaviour extends BuildPointBehaviour
{
    BuildTown buildTown;
    
    public BuildTownBehaviour(BuildTown buildTown)
    {
        super();
        this.buildTown = buildTown;
    }

    @Override
    public void clicked(PieceVisual pieceVisual, BoardVisual board)
    {
        
    }
}
