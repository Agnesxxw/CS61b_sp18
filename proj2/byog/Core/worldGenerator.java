package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;
import java.util.Random;

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
     * return the random new room position[leftbottom, righttop] coordinate in the north
     * @param point start point
     * @param W width of the room
     * @param H height of the room
     * @return
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
     * @return
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
     * @return
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
     * @return
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

}
