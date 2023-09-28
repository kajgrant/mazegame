package Game;

public class PlaySound {
    private static Sound music = new Sound();
    private static Sound se = new Sound();


    /**
     * Plays background music
     *
     * @param i Index of the file needed to be set found from the {@link Sound}
     *          class
     */
    public static void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    /**
     * Stops music
     */
    public static void stopMusic() {
        music.stop();
    }

    /**
     * Plays sound effects
     *
     * @param i Index of the file needed to be set found from the {@link Sound}
     *          class
     */
    public static void playSE(int i) {
        se.setFile(i);
        se.play();
    }

}
