package soc.common.board.routing.longestRoadStrategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import soc.common.board.layout.HasPoint;
import soc.common.board.layout.HasSide;
import soc.common.board.layout.HexPoint;
import soc.common.board.layout.HexSide;
import soc.common.board.pieces.pieceLists.SidePieceList;
import soc.common.board.routing.BoardGraph;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.board.routing.Route;
import soc.common.board.routing.RouteImpl;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;

/*
 * Bruteforce strategy to calculate longest road ported from SettleIn
 * 
 * Awesomely insane way to check the board for the longest road. How this works
 * in a nutshell:
 * -Two possible new paths are created for every SidePiece
 * -From here, every path is walked creating a new permutation for every split encountered
 * -When an end is encountered, the path is added to the list of routes with end
 * -When all possible paths are walked, first encountered path with highest length
 *      value is picked and returned.
 *      
 * TODO: Abstract duplicate code
 * TODO: Chop Huge Big Insane method into pieces
 * TODO: Make algorithm more efficient (RefinedBruteForceStrategy?)
 */
public class BruteForceStrategy implements LongestRoadStrategy
{
    private BoardGraph boardGraph;
    private Game game;
    private Map<GamePlayer, List<HexPoint>> opponentsTownsCitiesMap = new HashMap<GamePlayer, List<HexPoint>>();

    public BruteForceStrategy(BoardGraph boardGraph, Game game)
    {
        super();
        this.boardGraph = boardGraph;
        this.game = game;

        // Fill the HashMap with a list of opponents' towns & cities for every
        // player
        for (GamePlayer player : game.getPlayers())
        {
            // Create a new list containing the opponent's locations with towns
            // & cities
            List<HexPoint> opponentsTownsCities = new ArrayList<HexPoint>();

            // Fill the list with all encountered towns & cities
            for (GamePlayer opponent : game.getPlayers().getOpponents(player))
                for (HasPoint pointPiece : opponent.getPointPieces())
                    opponentsTownsCities.add(pointPiece.getPoint());

            // Finally, add the list to the HashMap
            opponentsTownsCitiesMap.put(player, opponentsTownsCities);
        }
    }

    @Override
    public Route calculateLongestRoad(Route currentLongest,
            BoardGraph boardGraph, Game game)
    {
        // Create a list and fill the list with the longest route of every
        // player
        List<Route> routes = new ArrayList<Route>();
        for (GamePlayer player : game.getPlayers())
            routes.add(getLongestRoad(player, boardGraph, game));

        // No routes found, return nothing
        if (routes.size() == 0)
            return null;

        // Check which is the longest, and return it when it exceeds the length
        // of given current longest route
        Route longest = routes.get(0);

        for (Route route : routes)
        {
            if (route != null)
                if (longest == null || route.getLength() > longest.getLength())
                    longest = route;
        }

        if (currentLongest == null)
            return longest;

        return longest.getLength() > currentLongest.getLength() ? longest
                : currentLongest;
    }

