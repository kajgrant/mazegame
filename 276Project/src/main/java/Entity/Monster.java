package Entity;

import Game.GameConsole;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Monster class for creating both moving and stationary monsters
 * 
 * @author kgrantma
 * @since 1.0
 */
public class Monster extends Entity {

    /**
     * Represents the damage associated with the monster instance
     */
    protected int damage = 10;

    /**
     * Boolean dictating if the enemy is stationary or moving
     */
    public boolean isMove = false;

    /**
     * Represents the sprite image of the object
     */
    public BufferedImage leftWalk, leftStatic, rightWalk, rightStatic;

    /**
     * Represents random movement
     */
    private Random rand = new Random();

    /**
     * List of possible moves the monster can make
     */
    ArrayList<String> possibleMoves;

    /**
     * Constructs a new Monster object.
     * 
     * @param console  {@link GameConsole} GameConsole instance
     * @param moveBool Boolean representing if the enemy is moving or stationary
     */
    public Monster(GameConsole console, boolean moveBool) {
        super(console);
        worldPosX = tileSize * 25;
        worldPosY = tileSize * 21;
        isMove = moveBool;
        possibleMoves = new ArrayList<String>();

        // Setting and getting monster
        setDefaultPosition();
        getMonsterImages();
    }

    /**
     * Sets monster's initial position
     */
    private void setDefaultPosition() {
        worldPosX = tileSize * 25;
        worldPosY = tileSize * 21;
        speed = 0;
        direction = "down";
    }

    /**
     * Gets images from res folder
     */
    private void getMonsterImages() {
        String path = "";
        if (isMove) {
            path = "bat";
        } else {
            path = "snake";
        }

        leftStatic = setup("/monster/" + path + "/leftStatic");
        leftWalk = setup("/monster/" + path + "/leftWalk");
        rightStatic = setup("/monster/" + path + "/rightStatic");
        rightWalk = setup("/monster/" + path + "/rightWalk");
    }

    /**
     * Draws the Monster object on the screen based on its position (x, y).
     * Appropriately handles relative position according to {@link GameConsole}.
     * 
     * @param graphic2D {@link Graphics2D} object to be drawn onto
     */
    public void draw(Graphics2D graphic2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
            case "left":
                if (spriteNum == 1) {
                    image = leftStatic;
                }
                if (spriteNum == 2) {
                    image = leftWalk;
                }
                break;
            case "down":
            case "right":
                if (spriteNum == 1) {
                    image = rightStatic;
                }
                if (spriteNum == 2) {
                    image = rightWalk;
                }
                break;
        }
        int[] arr = calculateDrawPosition();
        graphic2D.drawImage(image, arr[0], arr[1], tileSize, tileSize, null);
    }

    /**
     * Calculates the best direction to move based on the given set of possible
     * moves
     * 
     */
    private String calculateBestMove() {
        int manhattenDist;
        int lowestDist = 9999;
        String bestMove = "";

        int playerX = console.player.worldPosX;
        int playerY = console.player.worldPosY;

        int nextX, nextY;

        possibleMoves = cChecker.getPossibleMoves(this);

        for (int i = 0; i < possibleMoves.size(); i++) {
            nextX = 0;
            nextY = 0;
            switch (possibleMoves.get(i)) {
                case "left":
                    nextX = -1;
                    break;
                case "right":
                    nextX = 1;
                    break;
                case "up":
                    nextY = -1;
                    break;
                case "down":
                    nextY = 1;
                    break;
            }

            manhattenDist = Math.abs(playerX - (worldPosX + nextX * tileSize))
                    + Math.abs(playerY - (worldPosY + nextY * tileSize));
            if (manhattenDist < lowestDist) {
                bestMove = possibleMoves.get(i);
                lowestDist = manhattenDist;
            }

        }

        return bestMove;
    }

    /**
     * Sets the direction to minimize player distance and speed to a random value
     * 
     */
    private void moveRandom() {
        // Monster will move at a speed between 1-4
        speed = 1 + rand.nextInt(4);
        direction = calculateBestMove();
    }

    /**
     * Updates the monsters position based on the set speed, as well as changes the
     * monster's image
     * 
     */
    public void update() {
        collisionOn = cChecker.detectWallCollision(this, direction);

        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldPosY -= speed;
                    break;
                case "down":
                    worldPosY += speed;
                    break;
                case "left":
                    worldPosX -= speed;
                    break;
                case "right":
                    worldPosX += speed;
                    break;
            }
        }

        spriteCount++;
        // monster image changes every 10 frames
        if (spriteCount > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCount = 0;
            if (isMove) {
                moveRandom();
            }
        }

    }

}
