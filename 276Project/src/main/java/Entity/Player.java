package Entity;

import Game.*;
import Object.DoorOpen;
import Object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Player class which has its movements controlled by the user, fields and
 * methods are inherited from {@link Entity} class
 * 
 * @author bkh6
 * @author kgrantma
 *
 */
public class Player extends Entity {

    /**
     * Represents the input of the user
     */
    KeyHandler keyInput;


    /**
     * Represents the sprite image of the object
     */
    public BufferedImage upRight, upLeft, downRight, downLeft, leftWalk, leftStatic, rightWalk, rightStatic;

    /**
     * Represents the coordinates of the player relative to the screen
     */
    public final int screenX;
    public final int screenY;

    /**
     * Represents screen height and width from {@link ScreenSettings} class
     */
    private int screenWidth = ScreenSettings.screenWidth();
    private int screenHeight = ScreenSettings.screenHeight();

    /**
     * Represents the monsters from {@link GameSetter} class
     */
    private Monster[] monsters;


    /**
     * Represents the objects from {@link GameSetter} class
     */
    private SuperObject[] obj;
    /**
     * Represents the number of gold the player has accumulated
     */
    public static int normalGold = 0;
    /**
     * Represents the total number of gold the player has accumulated - including
     * bonus points
     */
    public static int score = 0;
    /**
     * Boolean to check if the player is currently invincible
     */
    public Boolean isInvincible = false;
    /**
     * Number of frames the player is invincible for
     * 60 frames/second * 2 seconds = 120 frames of invulnerability
     */
    public int numInvincibleFrames = 60 * 2;
    /**
     * Counter to track how many frames have elapsed since invulnerability started
     */
    private int invincibleCounter = 0;

    /**
     * Constructs a new player object
     * 
     * @param console  {@link GameConsole} to reference from.
     * @param keyInput {@link KeyHandler} to reference from.
     */
    public Player(GameConsole console, KeyHandler keyInput) {
        super(console);
        this.keyInput = keyInput;
        monsters = console.gameSetter.getMonster();
        obj = console.gameSetter.getObjects();

        screenX = screenWidth / 2 - (tileSize / 2);
        screenY = screenHeight / 2 - (tileSize / 2);

        // Setting and getting player
        setDefaultPosition();
        getPlayerImages();

    }

    /**
     * Sets the player's default position, speed, and direction
     */
    public void setDefaultPosition() {
        worldPosX = tileSize * 16;
        worldPosY = tileSize * 30;
        speed = 4;
        direction = "up";
    }

    /**
     * Restores the player's score, gold, and health and sets invincibility and
     * collision to false
     */
    public void restorePlayer() {
        normalGold = 0;
        score = 0;
        isInvincible = false;
        collisionOn = false;
    }

    /**
     * Gets the player's default image from the res folder using
     * {@link #setup(String)} method.
     */
    private void getPlayerImages() {
        upLeft = setup("/player/backLeft");
        upRight = setup("/player/backRight");
        downLeft = setup("/player/frontLeft");
        downRight = setup("/player/frontRight");
        leftStatic = setup("/player/leftStatic");
        leftWalk = setup("/player/leftWalk");
        rightStatic = setup("/player/rightStatic");
        rightWalk = setup("/player/rightWalk");

    }

    /**
     * Draws the Player object on the screen based on its position (x, y).
     * Appropriately handles relative position according to {@link GameConsole}.
     * @param graphic2D {@link Graphics2D} object to be drawn onto
     */
    public void draw(Graphics2D graphic2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = upRight;
                }
                if (spriteNum == 2) {
                    image = upLeft;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = downLeft;
                }
                if (spriteNum == 2) {
                    image = downRight;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = leftStatic;
                }
                if (spriteNum == 2) {
                    image = leftWalk;
                }
                break;
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
     * Updates the player image based on keyInput, collisions, health, and sprite
     * image
     */
    public void update() {
        if (keyInput.upPress || keyInput.downPress || keyInput.leftPress || keyInput.rightPress) {
            if (keyInput.upPress) {
                direction = "up";
            } else if (keyInput.downPress) {
                direction = "down";
            } else if (keyInput.leftPress) {
                direction = "left";
            } else if (keyInput.rightPress) {
                direction = "right";
            }

            collisionOn = false;

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    CollisionInstance checkCollision = new CollisionInstance(obj[i]);
                    if (cChecker.checkInstanceCollision(this, checkCollision)) {
                        pickUpObject(i);
                    }
                }
            }


