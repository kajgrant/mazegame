package Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * Sound class retrieves appropriate resources and provides sound effects and music
 * @author bkh6
 */
public class Sound {
    /**
     * Represents sound effect and music
     */
    Clip clip;

    /**
     * An array to hold all the sound effects and music
     */
    URL[] soundURL = new URL[30];



    /**
     * Retrieves the sounds from res folder and adds it to the array
     */
    public Sound() {

        //Adding all the sound types
        soundURL[0] = getClass().getResource("/sound/gameover.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/hitmonster.wav");
        soundURL[5] = getClass().getResource("/sound/Fiddlesticks.wav");
        soundURL[6] = getClass().getResource("/sound/fanfare.wav");
    }

    /**
     * Sets and opens file for usage
     *
     * @param i Index of the file needed to be set from the {@link #soundURL} array.
     */
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * plays the sound
     */
    public void play() {
        clip.start();
    }

    /**
     * loops the sound
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the sound
     */
    public void stop() {
        clip.stop();
    }



}
