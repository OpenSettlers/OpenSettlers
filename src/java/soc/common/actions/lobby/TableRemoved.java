package soc.common.actions.lobby;

import soc.common.lobby.Lobby;

public class TableRemoved extends AbstractLobbyAction
{
    private static final long serialVersionUID = 4562594164924778315L;
    private int gameInfoId;
    private int playerId;

    @Override
    public void perform(Lobby lobby)
    {
        // TODO Auto-generated method stub

    }

    public TableRemoved setGameInfoId(int gameInfoId)
    {
        this.gameInfoId = gameInfoId;
        return this;
    }

    public int getGameInfoId()
    {
        return gameInfoId;
    }

}
