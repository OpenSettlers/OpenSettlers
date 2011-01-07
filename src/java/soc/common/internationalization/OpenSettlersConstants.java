package soc.common.internationalization;

import com.google.gwt.i18n.client.Constants;

/*
 * TODO: replace all other literal constants from the code base
 */
public interface OpenSettlersConstants extends Constants
{
    @DefaultStringValue("Player")
    String player();

    @DefaultStringValue("Game")
    String game();

    @DefaultStringValue("Robber")
    String robber();

    @DefaultStringValue("Pirate")
    String pirate();

    @DefaultStringValue("Town")
    String town();

    @DefaultStringValue("City")
    String city();

    @DefaultStringValue("Road")
    String road();

    @DefaultStringValue("Development card")
    String developmentCard();

    @DefaultStringValue("Wheat")
    String wheat();

    @DefaultStringValue("Timber")
    String timber();

    @DefaultStringValue("Ore")
    String ore();

    @DefaultStringValue("Clay")
    String clay();

    @DefaultStringValue("Sheep")
    String sheep();

    @DefaultStringValue("Soldier")
    String soldier();

    @DefaultStringValue("Year of plenty")
    String yearOfPlenty();

    @DefaultStringValue("Monopoly")
    String monopoly();

    @DefaultStringValue("Road building")
    String roadBuilding();

    @DefaultStringValue("Victory point")
    String victoryPoint();

    @DefaultStringValue("Play")
    String play();

  String maxSizeIsNegativeOr0();

  String negative();

  String edgeWeightsAreNotAllowedInAnUnidrectedGraph();

  String noSuchVertexInTheGraph();

  String graphMustContainTheEndVertex();

  String theEndVertexIsTheSameAsTheStartVertex();

  String startVertexIsNull();

  String graphIsNull();

  String mustBeNonNegative();

  String endVertex();

  String startVertex();

  String hubVertex();

  String thisGraphOnlySupportsUndirectedOperations();

  String thisGraphDoesNotSupportEdgeAddition();

  String thisGraphIsUnmodifiable();

  String noSuchOperationInADirectedGraph();

  String noSuchOperationInAnUndirectedGraph();

  String loopsNotAllowed();

  String noSuchEdgeInBaseGraph();

  String noSuchVertexInBaseGraph();
}
