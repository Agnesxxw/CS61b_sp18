/**
 * @author: Agnes Xu
 * @date: 2020/3/3
 * @version: 1.0
 */

package byog.Core;
/**
 * Random world generator
 */
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;

public class worldGenerator {

    private static final int MAXROOMWIDTH = 6;
    private static final int MAXROOMHEIGHT = 6;
    private static final String NORTH = "N";
    private static final String EAST = "E";
    private static final String SOUTH = "S";
    private static final String WEST = "W";

    private int width;
    private int height;
    private Position initialPosition;
    private Random random;
    private TETile[][] world;

    /**
     * Returns WorldGenerator object without random seed specified
     *
     * @param w        width of generated world
     * @param h        height of generated world
     * @param initialX x coordinate of initial LOCKED_DOOR
     * @param initialY y coordinate of initial LOCKED_DOOR
     */
    worldGenerator(int w, int h, int initialX, int initialY) {
        width = w;
        height = h;
        initialPosition = new Position(initialX, initialY);
        random = new Random();
    }

    /**
     * Returns WorldGenerator object with random seed specified
     *
     * @param w        width of generated world
     * @param h        height of generated world
     * @param initialX x coordinate of initial LOCKED_DOOR
     * @param initialY y coordinate of initial LOCKED_DOOR
     * @param seed     random seed used to generate world
     */
    worldGenerator(int w, int h, int initialX, int initialY, long seed) {
        width = w;
        height = h;
        initialPosition = new Position(initialX, initialY);
        random = new Random(seed);
    }

    /**
     * Position coordinate
     */
    public static class Position {
        int x;
        int y;

        public Position(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }

    /**
     * World initialization
     */
    private void initialize() {
        world = new TETile[width][height];
        for (int w = 0; w < width; w += 1) {
            for (int h = 0; h < height; h += 1) {
                world[w][h] = Tileset.NOTHING;
            }
        }
    }

    /**
     * generate a single room
     * @param lb left-bottom coordination of the rectangle
     * @param rt right-top coordination of the rectangle
     */
    private void generateRoom(Position lb, Position rt){
        int lbx = lb.x;
        int lby = lb.y;
        int rtx = rt.x;
        int rty = rt.y;

        for(int x = lbx; x <= rtx; lbx += 1){
            for(int y = lby; y <= lby; y += 1){
                if(y == rty || y == lby || x == lbx || x == rtx){ // Draw the wall of room
                    world[x][y] = Tileset.FLOWER;
                }else{
                    world[x][y] = Tileset.GRASS;
                }
            }
        }
    }

    /**
     * initial entrance
     * @param e initial position
     */
    private void entrance(Position e){
        world[e.x][e.y] = Tileset.LOCKED_DOOR;
    }

    /* Generate entrance by changing WALL to FLOOR at entryPoint */
    private void generateEntrance(Position point) {
        world[point.x][point.y] = Tileset.GRASS;
    }

    /* Generate exit by changing WALL to FLOOR at entryPoint */
    private void Exit(Position exitPoint) {
        world[exitPoint.x][exitPoint.y] = Tileset.GRASS;
    }