            collisionOn = cChecker.detectWallCollision(this, direction);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
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
            // Player image changes every 10 frames
            if (spriteCount > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }

        }

        if (isInvincible) {
            if (invincibleCounter > numInvincibleFrames) {
                isInvincible = false;
                invincibleCounter = 0;
            } else {
                invincibleCounter++;
            }
        }

        for (int i = 0; i < monsters.length; i++) {
            CollisionInstance checkCollision = new CollisionInstance(monsters[i]);
            if (cChecker.checkInstanceCollision(this, checkCollision)) {
                checkMonsterType(i);
            }
        }

    }

    /**
     * Takes the damage and applies it to the player's health and decreases score.
     * Changes game state if score dips below zero
     * 
     * @param damage damage amount to player
     */

    private void dealDamage(int damage) {
        if (!isInvincible) {
            score -= damage;
            isInvincible = true;

            console.ui.addMessage(" - " + damage, 1);
            if (console.ui.muteCheck == false) {
                PlaySound.playSE(4);
            }

            if (score < 0) {
                PlaySound.stopMusic();
                if (console.ui.muteCheck == false) {
                    PlaySound.playSE(0);
                }
                console.state = GameConsole.gameState.GAME_OVER_STATE;
            }
        }
    }

    /**
     * Responds with specified behaviours based on object index and name
     * 
     * @param i index of the object in  {@link AssetSetter} class 
     */
    private void checkMonsterType(int i) {

        if (monsters[i].isMove == true) {
            PlaySound.stopMusic();
            if (console.ui.muteCheck == false) {
                PlaySound.playSE(0);
            }
            console.state = GameConsole.gameState.GAME_OVER_STATE;
        } else {
            dealDamage(monsters[i].damage);
        }

    }

    /**
     * Represents actions when player comes int contact with objects
     * @param i Index of the object that is detected by {@link CollisionChecker} class
     */
    private void pickUpObject(int i) {

        // To Delete the object that was just touched
        String objectName = obj[i].name;
        int numGold = console.numGold;

        switch (objectName) {
            case "Gold":
                normalGold++;
                score += 10;
                console.ui.addMessage("+ 10", 0);
                obj[i] = null;
                if (console.ui.muteCheck == false) {
                    PlaySound.playSE(1);
                }
                if (normalGold == numGold) {
                    console.ui.addMessage("Door Opened!", 3);
                    if (console.ui.muteCheck == false) {
                        PlaySound.playSE(3);
                    }
                }
                break;
            // Opening door once all the gold has been collected
            case "DoorClosed":
                if (normalGold == numGold) {
                    obj[i] = new DoorOpen();
                    obj[i].worldX = 25 * tileSize;
                    obj[i].worldY = 43 * tileSize;
                    console.state = GameConsole.gameState.WON_STATE;
                    PlaySound.stopMusic();
                    if (console.ui.muteCheck == false) {
                        PlaySound.playSE(6);
                    }
                }
                break;
            case "Diamond":
                if (!obj[i].isHidden) {
                    score += 50;
                    console.ui.addMessage("+ 50", 2);
                    obj[i].isHidden = true;
                    if (console.ui.muteCheck == false) {
                        PlaySound.playSE(2);
                    }
                }
                break;

        }

    }

    /**
     * Gets the gold amount
     * @return Number of gold coins
     */
    public static int getNormalGold(){
        return normalGold;
    }

    /**
     * Gets the score
     * @return Score of the player (includes bonus and regular rewards)
     */
    public static int getScore(){
        return score;
    }

}
