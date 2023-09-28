package Game;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.sound.sampled.Clip;

public class SoundTest {


    Sound s;
    @Before
    public void init(){
        s = new Sound();

    }
    @Test
    public void soundC(){
        Sound sr = new Sound();
        assert (sr instanceof Sound);
    }

    @Test
    public void playS(){
        s.setFile(6);
        s.play();
    }

    @Test
    public void LoopS(){
        s.setFile(6);
        s.loop();
    }

    @Test
    public void stopS(){
        s.setFile(6);
        s.stop();
    }








}