    /**
     * Check if the new random room is available
     * @param lb left-bottom coordination of the rectangle
     * @param rt right-top coordination of the rectangle
     */
    private boolean checkAvailability(Position lb, Position rt){
        int lbx = lb.x;
        int lby = lb.y;
        int rtx = rt.x;
        int rty = rt.y;
        if(lbx < 0 || lby < 0 || rtx < 0 || rty < 0
                || lbx >= width || lby >= height || rtx >= width || rty >= height){
            return false;
        }
        for(int x = lbx; x <= rtx; lbx += 1){
            for(int y = lby; y <= lby; y += 1){
                TETile currentTile = world[x][y];
                if(currentTile == Tileset.FLOWER || currentTile == Tileset.GRASS){ // if current tile has been taken
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * return new direction if this direction isn't available
     * @param direction
     * @return new direction
     */
    private String getReverseDirc(String direction){
        switch (direction){
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case WEST:
                return EAST;
            default:
                return WEST;
        }
    }

    /**
     * return the random new room position[leftbottom, righttop] coordinate in the north
     * @param point start point
     * @param W width of the room
     * @param H height of the room
     * @return the new coordination of northroom
     */
    private Position[] newNorthPosition(Position point, int W, int H){
        int px = point.x;
        int py = point.y;
        int newlbx = px - 1 - random.nextInt(W);
        int newlby = py;
        int newrtx = newlbx + W + 1;
        int newrty = newlby + H + 1;
        Position newlb = new Position(newlbx, newlby);
        Position newrt = new Position(newrtx, newrty);
        Position[] newPosition = new Position[]{newlb, newrt};
        if(!checkAvailability(newlb, newrt)){
            return null;
        }else{
            return newPosition;
        }
    }

    /**
     * return the random new room position[leftbottom, righttop] coordinate in the east
     * @param point start point
     * @param W width of the room
     * @param H height of the room
     * @return the new coordination of eastroom
     */
    private Position[] newEastPosition(Position point, int W, int H) {
        int px = point.x;
        int py = point.y;
        int newlbx = px;
        int newlby = py - random.nextInt(H) - 1;
        int newrtx = newlbx + W + 1;
        int newrty = newlby + H + 1;
        Position newlb = new Position(newlbx, newlby);
        Position newrt = new Position(newrtx, newrty);
        Position[] newPosition = new Position[]{newlb, newrt};
        if (!checkAvailability(newlb, newrt)) {
            return null;
        } else {
            return newPosition;
        }
    }

    /**
     * return the random new room position[leftbottom, righttop] coordinate in the west
     * @param point start point
     * @param W width of the room
     * @param H height of the room
     * @return the new coordination of westroom
     */
    private Position[] newWestPosition(Position point, int W, int H) {
        int px = point.x;
        int py = point.y;
        int newrtx = px;
        int newrty = py + random.nextInt(H) + 1;
        int newlbx = newrtx - W - 1;
        int newlby = newrty - H - 1;
        Position newlb = new Position(newlbx, newlby);
        Position newrt = new Position(newrtx, newrty);
        Position[] newPosition = new Position[]{newlb, newrt};
        if (!checkAvailability(newlb, newrt)) {
            return null;
        } else {
            return newPosition;
        }
    }

    /**
     * return the random new room position[leftbottom, righttop] coordinate in the south
     * @param point start point
     * @param W width of the room
     * @param H height of the room
     * @return the new coordination of southroom
     */
    private Position[] newSouthPosition(Position point, int W, int H) {
        int px = point.x;
        int py = point.y;
        int newrtx = px + random.nextInt(W) + 1;
        int newrty = py;
        int newlbx = newrtx - W - 1;
        int newlby = newrty - H - 1;
        Position newlb = new Position(newlbx, newlby);
        Position newrt = new Position(newrtx, newrty);
        Position[] newPosition = new Position[]{newlb, newrt};
        if (!checkAvailability(newlb, newrt)) {
            return null;
        } else {
            return newPosition;
        }
    }

    /**
     * Returns random exit position and direction from a given rectangular room
     * @param leftBottom current room leftbottom coordinate
     * @param rightTop current room righttop coordinate
     * @param currentDirection current direction
     * @return new direction and new position coordinate after move towards the new random direction
     */
    private Object[] randomExit(Position leftBottom, Position rightTop, String currentDirection) {
        int lbX = leftBottom.x;
        int lbY = leftBottom.y;
        int rtX = rightTop.x;
        int rtY = rightTop.y;
        int w = rtX - lbX - 1;
        int h = rtY - lbY - 1;
        Object[] exitAndDirection = new Object[2];

        // Decide nextDirection using Linkedlist data structuere
        List<String> directions = new LinkedList<>();
        directions.add(NORTH);
        directions.add(EAST);
        directions.add(SOUTH);
        directions.add(WEST);
        directions.remove(getReverseDirc(currentDirection)); // prevent back to previous direction
        // eg : from north to south -> current direction = south, so remove north direction, otherwise, back to original
        String nextDirection = directions.get(random.nextInt(directions.size())); // then generate a random direction

        // Decide next exitPosition
        Position nextExitPosition;
        switch (nextDirection) {
            case NORTH:
                nextExitPosition = new Position(rtX - random.nextInt(w) - 1, rtY);
                break;
            case EAST:
                nextExitPosition = new Position(rtX, rtY - random.nextInt(h) - 1);
                break;
            case SOUTH:
                nextExitPosition = new Position(lbX + random.nextInt(w) + 1, lbY);
                break;
            default:
                nextExitPosition = new Position(lbX, lbY + random.nextInt(h) + 1);
                break;
        }

        exitAndDirection[0] = nextExitPosition;
        exitAndDirection[1] = nextDirection;
        return exitAndDirection;
    }
    // @aviatesk
    /* Makes a mono exit from a given rectangular room and calls another recursiveAddRandomRoom */
    private void monoExit(Position leftBottom, Position rightTop, String currentDirection) {
        Object[] exitAndDirection = randomExit(leftBottom, rightTop, currentDirection);
        Position nextExitPosition = (Position) exitAndDirection[0];
        String nextDirection = (String) exitAndDirection[1];
        recursiveAddRandomRoom(nextExitPosition, nextDirection);
    }

    // @aviatesk
    /* Makes bi exits from a given rectangular room and calls another recursiveAddRandomRoom */
    private void biExit(Position leftBottom, Position rightUpper, String currentDirection) {
        Object[] exitAndDirection1 = randomExit(leftBottom, rightUpper, currentDirection);
        Position nextExitPosition1 = (Position) exitAndDirection1[0];
        String nextDirection1 = (String) exitAndDirection1[1];

        Object[] exitAndDirection2;
        Position nextExitPosition2 = nextExitPosition1;
        String nextDirection2 = nextDirection1;
        while (nextDirection2.equals(nextDirection1)) {
            exitAndDirection2 = randomExit(leftBottom, rightUpper, currentDirection);
            nextExitPosition2 = (Position) exitAndDirection2[0];
            nextDirection2 = (String) exitAndDirection2[1];
        }

        recursiveAddRandomRoom(nextExitPosition1, nextDirection1);
        recursiveAddRandomRoom(nextExitPosition2, nextDirection2);
    }
    // @aviatesk
    /* Makes tri exits from a given rectangular room and calls another recursiveAddRandomRoom */
    private void triExit(Position leftBottom, Position rightUpper, String currentDirection) {
        Object[] exitAndDirection1 = randomExit(leftBottom, rightUpper, currentDirection);
        Position nextExitPosition1 = (Position) exitAndDirection1[0];
        String nextDirection1 = (String) exitAndDirection1[1];

        Object[] exitAndDirection2;
        Position nextExitPosition2 = nextExitPosition1;
        String nextDirection2 = nextDirection1;
        while (nextDirection2.equals(nextDirection1)) {
            exitAndDirection2 = randomExit(leftBottom, rightUpper, currentDirection);
            nextExitPosition2 = (Position) exitAndDirection2[0];
            nextDirection2 = (String) exitAndDirection2[1];
        }

        Object[] exitAndDirection3;
        Position nextExitPosition3 = nextExitPosition1;
        String nextDirection3 = nextDirection1;
        while (nextDirection3.equals(nextDirection1) || nextDirection3.equals(nextDirection2)) {
            exitAndDirection3 = randomExit(leftBottom, rightUpper, currentDirection);
            nextExitPosition3 = (Position) exitAndDirection3[0];
            nextDirection3 = (String) exitAndDirection3[1];
        }

        recursiveAddRandomRoom(nextExitPosition1, nextDirection1);
        recursiveAddRandomRoom(nextExitPosition2, nextDirection2);
        recursiveAddRandomRoom(nextExitPosition3, nextDirection3);
    }

    // @aviatesk
    /* Recursively adds random rooms */
    private void recursiveAddRandomRoom(Position exitPosition, String currentDirection) {

        int exitX = exitPosition.x;
        int exitY = exitPosition.y;
        int w = random.nextInt(MAXROOMWIDTH) + 1;
        int h = random.nextInt(MAXROOMHEIGHT) + 1;

        Position entryPosition;
        Position[] lrPositions;
        switch (currentDirection) {
            case NORTH:
                entryPosition = new Position(exitX, exitY + 1);
                lrPositions = newNorthPosition(entryPosition, w, h);
                break;
            case EAST:
                entryPosition = new Position(exitX + 1, exitY);
                lrPositions = newEastPosition(entryPosition, w, h);
                break;
            case SOUTH:
                entryPosition = new Position(exitX, exitY - 1);
                lrPositions = newSouthPosition(entryPosition, w, h);
                break;
            default:
                entryPosition = new Position(exitX - 1, exitY);
                lrPositions = newWestPosition(entryPosition, w, h);
                break;
        }

        if (lrPositions != null) {

            Exit(exitPosition);
            Position leftBottom = lrPositions[0];
            Position rightUpper = lrPositions[1];
            generateRoom(leftBottom, rightUpper);
            entrance(entryPosition);

            switch (random.nextInt(3) + 1) {
                /* Comment in below for more simpler world */
//                case 1: monoExit(leftBottom, rightUpper, currentDirection); break;
                case 2:
                    biExit(leftBottom, rightUpper, currentDirection);
                    break;
                default:
                    triExit(leftBottom, rightUpper, currentDirection);
                    break;
            }

        }

    }

    /**
     * Returns a world generated with given random seed
     *
     * @return a randomly generated world
     */
    TETile[][] generate() {
        initialize();

        // Make the first room
        Position[] lrPositions = newNorthPosition(initialPosition,MAXROOMWIDTH, MAXROOMHEIGHT);
        Position leftBottom = lrPositions[0];
        Position rightTop = lrPositions[1];
        generateRoom(leftBottom, rightTop);
        generateEntrance(initialPosition);

        // Recursively call recursiveAddRandomRoom via first calling triExit
        triExit(leftBottom, rightTop, NORTH);

        return world;
    }

    // Main method just to check this class works itself
    public static void main(String[] args) {
        int w = 80;
        int h = 50;
        TERenderer ter = new TERenderer();
        ter.initialize(w, h);
        worldGenerator wg = new worldGenerator(w, h, 40, 5, 42);
        wg.initialize();
        wg.generate();
        ter.renderFrame(wg.world);
    }
}