    private Route getLongestRoad(GamePlayer player, BoardGraph graph, Game game)
    {
        SidePieceList roadsShips = player.getSidePieces();
        List<List<HexPoint>> routesWithEnd = new ArrayList<List<HexPoint>>();
        List<List<HexPoint>> routesWithoutEnd = new ArrayList<List<HexPoint>>();

        List<HexPoint> uniqueJoins = new ArrayList<HexPoint>();

        for (HasSide sidePiece : roadsShips)
        {
            HexPoint p1 = sidePiece.getSide().getHexPoint1();
            HexPoint p2 = sidePiece.getSide().getHexPoint2();

            if (uniqueJoins.contains(p1))
                uniqueJoins.add(p1);

            if (uniqueJoins.contains(p2))
                uniqueJoins.add(p2);
        }

        // create a new route possibility for each unique hexpoint
        for (HasSide piece : roadsShips)
        {
            List<HexPoint> newRoute = new ArrayList<HexPoint>();
            List<HexPoint> newRoute2 = new ArrayList<HexPoint>();

            // each side creates 2 possibilities, with opposite directions to
            // go to.
            newRoute.add(piece.getSide().getHexPoint1());
            newRoute.add(piece.getSide().getHexPoint2());
            newRoute2.add(piece.getSide().getHexPoint1());
            newRoute2.add(piece.getSide().getHexPoint2());
            routesWithoutEnd.add(newRoute);
            routesWithoutEnd.add(newRoute2);
        }
        int j = 0;

        // we iterate of over all routes which arent marked with an endpoint
        // yet. Eventuually only routes with endpoints will exist,
        // as a path eventually encounters a point where:
        // -A town/city of an opponent is resided
        // -Two roads/ships meeting following qualification:
        // -allready present in the route
        // -opponent has road/ship
        // -no player has road/ship on both locations
        // -
        while (routesWithoutEnd.size() > 0)
        {
            j++;
            // now we have three possibilities:
            // 1. last point is also an endpoint
            // -close the route, add it to list of routes with end

            // 2. current point is a split
            // pick a direction, copy route to list of routes without end
            // proceed with new pass of check
            // 3. current point has a connection with 1 road or ship
            // add currentpoint to the route
            // proceed with new pass of the last point
            //
            // When the route already contains one road in the neighbours,
            // we can only proceed when we have a split
            // When the route contains both neighbours, we reached an end

            // grab a route from the list
            List<HexPoint> route = routesWithoutEnd.get(0);

            // the point used as starting point for the two lookahead points
            HexPoint lastPoint = route.get(route.size() - 1);

            // the point where we came from
            HexPoint beforeLastPoint = route.get(route.size() - 2);

            // side where we came from
            HexSide side = new HexSide(lastPoint, beforeLastPoint);

            List<HexPoint> opponentsTownsCities = opponentsTownsCitiesMap
                    .get(player);

            if (isEndPoint(lastPoint, side, player)
                    || opponentsTownsCities.contains(lastPoint))
            // we have an endpoint here
            {
                routesWithEnd.add(route);
                routesWithoutEnd.remove(route);
                continue;
            }
            if (isSplit(lastPoint, side, player))
            // we encountered a split here.
            // possibilities:
            // -one, or both of the sides are already contained within the
            // route.
            // -both: treat as end
            // -one: pick other side to continue
            // -zero: split the route
            {
                List<HexPoint> newPoints = lastPoint
                        .getOtherNeighbours(beforeLastPoint);

                boolean firstIsContained = isContained(route, lastPoint,
                        newPoints.get(0));
                boolean secondIsContained = isContained(route, lastPoint,
                        newPoints.get(1));
                if (firstIsContained && secondIsContained)
                {
                    // we reached an end: both neighbours are already in the
                    // route, cannot proceed, close route
                    routesWithEnd.add(route);
                    routesWithoutEnd.remove(route);
                    continue;
                }

                if (firstIsContained)
                {
                    route.add(newPoints.get(1));
                    continue;
                }
                if (secondIsContained)
                {
                    route.add(newPoints.get(0));
                    continue;
                }

                // neither is contained, we encountered a valid split.

                // the first point gets added to the splittedroute
                List<HexPoint> splittedRoute = copyList(route);
                splittedRoute.add(newPoints.get(0));
                routesWithoutEnd.add(splittedRoute);

                // the second point is the new lastpoint for the current route
                route.add(newPoints.get(1));

                continue;
            }

            // no split, no endpoint, no city/town of opponent present.
            // So, we have one ship or road in the neighbours.
            // road+ship: we need a town, then extend route
            // road+road or ship+ship: extend route
            // ship+road, we need a town, then extend route

            // Get the two neighbouring points from the side
            List<HexSide> otherSides = lastPoint.getOtherSides(side);

            List<HexPoint> playerTownsCities = player.getPointPieces()
                    .getPoints();

            if (player.getRoads().contains(side))
            // road
            {
                // If any of both neighbours is contained within the list of
                // roads,
                // we have a connection.
                List<HexSide> roads = new ArrayList<HexSide>();
                for (HexSide otherSide : otherSides)
                    if (player.getRoads().contains(otherSide))
                        roads.add(side);

                if (roads.size() == 1)
                // road + road
                {
                    HexPoint newPoint = roads.get(0).getOtherPoint(lastPoint);
                    if (isContained(route, lastPoint, newPoint))
                    {
                        routesWithEnd.add(route);
                        routesWithoutEnd.remove(route);
                        continue;
                    }
                    else
                    {
                        route.add(newPoint);
                        continue;
                    }
                }

                // No road + road found. Check for road+ship, but only when we
                // have a town/city
                // on the point, which connects the road and ship.
                if (playerTownsCities.contains(lastPoint))
                {
                    // road + ship with city/town
                    List<HexSide> ships = new ArrayList<HexSide>();
                    for (HexSide otherSide : otherSides)
                        if (player.getShips().contains(otherSide))
                            ships.add(side);

                    if (ships.size() == 1)
                    {
                        HexPoint newPoint = ships.get(0).getOtherPoint(
                                lastPoint);
                        if (isContained(route, lastPoint, newPoint))
                        {
                            routesWithEnd.add(route);
                            routesWithoutEnd.remove(route);
                            continue;
                        }
                        else
                        {
                            route.add(newPoint);
                            continue;
                        }
                    }
                }
            }
            else
            // ship
            {
                // If any of both neighbours is contained within the list of
                // roads,
                // we have a connection.
                List<HexSide> ships = new ArrayList<HexSide>();
                for (HexSide otherSide : otherSides)
                    if (player.getShips().contains(otherSide))
                        ships.add(side);

                if (ships.size() == 1)
                // road + road
                {
                    HexPoint newPoint = ships.get(0).getOtherPoint(lastPoint);
                    if (isContained(route, lastPoint, newPoint))
                    {
                        routesWithEnd.add(route);
                        routesWithoutEnd.remove(route);
                        continue;
                    }
                    else
                    {
                        route.add(newPoint);
                        continue;
                    }
                }

                // No road + road found. Check for road+ship, but only when we
                // have a town/city
                // on the point, which connects the road and ship.
                if (playerTownsCities.contains(lastPoint))
                {
                    // road + ship with city/town
                    List<HexSide> roads = new ArrayList<HexSide>();
                    for (HexSide otherSide : otherSides)
                        if (player.getRoads().contains(otherSide))
                            roads.add(side);

                    if (roads.size() == 1)
                    {
                        HexPoint newPoint = roads.get(0).getOtherPoint(
                                lastPoint);
                        if (isContained(route, lastPoint, newPoint))
                        {
                            routesWithEnd.add(route);
                            routesWithoutEnd.remove(route);
                            continue;
                        }
                        else
                        {
                            route.add(newPoint);
                            continue;
                        }
                    }
                }
            }
        }

        // sweet! now we have a list of all possible roads. Pick the
        // first matching the maximum length;
        if (routesWithEnd.size() == 0)
            return null;

        List<HexPoint> longest = routesWithEnd.get(0);
        for (List<HexPoint> list : routesWithEnd)
            if (longest.size() < list.size())
                longest = list;

        // edges.size() == vertices.size() + 1, since each edge needs two
        // vertices
        // As such, size must be more then 5.
        if (longest.size() > 5)
        {
            return new RouteImpl(boardGraph, longest, player);
        }
        else
        {
            // route length did not exceed 4 roads, so no route found.
            return null;
        }

    }

