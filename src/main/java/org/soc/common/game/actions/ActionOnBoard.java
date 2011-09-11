package org.soc.common.game.actions;

import org.soc.common.game.Chit;
import org.soc.common.game.Chit.Chit2;
import org.soc.common.game.Port;
import org.soc.common.game.Territory;
import org.soc.common.game.hexes.AbstractHex;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.hexes.TimberHex;
import org.soc.common.views.widgetsInterface.visuals.BoardVisual;
import org.soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.HexVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.PortVisual;

public interface ActionOnBoard {
  public void clicked(PieceVisual pieceVisual, BoardVisual board);
  public void mouseEnter(PieceVisual pieceVisual, BoardVisual board);
  public void mouseOut(PieceVisual pieceVisual, BoardVisual board);

  /** Proxies boardbehaviour through to GameBoardBaheviour of encapsulated GameBoardVisual */
  public class ProxyBehaviour implements ActionOnBoard {
    private GameBoardVisual gameBoardVisual;

    public ProxyBehaviour(GameBoardVisual gameBoardVisual) {
      super();
      this.gameBoardVisual = gameBoardVisual;
    }
    @Override public void clicked(PieceVisual pieceVisual, BoardVisual board) {
      gameBoardVisual.clicked(pieceVisual, gameBoardVisual);
    }
    @Override public void mouseEnter(PieceVisual pieceVisual, BoardVisual board) {
      gameBoardVisual.mouseEnter(pieceVisual, gameBoardVisual);
    }
    @Override public void mouseOut(PieceVisual pieceVisual, BoardVisual board) {
      gameBoardVisual.mouseOut(pieceVisual, gameBoardVisual);
    }
  }

  /** Default behaviour of an BoardVisual, shows information of clicked item and highlights hovered
   * PieceVisual */
  public class DefaultBehaviour implements ActionOnBoard {
    @Override public void clicked(PieceVisual pieceVisual, BoardVisual board) {}
    /** highlights hovered PieceVisual */
    @Override public void mouseEnter(PieceVisual pieceVisual, BoardVisual board) {
      pieceVisual.setSelected(true);
    }
    /** De-highlights hovered PieceVisual */
    @Override public void mouseOut(PieceVisual pieceVisual, BoardVisual board) {
      pieceVisual.setSelected(false);
    }
  }

  /** Represents a null behaviour, where a board does not have any behaviour attached */
  public class NoBehaviour implements ActionOnBoard {
    @Override public void clicked(PieceVisual pieceVisual, BoardVisual board) {}
    @Override public void mouseEnter(PieceVisual pieceVisual, BoardVisual board) {}
    @Override public void mouseOut(PieceVisual pieceVisual, BoardVisual board) {}
  }

  public class SetChitOnBoard implements ActionOnBoard {
    private Chit currentChit = new Chit2();

    @Override public void clicked(PieceVisual pieceVisual, BoardVisual board) {
      HexVisual hexVisual = pieceVisual.hexVisual();
      if (hexVisual != null) {
        Hex hex = hexVisual.hex();
        if (hex.producesResource())
          hex.setChit(currentChit.copy());
      }
    }
    public Chit chit() {
      return currentChit;
    }
    public SetChitOnBoard setChit(Chit currentChit) {
      this.currentChit = currentChit;
      return this;
    }
    @Override public void mouseEnter(PieceVisual pieceVisual, BoardVisual board) {
      HexVisual hexVisual = pieceVisual.hexVisual();
      if (hexVisual != null && hexVisual.hex().producesResource())
        pieceVisual.setSelected(true);
    }
    @Override public void mouseOut(PieceVisual pieceVisual, BoardVisual board) {
      pieceVisual.setSelected(false);
    }
  }

  public class SetHexOnBoard implements ActionOnBoard {
    private Hex hex = new TimberHex();

    public Hex hex() {
      return hex;
    }
    public SetHexOnBoard setHex(Hex h) {
      this.hex = h;
      return this;
    }
    @Override public void clicked(PieceVisual pieceVisual, BoardVisual boardVisual) {
      HexVisual hexVisual = pieceVisual.hexVisual();
      if (hexVisual != null) {
        Hex newHex = hex.copy();
        newHex.setLocation(hexVisual.hex().location());
        boardVisual.board().hexes().set(hexVisual.hex().location(), newHex);
      }
    }
    @Override public void mouseEnter(PieceVisual pieceVisual, BoardVisual board) {
      pieceVisual.setSelected(true);
    }
    @Override public void mouseOut(PieceVisual pieceVisual, BoardVisual board) {
      pieceVisual.setSelected(false);
    }
  }

  public class SetPortOnBoard implements ActionOnBoard {
    private Port port;
    private HexVisual currentHexVisual;

    public Port getPort() {
      return port;
    }
    public SetPortOnBoard setPort(Port p) {
      this.port = p;
      return this;
    }
    @Override public void clicked(PieceVisual pieceVisual, BoardVisual board) {
      PortVisual portVisual = pieceVisual.portVisual();
      if (portVisual != null) {
        AbstractHex seaHex = (AbstractHex) currentHexVisual.hex();
        Port p = port.copy();
        p.setRotationPosition(portVisual.getPort().rotationPosition());
        p.setHexLocation(seaHex.location());
        seaHex.setPort(p);
      }
    }
    @Override public void mouseEnter(PieceVisual pieceVisual, BoardVisual board) {
      HexVisual hexVisual = pieceVisual.hexVisual();
      if (hexVisual != null && hexVisual.hex().canHavePort()) {
        hexVisual.setSelected(true);
        hexVisual.getPortPossibilitiesVisual().setVisible(true);
        hexVisual.getPortPossibilitiesVisual().updatePossibilities();
        currentHexVisual = hexVisual;
      }
      PortVisual portVisual = pieceVisual.portVisual();
      if (portVisual != null)
        portVisual.setSelected(true);
    }
    @Override public void mouseOut(PieceVisual pieceVisual, BoardVisual board) {
      HexVisual hexVisual = pieceVisual.hexVisual();
      if (hexVisual != null && hexVisual.hex().canHavePort()) {
        hexVisual.setSelected(false);
        hexVisual.getPortPossibilitiesVisual().setVisible(false);
        currentHexVisual = null;
      }
      PortVisual portVisual = pieceVisual.portVisual();
      if (portVisual != null)
        portVisual.setSelected(false);
    }
  }

  public class SetTerritoryOnBoard implements ActionOnBoard {
    private Territory territory;

    @Override public void clicked(PieceVisual pieceVisual, BoardVisual board) {
      HexVisual hexVisual = pieceVisual.hexVisual();
      if (hexVisual != null && hexVisual.hex().territory() != null) {
        hexVisual.getTerritory().setTerritory(territory);
        hexVisual.getPortPossibilitiesVisual().updatePossibilities();
      }
    }
    public Territory territory() {
      return territory;
    }
    public SetTerritoryOnBoard setTerritory(Territory territory) {
      this.territory = territory;
      return this;
    }
    public void mouseEnter(PieceVisual pieceVisual, BoardVisual board) {
      HexVisual hexVisual = pieceVisual.hexVisual();
      if (hexVisual != null)
        hexVisual.setSelected(hexVisual.getTerritory() != null);
    }
    @Override public void mouseOut(PieceVisual hex, BoardVisual board) {
      hex.setSelected(false);
    }
  }
}
