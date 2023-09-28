package Game;

import Tile.Tile;
import Entity.Entity;

import java.util.ArrayList;

/**
 * Class that checks collisions between entities and tiles, objects, and
 * monsters
 * 
 * @author bkh6
 * @author smc26
 * @author kgrantma
 */
public class CollisionChecker {

    GameConsole console;

    /**
     * Constructs a new CollisionChecker instance and saves a reference to a given {@link GameConsole}
     * @param console GameConsole to be saved
     */
    public CollisionChecker(GameConsole console) {
        this.console = console;
    }

    /**
     * Detects if an {@link Entity} is colliding with its nearest two tiles based on
     * direction. It considers its so
     * called 'left' and 'right' tiles in a given direction, and checks if it is
     * colliding respectively by matching
     * if the left and right tiles are of type {@link Tile.Type#WALL}. Valid
     * directions are "up", "down", "left",
     * "right"
     * 
     * @param entity    The entity to check collision with
     * @param direction The direction in which to check collision with
     * @return Whether the entity collides with {@link Tile.Type#WALL} objects in a
     *         given direction
     */
    public boolean detectWallCollision(Entity entity, String direction) {
        boolean retVal = false;
        entity.solidArea.x = entity.worldPosX + entity.solidArea.x;
        entity.solidArea.y = entity.worldPosY + entity.solidArea.y;

        int tileSize = ScreenSettings.tileSize();

        int top_bound = (entity.worldPosY + entity.solidArea.y) / tileSize / 2;
        int bottom_bound;
        int left_bound = (entity.worldPosX + entity.solidArea.x) / tileSize / 2;
        int right_bound;

        Tile tile_left, tile_right;
        Tile[][] tiles = console.gameSetter.getTile();

        switch (direction) {
            case "up":
                // y position + solid area's y component - entity's speed
                int top_bound_sum = entity.worldPosY + entity.solidArea.y - entity.speed;
                top_bound = top_bound_sum / tileSize / 2;
                right_bound = (entity.worldPosX + entity.solidArea.x + entity.solidArea.width + 16)
                        / tileSize / 2;
                tile_left = tiles[top_bound][left_bound];
                tile_right = tiles[top_bound][right_bound];
                if (tile_left.type == Tile.Type.WALL || tile_right.type == Tile.Type.WALL)
                    retVal = true;
                break;
            case "down":
                // y position + solid area's y component + 3 * solid area's height + entity's
                // speed
                int bottom_bound_sum = entity.worldPosY + entity.solidArea.y + 3 * entity.solidArea.height
                        + entity.speed;
                right_bound = (entity.worldPosX + entity.solidArea.x + entity.solidArea.width + 16)
                        / tileSize / 2;
                bottom_bound = bottom_bound_sum / tileSize / 2;
                tile_left = tiles[bottom_bound][left_bound];
                tile_right = tiles[bottom_bound][right_bound];
                if (tile_left.type == Tile.Type.WALL || tile_right.type == Tile.Type.WALL)
                    retVal = true;
                break;
            case "left":
                // x position + solid area's x component - 8 - entity's speed
                // (-8 is a constant which pushes the box left)
                int left_bound_sum = entity.worldPosX + entity.solidArea.x - 8 - entity.speed;
                bottom_bound = (entity.worldPosY + entity.solidArea.y + entity.solidArea.height + 32)
                        / tileSize / 2;
                left_bound = left_bound_sum / tileSize / 2;
                tile_left = tiles[top_bound][left_bound];
                tile_right = tiles[bottom_bound][left_bound];
                if (tile_left.type == Tile.Type.WALL || tile_right.type == Tile.Type.WALL)
                    retVal = true;
                break;
            case "right":
                // x position + solid area's x component + 2 * solid area's width + entity's
                // speed
                int right_bound_sum = entity.worldPosX + entity.solidArea.x + 2 * entity.solidArea.width + entity.speed;
                bottom_bound = (entity.worldPosY + entity.solidArea.y + entity.solidArea.height + 32)
                        / tileSize / 2;
                right_bound = right_bound_sum / tileSize / 2;
                tile_left = tiles[top_bound][right_bound];
                tile_right = tiles[bottom_bound][right_bound];
                if (tile_left.type == Tile.Type.WALL || tile_right.type == Tile.Type.WALL)
                    retVal = true;
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        return retVal;
    }

    /**
     * Utility function which checks whether a given entity collides with a
     * {@link Tile.Type#WALL} object in any
     * direction
     * 
     * @param entity The entity to check
     * @return An {@link ArrayList<String>} of possible directions, consisting of
     *         strings "left", "right",
     *         "up", "down
     */
    public ArrayList<String> getPossibleMoves(Entity entity) {
        ArrayList<String> possibleMoves = new ArrayList<String>();

        if (!detectWallCollision(entity, "left")) {
            possibleMoves.add("left");
        }
        if (!detectWallCollision(entity, "right")) {
            possibleMoves.add("right");
        }
        if (!detectWallCollision(entity, "up")) {
            possibleMoves.add("up");
        }
        if (!detectWallCollision(entity, "down")) {
            possibleMoves.add("down");
        }

        return possibleMoves;
    }

    /**
     * Checks if collisions occur with other entities
     * 
     * @param entity the entity to be checked for collisions
     * @param target the collisionInstance (either entity or object)
     * @return returns true if collision occurs, otherwise false
     */
    public Boolean checkInstanceCollision(Entity entity, CollisionInstance target) {
        Boolean isCollision = false;

        if (target != null) {
            // Get entity's solid area position
            entity.solidArea.x = entity.worldPosX + entity.solidArea.x;
            entity.solidArea.y = entity.worldPosY + entity.solidArea.y;
            // Get the object's solid area position
            target.solidArea.x = target.worldPosX + target.solidArea.x;
            target.solidArea.y = target.worldPosY + target.solidArea.y;

            // Simulating entity's movement after it is moved
            // Checking if the movement will result in a collision
            // If it does, return the index it touched
            // Checking for each direction
            switch (entity.direction) {
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(target.solidArea)) {
                        entity.collisionOn = true;
                        isCollision = true;
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(target.solidArea)) {
                        entity.collisionOn = true;
                        isCollision = true;
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(target.solidArea)) {
                        entity.collisionOn = true;
                        isCollision = true;
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(target.solidArea)) {
                        entity.collisionOn = true;
                        isCollision = true;
                    }
                    break;
            }
        }
        // resetting all the measurements
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        target.solidArea.x = target.solidAreaDefaultX;
        target.solidArea.y = target.solidAreaDefaultY;

        // Returning the index of the touched (untouched if index = 999) object
        return isCollision;
    }

}
