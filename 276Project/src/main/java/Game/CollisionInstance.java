package Game;

import java.awt.Rectangle;
import Object.SuperObject;
import Entity.Entity;

/**
 * Wrapper class for detecting collisions in the {@link CollisionChecker} class
 * 
 * @author kgrantma
 */
public class CollisionInstance {
    public Rectangle solidArea;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public int worldPosX, worldPosY;

    /**
     * Constructor using type {@link Entity}
     * 
     * @param entity The player/monster object
     */
    public CollisionInstance(Entity entity) {
        this.solidArea = entity.solidArea;
        this.solidAreaDefaultX = entity.solidAreaDefaultX;
        this.solidAreaDefaultY = entity.solidAreaDefaultY;
        this.worldPosX = entity.worldPosX;
        this.worldPosY = entity.worldPosY;
    }

    /**
     * Constructor using type {@link SuperObject}
     * 
     * @param object The object instance
     */
    public CollisionInstance(SuperObject object) {
        this.solidArea = object.solidArea;
        this.solidAreaDefaultX = object.solidAreaDefaultX;
        this.solidAreaDefaultY = object.solidAreaDefaultY;
        this.worldPosX = object.worldX;
        this.worldPosY = object.worldY;
    }
}
