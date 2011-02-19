/**
 * Keeps all the visuals for every specific web graphics technology. 
 * AbstractVisuals keeps a list of interfaces and their abstract implementations,
 * such that lots of logic can be reused. This reusing of logic mainly happens in
 * the behaviour package, where board and gameboard behaviour is defined for various
 * types of interaction behaviour with the player. 
 * 
 * A canvas of WebGL implementation thus only needs to subclass the Abstract*Visual 
 * classes. Interaction behaviour then will be consistent whether an svg, canvas or webgl 
 * technology is used to render a board or a gameboard. 
 * 
 */
package soc.gwtClient.game.widgetsInterface.visuals;