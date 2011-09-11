package org.soc.common.internationalization;

import com.google.gwt.i18n.client.Constants;

public interface OpenSettlersConstants extends Constants {
  @DefaultStringValue("Player") String player();
  @DefaultStringValue("Army") String army();
  @DefaultStringValue("Earn 2 points by having the largest amount of soldiers") String
          armyDescription();
  @DefaultStringValue("Longest road") String longestRoad();
  @DefaultStringValue("Earn 2 points by having the longest unbroken sequence of roads, towns and ships")
          String longestRoadDescription();
  @DefaultStringValue("Longest road") String wall();
  @DefaultStringValue("Earn 2 points by having the longest unbroken sequence of roads, towns and ships")
          String wallDescription();
  @DefaultStringValue("Island bonus point") String islandBonus();
  @DefaultStringValue("Earn one point by building on a newly discovered island") String
          islandBonusDescription();
  @DefaultStringValue("Game") String game();
  @DefaultStringValue("Robber") String robber();
  @DefaultStringValue("Block a resource hex, and rob a player") String robberDescription();
  @DefaultStringValue("Pirate") String pirate();
  @DefaultStringValue("Block ships on sea hex, and rob a player") String pirateDescription();
  @DefaultStringValue("Town") String town();
  @DefaultStringValue("Piece to build on a hex corner, worth 1 point") String townDescription();
  @DefaultStringValue("City") String city();
  @DefaultStringValue("Piece to replace a settlement with, worth 2 points") String
          cityDescription();
  @DefaultStringValue("Road") String road();
  @DefaultStringValue("Piece to discover new places on land") String roadDescription();
  @DefaultStringValue("Ship") String ship();
  @DefaultStringValue("Piece to discover new places on sea") String shipDescription();
  @DefaultStringValue("Development card") String developmentCard();
  @DefaultStringValue("Card to advance game development") String developmentCardDescription();
  @DefaultStringValue("Wheat hex") String wheatHex();
  @DefaultStringValue("Produces wheat, to built settlements, cities and buy development cards")
          String wheatHexDescription();
  @DefaultStringValue("Timber hex") String timberHex();
  @DefaultStringValue("Produces timber, to built roads & settlements") String
          timberHexDescription();
  @DefaultStringValue("Ore hex") String oreHex();
  @DefaultStringValue("Produces ore, to buy cities &, development cards") String
          oreHexDescription();
  @DefaultStringValue("Clay hex") String clayHex();
  @DefaultStringValue("Produces clay, to use for roads & settlements") String clayHexDescription();
  @DefaultStringValue("Sheep hex") String sheepHex();
  @DefaultStringValue("Sheep needed for settlements, development cards and ") String
          sheepHexDescription();
  @DefaultStringValue("Placeholder hex") String noneHex();
  @DefaultStringValue("Does nothing, design-time hex to specify a position should not be rendered in the game")
          String noneHexDescription();
  @DefaultStringValue("Random hex") String randomHex();
  @DefaultStringValue("At game start, replaced by any random hex from it's territory's list of hexes")
          String randomHexDescription();
  @DefaultStringValue("Volcano hex") String volcanoHex();
  @DefaultStringValue("Produces gold, 1:6 chance this volcano will erupt every time it's chit number is rolled")
          String volcanoHexDescription();
  @DefaultStringValue("Desert hex") String desertHex();
  @DefaultStringValue("Produces nothing, starting position for the robber") String
          desertHexDescription();
  @DefaultStringValue("Sea hex") String seaHex();
  @DefaultStringValue("Sea hex, can have a port") String seaHexDescription();
  @DefaultStringValue("Discovery hex") String discoveryHex();
  @DefaultStringValue("Build a road or ship adjacent to this hex to flip it") String
          discoveryHexDescription();
  @DefaultStringValue("Gold hex") String goldHex();
  @DefaultStringValue("Produces gold") String goldHexDescription();
  @DefaultStringValue("Wheat") String wheat();
  @DefaultStringValue("Produced by fields, build settlements, development cards and cities using wheat")
          String wheatDescription();
  @DefaultStringValue("Timber") String timber();
  @DefaultStringValue("Produced by a forest, built roads, settlements using timber") String
          timberDescription();
  @DefaultStringValue("Ore") String ore();
  @DefaultStringValue("Produced by rocky mountains, build cities and development card using ore")
          String oreDescription();
  @DefaultStringValue("Clay") String clay();
  @DefaultStringValue("Produced") String clayDescription();
  @DefaultStringValue("Sheep") String sheep();
  @DefaultStringValue("Sheep needed for settlements, development cards and ") String
          sheepDescription();
  @DefaultStringValue("Gold") String gold();
  @DefaultStringValue("Exchange a gold resource for any tradeable resource") String
          goldDescription();
  @DefaultStringValue("Diamond") String diamond();
  @DefaultStringValue("Use diamonds as gold when buying a development card") String
          diamondDescription();
  @DefaultStringValue("Soldier") String soldier();
  @DefaultStringValue("Move the robber and rob a player") String soldierDescription();
  @DefaultStringValue("Year of plenty") String yearOfPlenty();
  @DefaultStringValue("Gain 2 gold resources") String yearOfPlentyDescription();
  @DefaultStringValue("Dummy development card") String dummyDevelopmentCard();
  @DefaultStringValue("You don't know what's inside") String dummyDevelopmentCardDescription();
  @DefaultStringValue("Monopoly") String monopoly();
  @DefaultStringValue("Steal resources of selected type from all players") String
          monopolyDescription();
  @DefaultStringValue("Road building") String roadBuilding();
  @DefaultStringValue("Place two roads, ships or bridges for free") String
          roadBuildingDescription();
  @DefaultStringValue("Victory point") String victoryPoint();
  @DefaultStringValue("This card is worth 1 point, and can be played anytime during turn") String
          victoryPointDescription();
  @DefaultStringValue("Play") String play();
  @DefaultStringValue("Clay port") String clayPort();
  @DefaultStringValue("Trade 2 clay to the  bank for 1 resource") String clayPortDescription();
  @DefaultStringValue("Sheep port") String sheepPort();
  @DefaultStringValue("Trade 2 sheep to the bank for 1 resource") String sheepPortDescription();
  @DefaultStringValue("Wheat port") String wheatPort();
  @DefaultStringValue("Trade 2 wheat to the bank for 1 resource") String wheatPortDescription();
  @DefaultStringValue("Timber port") String timberPort();
  @DefaultStringValue("Trade 2 timber to the bank for 1 resource") String timberPortDescription();
  @DefaultStringValue("Ore port") String orePort();
  @DefaultStringValue("Trade 2 ore to the bank for 1 resource") String orePortDescription();
  @DefaultStringValue("3:1 port") String threeToOnePort();
  @DefaultStringValue("Trade 3 of the same resources to the bank for 1 resource") String
          threeToOnePortDescription();
  @DefaultStringValue("4:1 bank port") String fourToOnePort();
  @DefaultStringValue("Trade 4 of the same resources to the bank for 1 resource") String
          fourToOnePortDescription();
  @DefaultStringValue("Random port") String randomPort();
  @DefaultStringValue("Placeholder for another port") String randomPortDescription();
  @DefaultStringValue("Possible location for a port") String possiblePort();
  @DefaultStringValue("Placeholder for a valid place to put a port") String
          possiblePortDescription();
  @DefaultStringValue("2 chit") String chit2();
  @DefaultStringValue("Chance of 1:36 of being rolled by dice, 12 has equal chances") String
          chit2Description();
  @DefaultStringValue("3 chit") String chit3();
  @DefaultStringValue("Chance of 2:36 of being rolled by dice, 11 has equal chances") String
          chit3Description();
  @DefaultStringValue("4 chit") String chit4();
  @DefaultStringValue("Chance of 3:36 of being rolled by dice, 10 has equal chances") String
          chit4Description();
  @DefaultStringValue("5 chit") String chit5();
  @DefaultStringValue("Chance of 4:36 of being rolled by dice, 9 has equal chances") String
          chit5Description();
  @DefaultStringValue("6 chit") String chit6();
  @DefaultStringValue("Chance of 5:36 of being rolled by dice, 8 has equal chances") String
          chit6Description();
  @DefaultStringValue("8 chit") String chit8();
  @DefaultStringValue("Chance of 5:36 of being rolled by dice, 6 has equal chances") String
          chit8Description();
  @DefaultStringValue("9 chit") String chit9();
  @DefaultStringValue("Chance of 4:36 of being rolled by dice, 5 has equal chances") String
          chit9Description();
  @DefaultStringValue("10 chit") String chit10();
  @DefaultStringValue("Chance of 3:36 of being rolled by dice, 4 has equal chances") String
          chit10Description();
  @DefaultStringValue("11 chit") String chit11();
  @DefaultStringValue("Chance of 2:36 of being rolled by dice, 3 has equal chances") String
          chit11Description();
  @DefaultStringValue("12 chit") String chit12();
  @DefaultStringValue("Chance of 1:36 of being rolled by dice, 2 has equal chances") String
          chit12Description();
  @DefaultStringValue("Random chit") String chitRandom();
  @DefaultStringValue("Placeholder, replaced at game start by a chit from it's territory's list of chits")
          String chitRandomDescription();
}
