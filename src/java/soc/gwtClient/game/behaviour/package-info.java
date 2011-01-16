/*
 * Three types of interaction behaviour are defined. Those are BoardBehaviour, 
 * GameBoardBehaviour and GameBehaviour. As the names imply, they specify behaviour
 * on a Board, a GameBoard and a GamePanel respectively. 
 * 
 * A BoardBehaviour only interacts with and visualizes hexes. GameBoardBehaviour also
 * takes board pieces into account, of which SideVisual and PointVisual are part.
 * GameBehaviour usually encapsulates a BoardBehaviour or GameBoardBehaviour. 
 * Furthermore, GameBehaviour interacts to the GamePanel showing or.hiding widgets
 * encapsulated and made publicly accessible by the GamePanel.
 * 
 * GameBehaviour interacts with the player using the GamePanel. Two interfaces of GameBehaviour
 * exist: GameBehaviour and ReceiveGameBehaviour (which extends GameBehaviour). 
 * GameBehaviour is associates behaviour with a GameAction, and applies this 
 * behaviour to the GamePanel. Usually a GameBehaviour class will encapsulate
 * GameBoardBehaviour to visualize a changed state of the GameBoard. 
 * ReceiveGameBehaviour adds a method endsManually(), specifying if the behaviour
 * state will vanish automatically using animation, or manually by the user as a 
 * result of a click on a button. RollDiceResult uses this to make sure the player 
 * on turn is aware of the resources being distributed. When the player has 
 * acknowledged this, RollDiceResult calls doneReceiveBehaviour() on the GamePanel, 
 * which can decide if there is a behaviour to be associated with the next action
 * on the actions queue of the game. 
 */

package soc.gwtClient.game.behaviour;