    private boolean isEndPoint(HexPoint point, HexSide side, GamePlayer player)
    {
        // create a list of opponents' cities+towns
        List<HexPoint> opponentsTownsCities = opponentsTownsCitiesMap
                .get(player);

        // an opponent has a town or city the point.
        // Great, we have an end here, so return true.
        if (opponentsTownsCities.contains(point))
        {
            return true;
        }
        else
        {
            // when the direction fails to connect, we have an end
            List<HexPoint> playerTownsCities = player.getPointPieces()
                    .getPoints();

            // opponent has no city/town at either point. Determine
            // if we have a connection.
            // A connection is there when:
            // - road + road
            // - road + town/city + ship
            // - ship + ship
            // - ship + town/city + road
            if (player.getRoads().contains(side))
            // road
            {
                // Get the two neighbouring points from the side
                List<HexSide> otherSides1 = point.getOtherSides(side);

                // If any of both neighbours is contained within the list of
                // roads,
                // we have a connection.
                if (player.getRoads().contains(otherSides1.get(0))
                        || player.getRoads().contains(otherSides1.get(1)))
                    // road + road
                    return false;

                // No road + road found. Check for road+ship, but only when we
                // have a town/city
                // on the point, which connects the road and ship.
                if (playerTownsCities.contains(side.getHexPoint1()))
                {
                    // road + ship with city/town
                    if (player.getShips().contains(otherSides1.get(0))
                            || player.getShips().contains(otherSides1.get(1)))
                        return false;
                }
            }
            else
            // ship
            {
                // Get the two neighbouring points from the side
                List<HexSide> otherSides1 = point.getOtherSides(side);

                // If any of both neighbours is contained within the list of
                // ships,
                // we have a connection.
                if (player.getShips().contains(otherSides1.get(0))
                        || player.getShips().contains(otherSides1.get(1)))
                    // ship + ship
                    return false;

                // No ship + ship found. Check for ship + road, but only when we
                // have a town/city
                // on the point, which connects the ship and road.
                if (playerTownsCities.contains(point))
                {
                    // ship + road with city/town
                    if (player.getRoads().contains(otherSides1.get(0))
                            || player.getRoads().contains(otherSides1.get(1)))
                        return false;
                }
            }
        }
        return true;
    }

    private boolean isSplit(HexPoint point, HexSide side, GamePlayer player)
    {
        GraphPoint graphPoint = boardGraph.findGraphPoint(point);
        if (boardGraph.getGraph().degreeOf(graphPoint) != 3)
            return false;

        Set<GraphSide> sides = boardGraph.getGraph().edgesOf(graphPoint);
        for (GraphSide graphSide : sides)
            if (graphSide.getPlayer() == null)
                return false;

        if (sides.iterator().next().getPlayer().equals(player)
                && sides.iterator().next().getPlayer().equals(player)
                && sides.iterator().next().getPlayer().equals(player))
            return true;

        return false;

    }

    private boolean isContained(List<HexPoint> route, HexPoint point1,
            HexPoint point2)
    {
        for (int i = 0; i < route.size() - 2; i++)
        {
            if (route.get(i).equals(point1) && route.get(i + 1).equals(point2)
                    || route.get(i).equals(point2)
                    && route.get(i + 1).equals(point1))
                return true;
        }
        return false;
    }

    private List<HexPoint> copyList(List<HexPoint> toCopy)
    {
        List<HexPoint> result = new ArrayList<HexPoint>();

        for (HexPoint point : toCopy)
            result.add(new HexPoint(point.getHex1(), point.getHex2(), point
                    .getHex3()));

        return result;
    }
}
