package Object;

import org.junit.Test;

public class GoldTest {
    @Test
    public void createGold(){
        Gold g = new Gold();
        assert(g instanceof Gold);

    }
}
