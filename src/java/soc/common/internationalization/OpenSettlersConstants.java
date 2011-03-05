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

    @DefaultStringValue("Block a resource hex, and rob a player")
    String robberDescription();

    @DefaultStringValue("Pirate")
    String pirate();

    @DefaultStringValue("Block ships on sea hex, and rob a player")
    String pirateDescription();

    @DefaultStringValue("Town")
    String town();

    @DefaultStringValue("Piece to build on a hex corner, worth 1 point")
    String townDescription();

    @DefaultStringValue("City")
    String city();

    @DefaultStringValue("Piece to replace a settlement with, worth 2 points")
    String cityDescription();

    @DefaultStringValue("Road")
    String road();

    @DefaultStringValue("Piece to discover new places")
    String roadDescription();

    @DefaultStringValue("Development card")
    String developmentCard();

    @DefaultStringValue("Card to advance game development")
    String developmentCardDescription();

    @DefaultStringValue("Wheat hex")
    String wheatHex();

    @DefaultStringValue("Produces wheat, to built settlements, cities and buy development cards")
    String wheatHexDescription();

    @DefaultStringValue("Timber hex")
    String timberHex();

    @DefaultStringValue("Produces timber, to built roads & settlements")
    String timberHexDescription();

    @DefaultStringValue("Ore hex")
    String oreHex();

    @DefaultStringValue("Produces ore, to buy cities &, development cards")
    String oreHexDescription();

    @DefaultStringValue("Clay hex")
    String clayHex();

    @DefaultStringValue("Produces clay, to use for roads & settlements")
    String clayHexDescription();

    @DefaultStringValue("Sheep hex")
    String sheepHex();

    @DefaultStringValue("Sheep needed for settlements, development cards and ")
    String sheepHexDescription();

    @DefaultStringValue("Placeholder hex")
    String noneHex();

    @DefaultStringValue("Does nothing, design-time hex to specify a position should not be rendered in the game")
    String noneHexDescription();

    @DefaultStringValue("Random hex")
    String randomHex();

    @DefaultStringValue("At game start, replaced by any random hex from it's territory's list of hexes")
    String randomHexDescription();

    @DefaultStringValue("Volcano hex")
    String volcanoHex();

    @DefaultStringValue("Produces gold, 1:6 chance this volcano will erupt every time it's chit number is rolled")
    String volcanoHexDescription();

    @DefaultStringValue("Desert hex")
    String desertHex();

    @DefaultStringValue("Produces nothing, starting position for the robber")
    String desertHexDescription();

    @DefaultStringValue("Sea hex")
    String seaHex();

    @DefaultStringValue("Sea hex, can have a port")
    String seaHexDescription();

    @DefaultStringValue("Discovery hex")
    String discoveryHex();

    @DefaultStringValue("Build a road or ship adjacent to this hex to flip it")
    String discoveryHexDescription();

    @DefaultStringValue("Gold hex")
    String goldHex();

    @DefaultStringValue("Produces gold")
    String goldHexDescription();

    @DefaultStringValue("Wheat")
    String wheat();

    @DefaultStringValue("Produced by fields, build settlements, development cards and cities using wheat")
    String wheatDescription();

    @DefaultStringValue("Timber")
    String timber();

    @DefaultStringValue("Produced by a forest, built roads, settlements using timber")
    String timberDescription();

    @DefaultStringValue("Ore")
    String ore();

    @DefaultStringValue("Produced by rocky mountains, build cities and development card using ore")
    String oreDescription();

    @DefaultStringValue("Clay")
    String clay();

    @DefaultStringValue("Produced")
    String clayDescription();

    @DefaultStringValue("Sheep")
    String sheep();

    @DefaultStringValue("Sheep needed for settlements, development cards and ")
    String sheepDescription();

    @DefaultStringValue("Gold")
    String gold();

    @DefaultStringValue("Exchange a gold resource for any tradeable resource")
    String goldDescription();

    @DefaultStringValue("Diamond")
    String diamond();

    @DefaultStringValue("Use diamonds as gold when buying a development card")
    String diamondDescription();

    @DefaultStringValue("Soldier")
    String soldier();

    @DefaultStringValue("Move the robber and rob a player")
    String soldierDescription();

    @DefaultStringValue("Year of plenty")
    String yearOfPlenty();

    @DefaultStringValue("Gain 2 gold resources")
    String yearOfPlentyDescription();

    @DefaultStringValue("Monopoly")
    String monopoly();

    @DefaultStringValue("Steal resources of selected type from all players")
    String monopolyDescription();

    @DefaultStringValue("Road building")
    String roadBuilding();

    @DefaultStringValue("Place two roads, ships or bridges for free")
    String roadBuildingDescription();

    @DefaultStringValue("Victory point")
    String victoryPoint();

    @DefaultStringValue("This card is worth 1 point, and can be played anytime during turn")
    String victoryPointDescription();

    @DefaultStringValue("Play")
    String play();
}
