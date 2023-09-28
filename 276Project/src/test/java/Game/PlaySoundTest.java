package Game;

import org.junit.Before;
import org.junit.Test;

public class PlaySoundTest {

    PlaySound s;
    @Before
    public void init(){
         s = new PlaySound();
    }

    @Test
    public void playM(){
        s.playMusic(5);
    }

    @Test
    public void StopM(){
        s.playMusic(6);
        s.stopMusic();
    }

    @Test
    public void playSE(){
        s.playSE(5);
    }

}
